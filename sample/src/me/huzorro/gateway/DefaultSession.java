package me.huzorro.gateway;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import me.huzorro.gateway.SessionFuture.SessionCloseFuture;
import me.huzorro.gateway.SessionFuture.SessionLoginFuture;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public  class DefaultSession implements Session {
    private final Logger logger = LoggerFactory.getLogger(DefaultSession.class);
    private Channel channel;
    private Object attachment;
    private ConcurrentMap<Object, MessageFuture> requestMap = new ConcurrentHashMap<Object, MessageFuture>();
    private ConsistentHashQueueGroup<BlockingQueue<MessageFuture>, MessageFuture> responseQueue;
    private ConsistentHashQueueGroup<BlockingQueue<MessageFuture>, MessageFuture> requestQueue;
    private ConsistentHashQueueGroup<BlockingQueue<MessageFuture>, MessageFuture> deliverQueue;
    private ConsistentHashQueueGroup<BlockingQueue<MessageFuture>, MessageFuture> messageQueue;
    private ConcurrentMap<Object, Future<?>> scheduledTaskMap = new ConcurrentHashMap<Object, Future<?>>();
    private ScheduledExecutorService scheduledExecutor;
    private SessionConfig config;
    private Semaphore windows;
    private SessionCloseFuture closeFuture = new SessionCloseFuture(this);
    private SessionLoginFuture loginFuture = new SessionLoginFuture(this);
    public DefaultSession(Channel channel,
            ConsistentHashQueueGroup<BlockingQueue<MessageFuture>, MessageFuture> requestQueue,
            ConsistentHashQueueGroup<BlockingQueue<MessageFuture>, MessageFuture> responseQueue,
            ConsistentHashQueueGroup<BlockingQueue<MessageFuture>, MessageFuture> deliverQueue,
            ConsistentHashQueueGroup<BlockingQueue<MessageFuture>, MessageFuture> messageQueue,
            ScheduledExecutorService scheduleExecutor,
            SessionConfig config) {
        setChannel(channel);
        setConfig(config);
        this.responseQueue = responseQueue;
        this.requestQueue = requestQueue;
        this.deliverQueue = deliverQueue;
        this.messageQueue = messageQueue;
        this.scheduledExecutor = scheduleExecutor;        
        windows = new Semaphore(this.config.getWindows());
    }
    /**
     * @return the channel
     */
    public Channel getChannel() {
        return channel;
    }

    /**
     * @param channel the channel to set
     */
    public void setChannel(Channel channel) {
        this.channel = channel;
        this.channel.getCloseFuture().addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if(future.isSuccess()) {
                    closeFuture.setClose();
                    for(Future<?> futureOfTask : scheduledTaskMap.values()) {
                        futureOfTask.cancel(true);
                    }
                    for(MessageFuture futureOfMessage : requestMap.values()) {
                        requestQueue.put(futureOfMessage);
                    }
                }
            }
        });
    }

    /* (non-Javadoc)
     * @see me.huzorro.gateway.Session#writeRequest(me.huzorro.gateway.Message)
     */
    @Override
    public void writeRequest(MessageFuture messageFuture) throws InterruptedException {
        windows.acquire();
        messageFuture.getMessage().incrementAndGetRequests();
        requestMap.put(messageFuture.getMessage().getHeader().getSequenceId(), messageFuture);
        channel.write(messageFuture.getMessage());
    }
    /* (non-Javadoc)
     * @see me.huzorro.gateway.Session#writeResponse(me.huzorro.gateway.Message)
     */
    @Override
    public void writeResponse(Message<?> message) throws InterruptedException {
        MessageFuture requestFuture = requestMap.remove(message.getHeader().getSequenceId());
        MessageFuture responseFuture = new MessageFuture(requestFuture.getMessage().setResponse(message));
        responseFuture.addListener(new QFutureListener() {
            @Override
            public void onComplete(QFuture future) {
                if(future.isSuccess()) messageQueue.remove((MessageFuture) future);
            }
        });
        responseQueue.put(responseFuture);        
        messageQueue.put(responseFuture);
        requestFuture.setSuccess();
        windows.release();
    }
    /* (non-Javadoc)
     * @see me.huzorro.gateway.Session#setAttachment(java.lang.Object)
     */
    @Override
    public void setAttachment(Object attachment) {
        this.attachment = attachment;
    }
    /* (non-Javadoc)
     * @see me.huzorro.gateway.Session#getAttachment()
     */
    @Override
    public Object getAttachment() {
        return attachment;
    }
    /* (non-Javadoc)
     * @see me.huzorro.gateway.Session#writeRequestAndScheduleTask(me.huzorro.gateway.Message)
     */
    @Override
    public void writeRequestAndScheduleTask(final MessageFuture messageFuture) throws InterruptedException {
        if(messageFuture.getMessage().incrementAndGetRequests() > config.getMaxRetry()) {
            logger.info("The request to send a message number has reached the maximum limit {}", messageFuture.getMessage().getRequests());
            logger.info("session closing");
            close();
            return;
        }
        writeRequest(messageFuture);
        Future<?> future = scheduledExecutor.schedule(new Runnable() {
            
            @Override
            public void run() {
                try {
                    writeRequestAndScheduleTask(messageFuture);
                } catch (InterruptedException e) {
                    logger.error("windows acquire interrupted: {}", e);
                    try {
                        close();
                    } catch (InterruptedException e1) {
                        logger.error("session close interrupted: {}", e1);
                    }
                }
            }
        } , config.getRetryWaitTime(), TimeUnit.MILLISECONDS);
        scheduledTaskMap.put(messageFuture.getMessage().getHeader().getSequenceId(), future);
        logger.debug("The request to send a message number is {}", messageFuture.getMessage().getRequests());
    }
    /* (non-Javadoc)
     * @see me.huzorro.gateway.Session#writeResponseAndScheduleTask(me.huzorro.gateway.Message)
     */
    @Override
    public void writeResponseAndScheduleTask(Message<?> message) throws InterruptedException {
        writeResponse(message);
        scheduledTaskMap.remove(message.getHeader().getSequenceId()).cancel(true);
    }
    /* (non-Javadoc)
     * @see me.huzorro.gateway.Session#writeDeliver(me.huzorro.gateway.MessageFuture)
     */
    @Override
    public void writeDeliver(Message<?> message) throws Exception {
        MessageFuture messageFuture = new MessageFuture(message);
        messageFuture.addListener(new QFutureListener() {
            @Override
            public void onComplete(QFuture future) {
                if(future.isSuccess()) messageQueue.remove((MessageFuture) future);
            }
        });
        messageQueue.put(messageFuture);
        deliverQueue.put(messageFuture);
    }    
    /* (non-Javadoc)
     * @see me.huzorro.gateway.Session#close()
     */
    @Override
    public void close() throws InterruptedException {
        channel.close();
    }
    /* (non-Javadoc)
     * @see me.huzorro.gateway.Session#getConfig()
     */
    @Override
    public SessionConfig getConfig() {
        return config;
    }
    /* (non-Javadoc)
     * @see me.huzorro.gateway.Session#setConfig(me.huzorro.gateway.SessionConfig)
     */
    @Override
    public void setConfig(SessionConfig config) {
        this.config = config;
    }
    /* (non-Javadoc)
     * @see me.huzorro.gateway.Session#isClosed()
     */
    @Override
    public boolean isClosed() {
        return closeFuture.isClosed();
    }
    /* (non-Javadoc)
     * @see me.huzorro.gateway.Session#getCloseFuture()
     */
    @Override
    public SessionCloseFuture getCloseFuture() {
        return closeFuture;
    }
    /* (non-Javadoc)
     * @see me.huzorro.gateway.Session#getLoginFuture()
     */
    @Override
    public SessionLoginFuture getLoginFuture() {
        return loginFuture;
    }
    

}

/**
 * 
 */
package me.huzorro.gateway;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ScheduledExecutorService;

import org.jboss.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author huzorro
 * @param <T>
 *
 */
public class DefaultServerSessionFactory<T> implements Factory<T> {
    private static final Logger logger = LoggerFactory.getLogger(DefaultServerSessionFactory.class);
    private ConsistentHashQueueGroup<BlockingQueue<MessageFuture>, MessageFuture> requestQueue;
    private ConsistentHashQueueGroup<BlockingQueue<MessageFuture>, MessageFuture> responseQueue;
    private ConsistentHashQueueGroup<BlockingQueue<MessageFuture>, MessageFuture> deliverQueue;
    private ConsistentHashQueueGroup<BlockingQueue<MessageFuture>, MessageFuture> messageQueue;
    private ScheduledExecutorService scheduleExecutor;
    private SessionPool sessionPool;
    private SessionConfig config;
    private Channel channel;
	public DefaultServerSessionFactory(            
            ConsistentHashQueueGroup<BlockingQueue<MessageFuture>, MessageFuture> requestQueue,
            ConsistentHashQueueGroup<BlockingQueue<MessageFuture>, MessageFuture> responseQueue,
            ConsistentHashQueueGroup<BlockingQueue<MessageFuture>, MessageFuture> deliverQueue,
            ConsistentHashQueueGroup<BlockingQueue<MessageFuture>, MessageFuture> messageQueue,
            ScheduledExecutorService scheduleExecutor,
            SessionPool sessionPool
            ) {
        this.requestQueue = requestQueue;
        this.responseQueue = responseQueue;
        this.deliverQueue = deliverQueue;
        this.messageQueue = messageQueue;
        this.scheduleExecutor = scheduleExecutor;
        this.sessionPool = sessionPool;
	}    
	/**
	 * 
	 */
	public DefaultServerSessionFactory(            
            SessionConfig config,
            ConsistentHashQueueGroup<BlockingQueue<MessageFuture>, MessageFuture> requestQueue,
            ConsistentHashQueueGroup<BlockingQueue<MessageFuture>, MessageFuture> responseQueue,
            ConsistentHashQueueGroup<BlockingQueue<MessageFuture>, MessageFuture> deliverQueue,
            ConsistentHashQueueGroup<BlockingQueue<MessageFuture>, MessageFuture> messageQueue,
            ScheduledExecutorService scheduleExecutor,
            SessionPool sessionPool,
            Channel channel
            ) {
        this.config = config;
        this.requestQueue = requestQueue;
        this.responseQueue = responseQueue;
        this.deliverQueue = deliverQueue;
        this.messageQueue = messageQueue;
        this.scheduleExecutor = scheduleExecutor;
        this.sessionPool = sessionPool;
        this.channel = channel;
	}
	
	/**
	 * @param channel the channel to set
	 */
	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	
	/**
	 * @param config the config to set
	 */
	public void setConfig(SessionConfig config) {
		this.config = config;
	}
	@Override
	@SuppressWarnings("unchecked")
	public T create() throws Exception {
        final DefaultSession session = new DefaultSession(channel, 
                requestQueue, responseQueue, deliverQueue, messageQueue, scheduleExecutor, config);
        session.getLoginFuture().addListener(new QFutureListener() {
            
            @Override
            public void onComplete(QFuture future) {
                // TODO Auto-generated method stub
                if(future.isSuccess())
                    try {
                        sessionPool.put(session, true);
                    } catch (Exception e) {
                        logger.error("put session to session pool failed {}", e);
                    }
            }
        });		
		return (T) session;
	}

}

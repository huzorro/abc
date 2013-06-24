package me.huzorro.gateway;

import java.util.concurrent.ScheduledExecutorService;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * the default client sessions factory
 * @author huzorro(huzorro@gmail.com)
 */
public class DefaultClientSessionFactory<T extends Session> implements Factory<T> {
    private static final Logger logger = LoggerFactory.getLogger(DefaultClientSessionFactory.class);
    private NettyTcpClient<ChannelFuture> nettyTcpClient;
    private Factory<?> messageFactory;
    private BdbQueueMap<Long, QFuture<Message>> receiveQueue;
    private BdbQueueMap<Long, QFuture<Message>> responseQueue;
    private BdbQueueMap<Long, QFuture<Message>> deliverQueue;
    private ScheduledExecutorService scheduleExecutor;
    private SessionPool sessionPool;
    private SessionConfig config;
    /**
     * 
     * @param nettyTcpClient
     * @param messageFactory
     * @param config
     * @param deliverQueue
     * @param responseQueue
     * @param receiveQueue
     * @param scheduleExecutor
     * @param sessionPool
     */
    public DefaultClientSessionFactory(
            NettyTcpClient<ChannelFuture> nettyTcpClient, 
            Factory<?> messageFactory,
            SessionConfig config,
            BdbQueueMap<Long, QFuture<Message>> deliverQueue,
            BdbQueueMap<Long, QFuture<Message>> responseQueue,
            BdbQueueMap<Long, QFuture<Message>> receiveQueue,
            ScheduledExecutorService scheduleExecutor,
            SessionPool sessionPool) {
        this.nettyTcpClient = nettyTcpClient;
        this.messageFactory = messageFactory;
        this.config = config;
        this.receiveQueue = receiveQueue;
        this.responseQueue = responseQueue;
        this.deliverQueue = deliverQueue;
        this.scheduleExecutor = scheduleExecutor;
        this.sessionPool = sessionPool;
    }

    @Override
    public T create() throws Exception {
        ChannelFuture future = nettyTcpClient.connect();
        future.addListener(new ChannelFutureListener(
        		) {
			
			@Override
			public void operationComplete(ChannelFuture future) throws Exception {
				if(future.isSuccess()) {
					Channel channel = future.sync().getChannel();
					if(channel.isWritable()) {
			            final DefaultSession session = new DefaultSession(channel, 
			                    deliverQueue, responseQueue, receiveQueue, scheduleExecutor, config);

			            session.getLoginFuture().addListener(new QFutureListener<Session>() {
			                
			                @Override
			                public void onComplete(QFuture<Session> future) {
			                    if(future.isSuccess())
			                        try {
			                            sessionPool.put(future.getMaster(), true);
			                            logger.info("Channel created successfully {}", session.getChannel());
			                        } catch (Exception e) {
			                        	e.printStackTrace();
			                            logger.error("put session to session pool failed", e.getCause());
			                        }
			                }
			            });	
			            Message message = (Message) messageFactory.create();
			            channel.write(message);			            
					}
				}
			}
		});
        return null;
    }

}

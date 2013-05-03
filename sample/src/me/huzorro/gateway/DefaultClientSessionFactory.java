package me.huzorro.gateway;

import java.util.concurrent.ScheduledExecutorService;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public class DefaultClientSessionFactory<T extends DefaultSession> implements Factory<T> {
    private static final Logger logger = LoggerFactory.getLogger(DefaultClientSessionFactory.class);
    private NettyTcpClient<ChannelFuture> nettyTcpClient;
    private Factory<?> messageFactory;
    private BdbQueueMap<Long, MessageFuture> requestQueue;
    private BdbQueueMap<Long, MessageFuture> responseQueue;
    private BdbQueueMap<Long, MessageFuture> deliverQueue;
    private ScheduledExecutorService scheduleExecutor;
    private SessionPool sessionPool;
    private SessionConfig config;

    public DefaultClientSessionFactory(
            NettyTcpClient<ChannelFuture> nettyTcpClient, 
            Factory<?> messageFactory,
            SessionConfig config,
            BdbQueueMap<Long, MessageFuture> requestQueue,
            BdbQueueMap<Long, MessageFuture> responseQueue,
            BdbQueueMap<Long, MessageFuture> deliverQueue,
            ScheduledExecutorService scheduleExecutor,
            SessionPool sessionPool) {
        this.nettyTcpClient = nettyTcpClient;
        this.messageFactory = messageFactory;
        this.config = config;
        this.requestQueue = requestQueue;
        this.responseQueue = responseQueue;
        this.deliverQueue = deliverQueue;
        this.scheduleExecutor = scheduleExecutor;
        this.sessionPool = sessionPool;
    }

    /* (non-Javadoc)
     * @see me.huzorro.gateway.Factory#create()
     */
    @Override
    @SuppressWarnings("unchecked")
    public T create() throws Exception {
        ChannelFuture future = nettyTcpClient.connect();
        Channel channel = future.awaitUninterruptibly().getChannel();
        final DefaultSession session = new DefaultSession(channel, 
                requestQueue, responseQueue, deliverQueue, scheduleExecutor, config);
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
        if(channel.isWritable()) {
            Message<?> message = (Message<?>) messageFactory.create();
            channel.write(message);            
        }
        return (T)session;
    }

}

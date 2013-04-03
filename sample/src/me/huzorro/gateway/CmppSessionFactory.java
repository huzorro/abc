package me.huzorro.gateway;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ScheduledExecutorService;

import org.jboss.netty.channel.ChannelFuture;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public class CmppSessionFactory<T extends CmppSession> extends DefaultSessionFactory<T> {

    /**
     * @param nettyTcpClient
     * @param loginRequestMessage
     * @param config
     * @param requestQueue
     * @param responseQueue
     * @param deliverQueue
     * @param messageQueue
     * @param scheduleExecutor
     * @param sessionPool
     */
    public CmppSessionFactory(
            NettyTcpClient<ChannelFuture> nettyTcpClient,
            Message<?> loginRequestMessage,
            SessionConfig config,
            ConsistentHashQueueGroup<BlockingQueue<MessageFuture>, MessageFuture> requestQueue,
            ConsistentHashQueueGroup<BlockingQueue<MessageFuture>, MessageFuture> responseQueue,
            ConsistentHashQueueGroup<BlockingQueue<MessageFuture>, MessageFuture> deliverQueue,
            ConsistentHashQueueGroup<BlockingQueue<MessageFuture>, MessageFuture> messageQueue,
            ScheduledExecutorService scheduleExecutor, SessionPool sessionPool) {
        super(nettyTcpClient, loginRequestMessage, config, requestQueue, responseQueue,
                deliverQueue, messageQueue, scheduleExecutor, sessionPool);
    }

}

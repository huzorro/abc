package me.huzorro.gateway;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ScheduledExecutorService;

import org.jboss.netty.channel.Channel;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public class CmppSession extends DefaultSession {

    /**
     * @param channel
     * @param requestQueue
     * @param responseQueue
     * @param deliverQueue
     * @param messageQueue
     * @param scheduleExecutor
     * @param config
     */
    public CmppSession(
            Channel channel,
            ConsistentHashQueueGroup<BlockingQueue<MessageFuture>, MessageFuture> requestQueue,
            ConsistentHashQueueGroup<BlockingQueue<MessageFuture>, MessageFuture> responseQueue,
            ConsistentHashQueueGroup<BlockingQueue<MessageFuture>, MessageFuture> deliverQueue,
            ConsistentHashQueueGroup<BlockingQueue<MessageFuture>, MessageFuture> messageQueue,
            ScheduledExecutorService scheduleExecutor, SessionConfig config) {
        super(channel, requestQueue, responseQueue, deliverQueue, messageQueue,
                scheduleExecutor, config);
    }

    
}

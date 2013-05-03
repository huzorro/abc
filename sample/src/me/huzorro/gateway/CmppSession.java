package me.huzorro.gateway;

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
            BdbQueueMap<Long, MessageFuture> requestQueue,
            BdbQueueMap<Long, MessageFuture> responseQueue,
            BdbQueueMap<Long, MessageFuture> deliverQueue,
            ScheduledExecutorService scheduleExecutor, SessionConfig config) {
        super(channel, requestQueue, responseQueue, deliverQueue,
                scheduleExecutor, config);
    }

    
}

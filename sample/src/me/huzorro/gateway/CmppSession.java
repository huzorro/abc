package me.huzorro.gateway;

import java.util.concurrent.ScheduledExecutorService;

import org.jboss.netty.channel.Channel;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public class CmppSession extends DefaultSession {
	
	/**
	 * 
	 * @param channel
	 * @param receiveQueue
	 * @param responseQueue
	 * @param deliverQueue
	 * @param scheduleExecutor
	 * @param config
	 */
    public CmppSession(
            Channel channel,
            BdbQueueMap<Long, QFuture<Message>> deliverQueue,
            BdbQueueMap<Long, QFuture<Message>> responseQueue,
            BdbQueueMap<Long, QFuture<Message>> receiveQueue,
            ScheduledExecutorService scheduleExecutor, SessionConfig config) {
    	super(channel, deliverQueue, responseQueue, receiveQueue, scheduleExecutor, config);
    }

    
}

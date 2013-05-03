/**
 * 
 */
package me.huzorro.gateway;

import java.util.concurrent.ScheduledExecutorService;

import org.jboss.netty.channel.Channel;

/**
 * @author huzorro
 * @param <T>
 *
 */
public class CmppServerSessionFactory<T> extends DefaultServerSessionFactory<T> {
	/**
	 * 
	 * @param config
	 * @param requestQueue
	 * @param responseQueue
	 * @param deliverQueue
	 * @param messageQueue
	 * @param scheduleExecutor
	 * @param sessionPool
	 */
	public CmppServerSessionFactory(
			BdbQueueMap<Long, MessageFuture> requestQueue,
			BdbQueueMap<Long, MessageFuture> responseQueue,
			BdbQueueMap<Long, MessageFuture> deliverQueue,
			ScheduledExecutorService scheduleExecutor, SessionPool sessionPool) {
		super(requestQueue, responseQueue, deliverQueue, 
				scheduleExecutor, sessionPool);
	}

	/**
	 * 
	 * @param config
	 * @param requestQueue
	 * @param responseQueue
	 * @param deliverQueue
	 * @param messageQueue
	 * @param scheduleExecutor
	 * @param sessionPool
	 * @param channel
	 */
	public CmppServerSessionFactory(
			SessionConfig config,
			BdbQueueMap<Long, MessageFuture> requestQueue,
			BdbQueueMap<Long, MessageFuture> responseQueue,
			BdbQueueMap<Long, MessageFuture> deliverQueue,
			ScheduledExecutorService scheduleExecutor, SessionPool sessionPool,
			Channel channel) {
		super(config, requestQueue, responseQueue, deliverQueue, 
				scheduleExecutor, sessionPool, channel);
	}

}

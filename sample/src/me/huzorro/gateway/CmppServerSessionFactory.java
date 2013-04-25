/**
 * 
 */
package me.huzorro.gateway;

import java.util.concurrent.BlockingQueue;
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
			ConsistentHashQueueGroup<BlockingQueue<MessageFuture>, MessageFuture> requestQueue,
			ConsistentHashQueueGroup<BlockingQueue<MessageFuture>, MessageFuture> responseQueue,
			ConsistentHashQueueGroup<BlockingQueue<MessageFuture>, MessageFuture> deliverQueue,
			ConsistentHashQueueGroup<BlockingQueue<MessageFuture>, MessageFuture> messageQueue,
			ScheduledExecutorService scheduleExecutor, SessionPool sessionPool) {
		super(requestQueue, responseQueue, deliverQueue, messageQueue,
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
			ConsistentHashQueueGroup<BlockingQueue<MessageFuture>, MessageFuture> requestQueue,
			ConsistentHashQueueGroup<BlockingQueue<MessageFuture>, MessageFuture> responseQueue,
			ConsistentHashQueueGroup<BlockingQueue<MessageFuture>, MessageFuture> deliverQueue,
			ConsistentHashQueueGroup<BlockingQueue<MessageFuture>, MessageFuture> messageQueue,
			ScheduledExecutorService scheduleExecutor, SessionPool sessionPool,
			Channel channel) {
		super(config, requestQueue, responseQueue, deliverQueue, messageQueue,
				scheduleExecutor, sessionPool, channel);
	}

}

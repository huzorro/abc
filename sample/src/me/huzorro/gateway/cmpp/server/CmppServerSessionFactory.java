/**
 * 
 */
package me.huzorro.gateway.cmpp.server;

import java.util.concurrent.ScheduledExecutorService;

import me.huzorro.gateway.BdbQueueMap;
import me.huzorro.gateway.DefaultServerSessionFactory;
import me.huzorro.gateway.Message;
import me.huzorro.gateway.QFuture;
import me.huzorro.gateway.SessionConfig;
import me.huzorro.gateway.SessionPool;

import org.jboss.netty.channel.Channel;
/**
 * 
 * @author huzorro(huzorro@gmail.com)
 *
 * @param <T>
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
			BdbQueueMap<Long, QFuture<Message>> receiveQueue,
			BdbQueueMap<Long, QFuture<Message>> responseQueue,
			BdbQueueMap<Long, QFuture<Message>> deliverQueue,
			ScheduledExecutorService scheduleExecutor, SessionPool sessionPool) {
		super(receiveQueue, responseQueue, deliverQueue, 
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
			BdbQueueMap<Long, QFuture<Message>> receiveQueue,
			BdbQueueMap<Long, QFuture<Message>> responseQueue,
			BdbQueueMap<Long, QFuture<Message>> deliverQueue,
			ScheduledExecutorService scheduleExecutor, SessionPool sessionPool,
			Channel channel) {
		super(config, receiveQueue, responseQueue, deliverQueue, 
				scheduleExecutor, sessionPool, channel);
	}

}

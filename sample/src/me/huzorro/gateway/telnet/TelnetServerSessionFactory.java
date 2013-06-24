/**
 * 
 */
package me.huzorro.gateway.telnet;

import java.util.concurrent.ScheduledExecutorService;

import org.jboss.netty.channel.Channel;

import me.huzorro.gateway.BdbQueueMap;
import me.huzorro.gateway.DefaultServerSessionFactory;
import me.huzorro.gateway.Message;
import me.huzorro.gateway.QFuture;
import me.huzorro.gateway.SessionConfig;
import me.huzorro.gateway.SessionPool;

/**
 * @author huzorro(huzorro@gmail.com)
 *
 */
public class TelnetServerSessionFactory<T> extends DefaultServerSessionFactory<T> {

	public TelnetServerSessionFactory(
			BdbQueueMap<Long, QFuture<Message>> requestQueue,
			BdbQueueMap<Long, QFuture<Message>> responseQueue,
			BdbQueueMap<Long, QFuture<Message>> deliverQueue,
			ScheduledExecutorService scheduleExecutor, SessionPool sessionPool) {
		super(requestQueue, responseQueue, deliverQueue, scheduleExecutor, sessionPool);
	}

	/**
	 * @param config
	 * @param requestQueue
	 * @param responseQueue
	 * @param deliverQueue
	 * @param scheduleExecutor
	 * @param sessionPool
	 * @param channel
	 */
	public TelnetServerSessionFactory(SessionConfig config,
			BdbQueueMap<Long, QFuture<Message>> requestQueue,
			BdbQueueMap<Long, QFuture<Message>> responseQueue,
			BdbQueueMap<Long, QFuture<Message>> deliverQueue,
			ScheduledExecutorService scheduleExecutor, SessionPool sessionPool,
			Channel channel) {
		super(config, requestQueue, responseQueue, deliverQueue, scheduleExecutor,
				sessionPool, channel);
	}

}

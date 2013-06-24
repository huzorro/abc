package me.huzorro.gateway;

import java.util.concurrent.ScheduledExecutorService;

import org.jboss.netty.channel.ChannelFuture;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public class CmppClientSessionFactory<T extends Session> extends DefaultClientSessionFactory<T> {

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
    public CmppClientSessionFactory(
            NettyTcpClient<ChannelFuture> nettyTcpClient,
            Factory<?> messageFactory,
            SessionConfig config,
            BdbQueueMap<Long, QFuture<Message>> deliverQueue,
            BdbQueueMap<Long, QFuture<Message>> responseQueue,
            BdbQueueMap<Long, QFuture<Message>> receiveQueue,
            ScheduledExecutorService scheduleExecutor, SessionPool sessionPool) {
		super(nettyTcpClient, messageFactory, config, deliverQueue,
				responseQueue, receiveQueue, scheduleExecutor, sessionPool);
    }

}

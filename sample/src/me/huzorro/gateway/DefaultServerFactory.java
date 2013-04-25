/**
 * 
 */
package me.huzorro.gateway;

import org.jboss.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author huzorro
 * @param <T>
 *
 */
public class DefaultServerFactory<T> implements Factory<T> {
    private static final Logger logger = LoggerFactory.getLogger(DefaultServerFactory.class);
    private NettyTcpServer<Channel> nettyTcpServer;
	/**
	 * 
	 */
	public DefaultServerFactory(NettyTcpServer<Channel> nettyTcpServer) {
		this.nettyTcpServer = nettyTcpServer;
	}

	@Override
	@SuppressWarnings("unchecked")
	public T create() throws Exception {
		return (T) nettyTcpServer.bind();
	}

}

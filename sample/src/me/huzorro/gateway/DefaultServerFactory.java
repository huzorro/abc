/**
 * 
 */
package me.huzorro.gateway;

import org.jboss.netty.channel.Channel;

/**
 * @author huzorro
 * @param <T>
 *
 */
public class DefaultServerFactory<T> implements Factory<T> {
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

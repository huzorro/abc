/**
 * 
 */
package me.huzorro.gateway;

import java.net.InetSocketAddress;

import org.jboss.netty.bootstrap.ServerBootstrap;

/**
 * @author huzorro
 * @param <T>
 *
 */
public class NettyTcpServer<T> implements TcpServer<T> {

    private ServerBootstrap bootstrap;
    private InetSocketAddress serverAddress;
    
    public NettyTcpServer(String host, int port, ServerBootstrap bootstrap) {
        this(bootstrap, new InetSocketAddress(host, port));
    }
    /**
     * 
     */
    public NettyTcpServer(ServerBootstrap bootstrap, InetSocketAddress serverAddress) {
        this.bootstrap = bootstrap;
        this.serverAddress = serverAddress;
    }

	@Override
	@SuppressWarnings("unchecked")
	public T bind() {
		return (T) bootstrap.bind(serverAddress);
	}

}

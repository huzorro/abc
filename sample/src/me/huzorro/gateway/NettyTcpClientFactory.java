package me.huzorro.gateway;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 * @param <T>
 */
public class NettyTcpClientFactory<T extends NettyTcpClient<ChannelFuture>> implements Factory<T> {
    private InetSocketAddress serverAddress;
    private ClientBootstrap bootstrap;
    /**
     * 
     * @param host
     * @param port
     * @param channelPipelineFactory
     * @param bootstrap
     */
    public NettyTcpClientFactory(
            String host, 
            int port, 
            ChannelPipelineFactory channelPipelineFactory,
            ClientBootstrap bootstrap) {
        this(new InetSocketAddress(host, port), 
                channelPipelineFactory,
                bootstrap,
                Executors.newCachedThreadPool(), 
                Executors.newCachedThreadPool());
    }
    public NettyTcpClientFactory(InetSocketAddress serverAddress, 
            ChannelPipelineFactory channelPipelineFactory,
            ClientBootstrap bootstrap,
            ExecutorService boss,
            ExecutorService work) {
        this.serverAddress = serverAddress;
        bootstrap.setFactory(new NioClientSocketChannelFactory(boss, work));
        bootstrap.setPipelineFactory(channelPipelineFactory);
        bootstrap.setOption("tcpNoDelay", true);
        bootstrap.setOption("keepAlive", true);
    }

    /* (non-Javadoc)
     * @see me.huzorro.gateway.Factory#create()
     */
    @Override
    @SuppressWarnings("unchecked")
    public T create() {
        return (T) new NettyTcpClient<ChannelFuture>(bootstrap, serverAddress);
    }

}

/**
 * 
 */
package me.huzorro.gateway.telnet;

import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;

import me.huzorro.gateway.BdbQueueMap;
import me.huzorro.gateway.DefaultServerSessionConfigFactory;
import me.huzorro.gateway.Message;
import me.huzorro.gateway.NettyTcpServer;
import me.huzorro.gateway.NettyTcpServerFactory;
import me.huzorro.gateway.QFuture;
import me.huzorro.gateway.Service;
import me.huzorro.gateway.Session;
import me.huzorro.gateway.SessionConfig;
import me.huzorro.gateway.SessionPool;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author huzorro(huzorro@gmail.com)
 *
 */
public class TelnetDuplexstreamServerService implements Service {

    private final Logger logger = LoggerFactory.getLogger(TelnetDuplexstreamServerService.class);
    private Map<String, SessionConfig> configMap;
    private Map<SessionConfig, ServerBootstrap> serverBootstrapMap;
    private Map<Object, BdbQueueMap<Long, QFuture<Message>>> requestMsgQueueMap;
    private Map<Object, BdbQueueMap<Long, QFuture<Message>>> responseMsgQueueMap;
    private Map<Object, BdbQueueMap<Long, QFuture<Message>>> deliverMsgQueueMap;
    private Map<SessionConfig, ScheduledExecutorService> scheduleExecutorMap;
    private Map<SessionConfig, SessionPool>  sessionPoolMap;      
	/**
	 * @param serverBootstrapMap 
	 * 
	 */
	public TelnetDuplexstreamServerService(            
            Map<String, SessionConfig> configMap,
            Map<SessionConfig, ServerBootstrap> serverBootstrapMap,
            Map<Object, BdbQueueMap<Long, QFuture<Message>>> requestMsgQueueMap,
            Map<Object, BdbQueueMap<Long, QFuture<Message>>> responseMsgQueueMap,
            Map<Object, BdbQueueMap<Long, QFuture<Message>>> deliverMsgQueueMap,
            Map<SessionConfig, ScheduledExecutorService> scheduleExecutorMap,
            Map<SessionConfig, SessionPool>  sessionPoolMap
            ) {
        this.configMap = configMap;
        this.serverBootstrapMap = serverBootstrapMap;
        this.requestMsgQueueMap = requestMsgQueueMap;
        this.responseMsgQueueMap = requestMsgQueueMap;
        this.deliverMsgQueueMap = deliverMsgQueueMap;
        this.scheduleExecutorMap = scheduleExecutorMap;
        this.sessionPoolMap = sessionPoolMap;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
        try {
            process();
        } catch (Exception e) {
            logger.error("Telnet duplexstream Server Service failed {}", e.getCause());
            Runtime.getRuntime().exit(-1);
        }
	}

	/* (non-Javadoc)
	 * @see me.huzorro.gateway.Service#process()
	 */
	@Override
	public void process() throws Exception {
        for(SessionConfig config : configMap.values()) {
        	TelnetServerSessionFactory<Session> sessionFactory = new TelnetServerSessionFactory<Session>(
                    requestMsgQueueMap.get(config), 
                    responseMsgQueueMap.get(config),
                    deliverMsgQueueMap.get(config), 
                    scheduleExecutorMap.get(config),
                    sessionPoolMap.get(config));
        	DefaultServerSessionConfigFactory<SessionConfig> configFactory = new TelnetServerSessionConfigFactory<SessionConfig>(config);
            ChannelPipelineFactory pipelineFactory = new TelnetServerChannelPipelineFactory(sessionFactory, configFactory, config);
            NettyTcpServerFactory<NettyTcpServer<Channel>> tcpServerFactory = 
            		 new NettyTcpServerFactory<NettyTcpServer<Channel>>(
                            config.getHost(), config.getPort(), pipelineFactory, serverBootstrapMap.get(config));
            NettyTcpServer<Channel> tcpServer = tcpServerFactory.create();
            
            tcpServer.bind();
        }
	}

}

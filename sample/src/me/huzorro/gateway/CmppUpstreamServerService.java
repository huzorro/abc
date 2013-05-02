/**
 * 
 */
package me.huzorro.gateway;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ScheduledExecutorService;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author huzorro
 *
 */
public class CmppUpstreamServerService implements Service {
    private final Logger logger = LoggerFactory.getLogger(CmppUpstreamServerService.class);
    private Map<String, SessionConfig> configMap;
    private Map<SessionConfig, ServerBootstrap> serverBootstrapMap;
    private Map<Object, ConsistentHashQueueGroup<BlockingQueue<MessageFuture>, MessageFuture>> requestMsgQueueMap;
    private Map<Object, ConsistentHashQueueGroup<BlockingQueue<MessageFuture>, MessageFuture>> responseMsgQueueMap;
    private Map<Object, ConsistentHashQueueGroup<BlockingQueue<MessageFuture>, MessageFuture>> deliverMsgQueueMap;
    private Map<Object, ConsistentHashQueueGroup<BlockingQueue<MessageFuture>, MessageFuture>>messageQueueMap;
    private Map<SessionConfig, ScheduledExecutorService> scheduleExecutorMap;
    private Map<Map<String, SessionConfig>, SessionPool> sessionPoolMap;      
	/**
	 * @param serverBootstrapMap 
	 * 
	 */
	public CmppUpstreamServerService(            
            Map<String, SessionConfig> configMap,
            Map<SessionConfig, ServerBootstrap> serverBootstrapMap,
            Map<Object, ConsistentHashQueueGroup<BlockingQueue<MessageFuture>, MessageFuture>> requestMsgQueueMap,
            Map<Object, ConsistentHashQueueGroup<BlockingQueue<MessageFuture>, MessageFuture>>responseMsgQueueMap,
            Map<Object, ConsistentHashQueueGroup<BlockingQueue<MessageFuture>, MessageFuture>> deliverMsgQueueMap,
            Map<Object, ConsistentHashQueueGroup<BlockingQueue<MessageFuture>, MessageFuture>> messageQueueMap,
            Map<SessionConfig, ScheduledExecutorService> scheduleExecutorMap,
            Map<Map<String, SessionConfig>, SessionPool> sessionPoolMap
            ) {
        this.configMap = configMap;
        this.serverBootstrapMap = serverBootstrapMap;
        this.requestMsgQueueMap = requestMsgQueueMap;
        this.responseMsgQueueMap = requestMsgQueueMap;
        this.deliverMsgQueueMap = deliverMsgQueueMap;
        this.messageQueueMap = messageQueueMap;
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
            logger.error("Cmpp Upstream Server Service failed {}", e);
            Runtime.getRuntime().exit(-1);
        }
	}

	/* (non-Javadoc)
	 * @see me.huzorro.gateway.Service#process()
	 */
	@Override
	public void process() throws Exception {
        for(SessionConfig config : configMap.values()) {
        	CmppServerSessionFactory<Session> sessionFactory = new CmppServerSessionFactory<Session>(
                    requestMsgQueueMap.get(config), 
                    responseMsgQueueMap.get(config),
                    deliverMsgQueueMap.get(config), 
                    messageQueueMap.get(config),
                    scheduleExecutorMap.get(config),
                    sessionPoolMap.get(configMap));
        	DefaultServerSessionConfigFactory<SessionConfig> configFactory = new CmppUpstreamServerSessionConfigFactory<SessionConfig>();
            ChannelPipelineFactory pipelineFactory = new CmppUpstreamServerChannelPipelineFactory(sessionFactory, configFactory);
            NettyTcpServerFactory<NettyTcpServer<Channel>> tcpServerFactory = 
            		 new NettyTcpServerFactory<NettyTcpServer<Channel>>(
                            config.getHost(), config.getPort(), pipelineFactory, serverBootstrapMap.get(config));
            NettyTcpServer<Channel> tcpServer = tcpServerFactory.create();
            
            tcpServer.bind();
        }
	}

}

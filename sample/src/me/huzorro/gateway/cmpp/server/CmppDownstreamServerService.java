package me.huzorro.gateway.cmpp.server;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
/**
 * 
 * @author huzorro(huzorro@gmail.com)
 *
 */
public class CmppDownstreamServerService implements Service {

    private final Logger logger = LoggerFactory.getLogger(CmppDownstreamServerService.class);
    private Map<String, SessionConfig> configMap;
    private Map<SessionConfig, ServerBootstrap> serverBootstrapMap;
    private Map<Object, BdbQueueMap<Long, QFuture<Message>>> receiveMsgQueueMap;
    private Map<Object, BdbQueueMap<Long, QFuture<Message>>> responseMsgQueueMap;
    private Map<Object, BdbQueueMap<Long, QFuture<Message>>> deliverMsgQueueMap;
    private Map<SessionConfig, ScheduledExecutorService> scheduleExecutorMap;
    private Map<SessionConfig, SessionPool>  sessionPoolMap;
    private List<String> configList;
    private List<SessionConfig> downstreamServicesRunningList;
    /**
     * 
     * @param configMap
     * @param serverBootstrapMap
     * @param requestMsgQueueMap
     * @param responseMsgQueueMap
     * @param deliverMsgQueueMap
     * @param scheduleExecutorMap
     * @param sessionPoolMap
     * @param downstreamServicesRunningList
     */
	public CmppDownstreamServerService(            
            Map<String, SessionConfig> configMap,
            Map<SessionConfig, ServerBootstrap> serverBootstrapMap,
            Map<Object, BdbQueueMap<Long, QFuture<Message>>> receiveMsgQueueMap,
            Map<Object, BdbQueueMap<Long, QFuture<Message>>> responseMsgQueueMap,
            Map<Object, BdbQueueMap<Long, QFuture<Message>>> deliverMsgQueueMap,
            Map<SessionConfig, ScheduledExecutorService> scheduleExecutorMap,
            Map<SessionConfig, SessionPool>  sessionPoolMap,
            List<SessionConfig> downstreamServicesRunningList
            ) {
		this(configMap, serverBootstrapMap, receiveMsgQueueMap,
				responseMsgQueueMap, deliverMsgQueueMap, scheduleExecutorMap,
				sessionPoolMap, downstreamServicesRunningList, null);
	}    
	/**
	 * 
	 * @param configMap
	 * @param serverBootstrapMap
	 * @param requestMsgQueueMap
	 * @param responseMsgQueueMap
	 * @param deliverMsgQueueMap
	 * @param scheduleExecutorMap
	 * @param sessionPoolMap
	 * @param downstreamServicesRunningList
	 * @param configList
	 */
	public CmppDownstreamServerService(            
            Map<String, SessionConfig> configMap,
            Map<SessionConfig, ServerBootstrap> serverBootstrapMap,
            Map<Object, BdbQueueMap<Long, QFuture<Message>>> receiveMsgQueueMap,
            Map<Object, BdbQueueMap<Long, QFuture<Message>>> responseMsgQueueMap,
            Map<Object, BdbQueueMap<Long, QFuture<Message>>> deliverMsgQueueMap,
            Map<SessionConfig, ScheduledExecutorService> scheduleExecutorMap,
            Map<SessionConfig, SessionPool>  sessionPoolMap,
            List<SessionConfig> downstreamServicesRunningList,
            List<String> configList
            ) {
        this.configMap = configMap;
        this.serverBootstrapMap = serverBootstrapMap;
        this.receiveMsgQueueMap = receiveMsgQueueMap;
        this.responseMsgQueueMap = responseMsgQueueMap;
        this.deliverMsgQueueMap = deliverMsgQueueMap;
        this.scheduleExecutorMap = scheduleExecutorMap;
        this.sessionPoolMap = sessionPoolMap;
        this.downstreamServicesRunningList = downstreamServicesRunningList;
        this.configList = configList;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
        try {
            process();
        } catch (Exception e) {
            logger.error("Cmpp Upstream Server Service failed {}", e.getCause());
            Runtime.getRuntime().exit(-1);
        }
	}


	@Override
	public void process() throws Exception {
        for(SessionConfig config : configMap.values()) {
        	if(configList != null && !configList.contains(config.getChannelIds())) continue;
        	create(config);
        	downstreamServicesRunningList.add(config);
        }
	}
	
	protected void create(SessionConfig config) throws Exception {
		CmppServerSessionFactory<Session> sessionFactory = new CmppServerSessionFactory<Session>(
				receiveMsgQueueMap.get(config),
				responseMsgQueueMap.get(config),
				deliverMsgQueueMap.get(config),
				scheduleExecutorMap.get(config), sessionPoolMap.get(config));
		DefaultServerSessionConfigFactory<SessionConfig> configFactory = new CmppUpstreamServerSessionConfigFactory<SessionConfig>(
				config);
		ChannelPipelineFactory pipelineFactory = new CmppDownstreamServerChannelPipelineFactory(
				sessionFactory, configFactory, config);
		NettyTcpServerFactory<NettyTcpServer<Channel>> tcpServerFactory = new NettyTcpServerFactory<NettyTcpServer<Channel>>(
				config.getHost(), config.getPort(), pipelineFactory,
				serverBootstrapMap.get(config));
		NettyTcpServer<Channel> tcpServer = tcpServerFactory.create();

		tcpServer.bind();
	}

}

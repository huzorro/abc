package me.huzorro.gateway;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ScheduledExecutorService;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public class CmppUpstreamClientService implements Service {
    private final Logger logger = LoggerFactory.getLogger(CmppUpstreamClientService.class);
    private Map<String, SessionConfig> configMap;
    private Map<SessionConfig, ClientBootstrap> clientBootstrapMap;
    private Map<Object, ConsistentHashQueueGroup<BlockingQueue<MessageFuture>, MessageFuture>> requestMsgQueueMap;
    private Map<Object, ConsistentHashQueueGroup<BlockingQueue<MessageFuture>, MessageFuture>> responseMsgQueueMap;
    private Map<Object, ConsistentHashQueueGroup<BlockingQueue<MessageFuture>, MessageFuture>> deliverMsgQueueMap;
    private Map<Object, ConsistentHashQueueGroup<BlockingQueue<MessageFuture>, MessageFuture>>messageQueueMap;
    private Map<SessionConfig, ScheduledExecutorService> scheduleExecutorMap;
    private Map<Map<String, SessionConfig>, SessionPool> sessionPoolMap;   
    /**
     * 
     * @param configMap
     * @param clientBootstrapMap
     * @param requestMsgQueueMap
     * @param responseMsgQueueMap
     * @param deliverMsgQueueMap
     * @param messageQueueMap
     * @param scheduleExecutorMap
     * @param sessionPoolMap
     */
    public CmppUpstreamClientService(
            Map<String, SessionConfig> configMap,
            Map<SessionConfig, ClientBootstrap> clientBootstrapMap,
            Map<Object, ConsistentHashQueueGroup<BlockingQueue<MessageFuture>, MessageFuture>> requestMsgQueueMap,
            Map<Object, ConsistentHashQueueGroup<BlockingQueue<MessageFuture>, MessageFuture>>responseMsgQueueMap,
            Map<Object, ConsistentHashQueueGroup<BlockingQueue<MessageFuture>, MessageFuture>> deliverMsgQueueMap,
            Map<Object, ConsistentHashQueueGroup<BlockingQueue<MessageFuture>, MessageFuture>> messageQueueMap,
            Map<SessionConfig, ScheduledExecutorService> scheduleExecutorMap,
            Map<Map<String, SessionConfig>, SessionPool> sessionPoolMap
            ) {
        this.configMap = configMap;
        this.clientBootstrapMap = clientBootstrapMap;
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
            logger.error("Cmpp Upstream Client Service failed {}", e);
            Runtime.getRuntime().exit(-1);
        }
    }

    /* (non-Javadoc)
     * @see me.huzorro.gateway.Service#process()
     */
    @Override
    public void process() throws Exception {
        for(SessionConfig config : configMap.values()) {
            ChannelPipelineFactory pipelineFactory = new CmppUpstreamClientChannelPipelineFactory();
            NettyTcpClientFactory<NettyTcpClient<ChannelFuture>> tcpClientFactory = 
                    new NettyTcpClientFactory<NettyTcpClient<ChannelFuture>>(
                            config.getHost(), config.getPort(), pipelineFactory, clientBootstrapMap.get(config));
            NettyTcpClient<ChannelFuture> tcpClient = tcpClientFactory.create();
            
            CmppConnectRequestMessageFactory<CmppConnectRequestMessage<ChannelBuffer>> connectRequestMessageFacotry = 
                    new CmppConnectRequestMessageFactory<CmppConnectRequestMessage<ChannelBuffer>>((CmppUpstreamSessionConfig) config);
            CmppClientSessionFactory<CmppSession> sessionFactory = new CmppClientSessionFactory<CmppSession>(
                    tcpClient, 
                    connectRequestMessageFacotry, 
                    config, 
                    requestMsgQueueMap.get(config), 
                    responseMsgQueueMap.get(config),
                    deliverMsgQueueMap.get(config), 
                    messageQueueMap.get(config),
                    scheduleExecutorMap.get(config),
                    sessionPoolMap.get(configMap));   
            for(int i = 0; i < config.getMaxSessions(); i++) {
                sessionFactory.create();
            }
        }
    }

}

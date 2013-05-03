package me.huzorro.gateway;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.configuration.CombinedConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.DefaultConfigurationBuilder;
import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public class GlobalVars {
    private final static Logger logger = LoggerFactory.getLogger(GlobalVars.class);
    public static DefaultConfigurationBuilder configBuilder;
    public static CombinedConfiguration config;
    public static AtomicLong sequenceId = new AtomicLong();
    public static Charset defaultLocalCharset = Charset.forName("UTF-8");
    public static Charset defaultTransportCharset = Charset.forName("GBK");    
    public static Map<String, SessionConfig> duplexSessionConfigMap = new HashMap<String, SessionConfig>();
    public static Map<String, SessionConfig> upstreamSessionConfigMap = new HashMap<String, SessionConfig>();
    public static Map<String, SessionConfig> downstreamSessionConfigMap = new HashMap<String, SessionConfig>();
    public static Map<Map<String, SessionConfig>, SessionPool> sessionPoolMap = new HashMap<Map<String, SessionConfig>, SessionPool>();
    
    public static Map<Object, BdbQueueMap<Long, MessageFuture>> requestMsgQueueMap = 
    		new HashMap<Object, BdbQueueMap<Long, MessageFuture>>();
    
    
    public static Map<Object, BdbQueueMap<Long, MessageFuture>> responseMsgQueueMap = 
    		new HashMap<Object, BdbQueueMap<Long, MessageFuture>>();
    
    
    public static Map<Object, BdbQueueMap<Long, MessageFuture>> deliverMsgQueueMap = 
    		new HashMap<Object, BdbQueueMap<Long, MessageFuture>>();

    
    public static Map<SessionConfig, ExecutorService> executorServiceMap = new HashMap<SessionConfig, ExecutorService>();
    public static Map<SessionConfig, ScheduledExecutorService> scheduleExecutorMap = new HashMap<SessionConfig, ScheduledExecutorService>();
    public static Map<SessionConfig, ClientBootstrap> clientBootstrapMap = new HashMap<SessionConfig, ClientBootstrap>();
    public static Map<SessionConfig, ServerBootstrap> serverBootstrapMap = new HashMap<SessionConfig, ServerBootstrap>();
    
    static {
        configBuilder = new DefaultConfigurationBuilder();
        configBuilder.setFileName("configuration.xml");
        try {
            config = configBuilder.getConfiguration(true);
        } catch (ConfigurationException e) {
            logger.error("config builder failed {}", e);
        }
    }
}

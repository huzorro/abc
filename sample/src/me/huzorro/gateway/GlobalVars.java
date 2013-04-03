package me.huzorro.gateway;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.DefaultConfigurationBuilder;
import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.bootstrap.ServerBootstrap;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public class GlobalVars {
    public static CompositeConfiguration config = new CompositeConfiguration();
    public static DefaultConfigurationBuilder configBuilder;
    public static Map<String, SessionConfig> duplexSessionConfigMap = new HashMap<String, SessionConfig>();
    public static Map<String, SessionConfig> upstreamSessionConfigMap = new HashMap<String, SessionConfig>();
    public static Map<String, SessionConfig> downstreamSessionConfigMap = new HashMap<String, SessionConfig>();
    public static Map<Map<String, SessionConfig>, SessionPool> sessionPoolMap = new HashMap<Map<String, SessionConfig>, SessionPool>();
    
    
//    public static Map<SessionConfig, BlockingQueue<MessageFuture>> requestMsgQueueMap = new HashMap<SessionConfig, BlockingQueue<MessageFuture>>();
//    public static Map<SessionConfig, BlockingQueue<MessageFuture>> responseMsgQueueMap = new HashMap<SessionConfig, BlockingQueue<MessageFuture>>();
//    public static Map<SessionConfig, BlockingQueue<MessageFuture>> deliverMsgQueueMap = new HashMap<SessionConfig, BlockingQueue<MessageFuture>>();
//    public static Map<SessionConfig, BlockingQueue<MessageFuture>> messageQueueMap = new HashMap<SessionConfig, BlockingQueue<MessageFuture>>();
    
    public static Map<Object, ConsistentHashQueueGroup<BlockingQueue<MessageFuture>, MessageFuture>> requestMsgQueueMap = 
            new HashMap<Object, ConsistentHashQueueGroup<BlockingQueue<MessageFuture>, MessageFuture>>();
    
    
    public static Map<Object, ConsistentHashQueueGroup<BlockingQueue<MessageFuture>, MessageFuture>> responseMsgQueueMap = 
            new HashMap<Object, ConsistentHashQueueGroup<BlockingQueue<MessageFuture>, MessageFuture>>();
    
    public static Map<Object, ConsistentHashQueueGroup<BlockingQueue<MessageFuture>, MessageFuture>> deliverMsgQueueMap = 
            new HashMap<Object, ConsistentHashQueueGroup<BlockingQueue<MessageFuture>, MessageFuture>>();
    
    public static Map<Object, ConsistentHashQueueGroup<BlockingQueue<MessageFuture>, MessageFuture>> messageQueueMap = 
            new HashMap<Object, ConsistentHashQueueGroup<BlockingQueue<MessageFuture>, MessageFuture>>();
    
    public static Map<SessionConfig, ExecutorService> executorServiceMap = new HashMap<SessionConfig, ExecutorService>();
    public static Map<SessionConfig, ScheduledExecutorService> scheduleExecutorMap = new HashMap<SessionConfig, ScheduledExecutorService>();
    public static Map<SessionConfig, ClientBootstrap> clientBootstrapMap = new HashMap<SessionConfig, ClientBootstrap>();
    public static Map<SessionConfig, ServerBootstrap> serverBootstrapMap = new HashMap<SessionConfig, ServerBootstrap>();
    
    static {
        configBuilder = new DefaultConfigurationBuilder();
        configBuilder.setFileName("configuration.xml");
    }
}

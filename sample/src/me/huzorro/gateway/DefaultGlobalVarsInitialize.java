package me.huzorro.gateway;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.bootstrap.ServerBootstrap;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public class DefaultGlobalVarsInitialize implements GlobalVarsInitialize {

    /* (non-Javadoc)
     * @see me.huzorro.gateway.GlobalVarsInitialize#sessionConfigInitialize()
     */
    @Override
    public GlobalVarsInitialize sessionConfigInitialize() {
        new UpstreamSessionConfigMapFactory<Map<String,SessionConfig>, UpstreamSessionConfig>(GlobalVars.configBuilder, GlobalVars.upstreamSessionConfigMap);
        new DownstreamSessionConfigMapFactory<Map<String,SessionConfig>, DownstreamSessionConfig>(GlobalVars.configBuilder, GlobalVars.downstreamSessionConfigMap);
        new DuplexstreamSessionConfigMapFactory<Map<String,SessionConfig>, DuplexstreamSessionConfig>(GlobalVars.configBuilder, GlobalVars.duplexSessionConfigMap);
        return this;
    }

    /* (non-Javadoc)
     * @see me.huzorro.gateway.GlobalVarsInitialize#sessionPoolInitialize()
     */
    @Override
    public GlobalVarsInitialize sessionPoolInitialize() {
        GlobalVars.sessionPoolMap.put(GlobalVars.upstreamSessionConfigMap, new DefaultSessionPool());
        GlobalVars.sessionPoolMap.put(GlobalVars.downstreamSessionConfigMap, new DefaultSessionPool());
        GlobalVars.sessionPoolMap.put(GlobalVars.duplexSessionConfigMap, new DefaultSessionPool());
        return this;
    }

    /* (non-Javadoc)
     * @see me.huzorro.gateway.GlobalVarsInitialize#messageQueueInitialize()
     */
    @Override
    public GlobalVarsInitialize messageQueueInitialize() {
        for(SessionConfig config : GlobalVars.upstreamSessionConfigMap.values()) {
            Collection<BlockingQueue<MessageFuture>> deliverQueueNodes = 
                    new ArrayList<BlockingQueue<MessageFuture>>();
            for(int i = 0; i < config.getDeliverQueueSequence(); i++) {
                deliverQueueNodes.add(new LinkedBlockingQueue<MessageFuture>(config.getDeliverQueueSize()));
            }
            ConsistentHashQueueGroup<BlockingQueue<MessageFuture>, MessageFuture> deliverQueueGroup = 
                    new ConsistentHashQueueGroup<BlockingQueue<MessageFuture>, MessageFuture>(deliverQueueNodes);
            
            GlobalVars.deliverMsgQueueMap.put(config, deliverQueueGroup);
            
            Collection<BlockingQueue<MessageFuture>> messageQueueNodes = 
                    new ArrayList<BlockingQueue<MessageFuture>>(); 
            messageQueueNodes.add(new LinkedBlockingQueue<MessageFuture>());
            
            ConsistentHashQueueGroup<BlockingQueue<MessageFuture>, MessageFuture> messageQueueGroup = 
                    new ConsistentHashQueueGroup<BlockingQueue<MessageFuture>, MessageFuture>(messageQueueNodes);
            
            GlobalVars.messageQueueMap.put(config, messageQueueGroup);
//            GlobalVars.deliverMsgQueueMap.put(config, new LinkedBlockingQueue<MessageFuture>(config.getDeliverQueueSize()));
//            GlobalVars.messageQueueMap.put(config, new LinkedBlockingQueue<MessageFuture>());
        }
        for(SessionConfig config : GlobalVars.downstreamSessionConfigMap.values()) {
            Collection<BlockingQueue<MessageFuture>> requestQueueNodes = 
                    new ArrayList<BlockingQueue<MessageFuture>>();
            for(int i = 0; i < config.getRequestQueueSequence(); i++) {
                requestQueueNodes.add(new LinkedBlockingQueue<MessageFuture>(config.getRequestQueueSize()));
            }
            ConsistentHashQueueGroup<BlockingQueue<MessageFuture>, MessageFuture> requestQueueGroup = 
                    new ConsistentHashQueueGroup<BlockingQueue<MessageFuture>, MessageFuture>(requestQueueNodes);
            
            GlobalVars.deliverMsgQueueMap.put(config, requestQueueGroup);
            
            Collection<BlockingQueue<MessageFuture>> responseQueueNodes = 
                    new ArrayList<BlockingQueue<MessageFuture>>();
            for(int i = 0; i < config.getResponseQueueSequence(); i++) {
                responseQueueNodes.add(new LinkedBlockingQueue<MessageFuture>(config.getResponseQueueSize()));
            }
            ConsistentHashQueueGroup<BlockingQueue<MessageFuture>, MessageFuture> responseQueueGroup = 
                    new ConsistentHashQueueGroup<BlockingQueue<MessageFuture>, MessageFuture>(responseQueueNodes);
            
            GlobalVars.deliverMsgQueueMap.put(config, responseQueueGroup);
            
            Collection<BlockingQueue<MessageFuture>> messageQueueNodes = 
                    new ArrayList<BlockingQueue<MessageFuture>>(); 
            messageQueueNodes.add(new LinkedBlockingQueue<MessageFuture>());
            
            ConsistentHashQueueGroup<BlockingQueue<MessageFuture>, MessageFuture> messageQueueGroup = 
                    new ConsistentHashQueueGroup<BlockingQueue<MessageFuture>, MessageFuture>(messageQueueNodes);
            
            GlobalVars.messageQueueMap.put(config, messageQueueGroup);    
            
//            GlobalVars.requestMsgQueueMap.put(config, new LinkedBlockingQueue<MessageFuture>(config.getRequestQueueSize()));
//            GlobalVars.responseMsgQueueMap.put(config, new LinkedBlockingQueue<MessageFuture>(config.getResponseQueueSize()));
//            GlobalVars.messageQueueMap.put(config, new LinkedBlockingQueue<MessageFuture>());
        }
        for(SessionConfig config : GlobalVars.duplexSessionConfigMap.values()) {
            Collection<BlockingQueue<MessageFuture>> deliverQueueNodes = 
                    new ArrayList<BlockingQueue<MessageFuture>>();
            for(int i = 0; i < config.getDeliverQueueSequence(); i++) {
                deliverQueueNodes.add(new LinkedBlockingQueue<MessageFuture>(config.getDeliverQueueSize()));
            }
            ConsistentHashQueueGroup<BlockingQueue<MessageFuture>, MessageFuture> deliverQueueGroup = 
                    new ConsistentHashQueueGroup<BlockingQueue<MessageFuture>, MessageFuture>(deliverQueueNodes);
            
            GlobalVars.deliverMsgQueueMap.put(config, deliverQueueGroup);            
            
            Collection<BlockingQueue<MessageFuture>> requestQueueNodes = 
                    new ArrayList<BlockingQueue<MessageFuture>>();
            for(int i = 0; i < config.getRequestQueueSequence(); i++) {
                requestQueueNodes.add(new LinkedBlockingQueue<MessageFuture>(config.getRequestQueueSize()));
            }
            ConsistentHashQueueGroup<BlockingQueue<MessageFuture>, MessageFuture> requestQueueGroup = 
                    new ConsistentHashQueueGroup<BlockingQueue<MessageFuture>, MessageFuture>(requestQueueNodes);
            
            GlobalVars.deliverMsgQueueMap.put(config, requestQueueGroup);
            
            Collection<BlockingQueue<MessageFuture>> responseQueueNodes = 
                    new ArrayList<BlockingQueue<MessageFuture>>();
            for(int i = 0; i < config.getResponseQueueSequence(); i++) {
                responseQueueNodes.add(new LinkedBlockingQueue<MessageFuture>(config.getResponseQueueSize()));
            }
            ConsistentHashQueueGroup<BlockingQueue<MessageFuture>, MessageFuture> responseQueueGroup = 
                    new ConsistentHashQueueGroup<BlockingQueue<MessageFuture>, MessageFuture>(responseQueueNodes);
            
            GlobalVars.deliverMsgQueueMap.put(config, responseQueueGroup);
            
            Collection<BlockingQueue<MessageFuture>> messageQueueNodes = 
                    new ArrayList<BlockingQueue<MessageFuture>>(); 
            messageQueueNodes.add(new LinkedBlockingQueue<MessageFuture>());
            
            ConsistentHashQueueGroup<BlockingQueue<MessageFuture>, MessageFuture> messageQueueGroup = 
                    new ConsistentHashQueueGroup<BlockingQueue<MessageFuture>, MessageFuture>(messageQueueNodes);
            
            GlobalVars.messageQueueMap.put(config, messageQueueGroup);               
            
//            GlobalVars.deliverMsgQueueMap.put(config, new LinkedBlockingQueue<MessageFuture>(config.getDeliverQueueSize()));
//            GlobalVars.requestMsgQueueMap.put(config, new LinkedBlockingQueue<MessageFuture>(config.getRequestQueueSize()));
//            GlobalVars.responseMsgQueueMap.put(config, new LinkedBlockingQueue<MessageFuture>(config.getResponseQueueSize()));
//            GlobalVars.messageQueueMap.put(config, new LinkedBlockingQueue<MessageFuture>());
        }
        return this;
    }

    /* (non-Javadoc)
     * @see me.huzorro.gateway.GlobalVarsInitialize#threadPoolInitialize()
     */
    @Override
    public GlobalVarsInitialize threadPoolInitialize() {
        for(SessionConfig config : GlobalVars.upstreamSessionConfigMap.values()) {
            GlobalVars.executorServiceMap.put(config, 
                    config.getGeneralThreadNum() > 0 ?
                Executors.newFixedThreadPool(config.getGeneralThreadNum()) : 
                    Executors.newCachedThreadPool());
            
            GlobalVars.scheduleExecutorMap.put(config, 
                    config.getScheduleThreadNum() > 0 ? 
                            Executors.newScheduledThreadPool(config.getScheduleThreadNum()) :
                                Executors.newScheduledThreadPool(0));
        }
        for(SessionConfig config : GlobalVars.downstreamSessionConfigMap.values()) {
            GlobalVars.executorServiceMap.put(config, 
                    config.getGeneralThreadNum() > 0 ?
                Executors.newFixedThreadPool(config.getGeneralThreadNum()) : 
                    Executors.newCachedThreadPool());
            
            GlobalVars.scheduleExecutorMap.put(config, 
                    config.getScheduleThreadNum() > 0 ? 
                            Executors.newScheduledThreadPool(config.getScheduleThreadNum()) :
                                Executors.newScheduledThreadPool(0));            
        }
        for(SessionConfig config : GlobalVars.duplexSessionConfigMap.values()) {
            GlobalVars.executorServiceMap.put(config, 
                    config.getGeneralThreadNum() > 0 ?
                Executors.newFixedThreadPool(config.getGeneralThreadNum()) : 
                    Executors.newCachedThreadPool());
            
            GlobalVars.scheduleExecutorMap.put(config, 
                    config.getScheduleThreadNum() > 0 ? 
                            Executors.newScheduledThreadPool(config.getScheduleThreadNum()) :
                                Executors.newScheduledThreadPool(0));
        }        
        return this;
    }

    /* (non-Javadoc)
     * @see me.huzorro.gateway.GlobalVarsInitialize#bootstrapInitialize()
     */
    @Override
    public GlobalVarsInitialize clientBootstrapInitialize() {
        for(SessionConfig config : GlobalVars.upstreamSessionConfigMap.values()) {
            GlobalVars.clientBootstrapMap.put(config, new ClientBootstrap());
        }
        for(SessionConfig config : GlobalVars.downstreamSessionConfigMap.values()) {
            GlobalVars.clientBootstrapMap.put(config, new ClientBootstrap());
        }
        for(SessionConfig config : GlobalVars.duplexSessionConfigMap.values()) {
            GlobalVars.clientBootstrapMap.put(config, new ClientBootstrap());
        }
        return this;
    }

    /* (non-Javadoc)
     * @see me.huzorro.gateway.GlobalVarsInitialize#serverBootstrapInitialize()
     */
    @Override
    public GlobalVarsInitialize serverBootstrapInitialize() {
        for(SessionConfig config : GlobalVars.upstreamSessionConfigMap.values()) {
            GlobalVars.serverBootstrapMap.put(config, new ServerBootstrap());
        }
        for(SessionConfig config : GlobalVars.downstreamSessionConfigMap.values()) {
            GlobalVars.serverBootstrapMap.put(config, new ServerBootstrap());
        }
        for(SessionConfig config : GlobalVars.duplexSessionConfigMap.values()) {
            GlobalVars.serverBootstrapMap.put(config, new ServerBootstrap());
        }
        return this;
    }

}

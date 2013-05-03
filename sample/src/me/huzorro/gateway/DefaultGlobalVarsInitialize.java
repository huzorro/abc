package me.huzorro.gateway;

import java.util.Map;
import java.util.concurrent.Executors;

import org.apache.commons.configuration.ConfigurationException;
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
    public GlobalVarsInitialize sessionConfigInitialize() throws ConfigurationException, InstantiationException, IllegalAccessException {
        new UpstreamSessionConfigMapFactory<Map<String,SessionConfig>, UpstreamSessionConfig>(GlobalVars.config, GlobalVars.upstreamSessionConfigMap).create();
        new DownstreamSessionConfigMapFactory<Map<String,SessionConfig>, DownstreamSessionConfig>(GlobalVars.config, GlobalVars.downstreamSessionConfigMap).create();
        new DuplexstreamSessionConfigMapFactory<Map<String,SessionConfig>, DuplexstreamSessionConfig>(GlobalVars.config, GlobalVars.duplexSessionConfigMap).create();
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
			GlobalVars.deliverMsgQueueMap.put(
					config,
					new BdbQueueMap<Long, MessageFuture>(config
							.getDeliverQueuePathHome(), config
							.getDeliverQueueName()));
        }
        for(SessionConfig config : GlobalVars.downstreamSessionConfigMap.values()) {
        	
			GlobalVars.requestMsgQueueMap.put(
					config,
					new BdbQueueMap<Long, MessageFuture>(config
							.getRequestQueuePathHome(), config
							.getRequestQueueName()));
			GlobalVars.responseMsgQueueMap.put(
					config,
					new BdbQueueMap<Long, MessageFuture>(config
							.getResponseQueuePathHome(), config
							.getResponseQueueName()));
        }
        for(SessionConfig config : GlobalVars.duplexSessionConfigMap.values()) {
        	
			GlobalVars.deliverMsgQueueMap.put(
					config,
					new BdbQueueMap<Long, MessageFuture>(config
							.getDeliverQueuePathHome(), config
							.getDeliverQueueName()));
			GlobalVars.requestMsgQueueMap.put(
					config,
					new BdbQueueMap<Long, MessageFuture>(config
							.getRequestQueuePathHome(), config
							.getRequestQueueName()));
			GlobalVars.responseMsgQueueMap.put(
					config,
					new BdbQueueMap<Long, MessageFuture>(config
							.getResponseQueuePathHome(), config
							.getResponseQueueName()));	
			
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

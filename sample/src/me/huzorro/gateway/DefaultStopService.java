/**
 * 
 */
package me.huzorro.gateway;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;

import net.xeoh.plugins.base.util.PluginManagerUtil;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author huzorro(huzorro@gmail.com)
 *
 */
public class DefaultStopService implements Service {
	private static final Logger logger = LoggerFactory.getLogger(DefaultStopService.class);
	private List<SessionConfig> runningList;
	private Map<String, Map<String, SessionConfig>> configMap;
	private Map<SessionConfig, SessionPool> sessionPoolMap;
	private Map<SessionConfig, ExecutorService> executorServiceMap;
	private Map<SessionConfig, ScheduledExecutorService> scheduleExecutorMap;
	private Map<SessionConfig, ScheduledExecutorService> externalScheduleExecutorMap;
	private Map<SessionConfig, ClientBootstrap> clientBootstrapMap;
	private Map<SessionConfig, ServerBootstrap> serverBootstrapMap;
	private Map<SessionConfig, PluginManagerUtil> pluginManagerUtilMap;


	/**
	 * @param runningList
	 * @param configMap
	 * @param sessionPoolMap
	 * @param executorServiceMap
	 * @param scheduleExecutorMap
	 * @param externalScheduleExecutorMap
	 * @param clientBootstrapMap
	 * @param serverBootstrapMap
	 */
	public DefaultStopService(
			List<SessionConfig> runningList,
			Map<String, Map<String, SessionConfig>> configMap,
			Map<SessionConfig, SessionPool> sessionPoolMap,
			Map<SessionConfig, ExecutorService> executorServiceMap,
			Map<SessionConfig, ScheduledExecutorService> scheduleExecutorMap,
			Map<SessionConfig, ScheduledExecutorService> externalScheduleExecutorMap,
			Map<SessionConfig, ClientBootstrap> clientBootstrapMap,
			Map<SessionConfig, ServerBootstrap> serverBootstrapMap,
			Map<SessionConfig, PluginManagerUtil> pluginManagerUtilMap) {
		this.runningList = runningList;
		this.configMap = configMap;
		this.sessionPoolMap = sessionPoolMap;
		this.executorServiceMap = executorServiceMap;
		this.scheduleExecutorMap = scheduleExecutorMap;
		this.externalScheduleExecutorMap = externalScheduleExecutorMap;
		this.clientBootstrapMap = clientBootstrapMap;
		this.serverBootstrapMap = serverBootstrapMap;
		this.pluginManagerUtilMap = pluginManagerUtilMap;
	}
	
	public List<String> stop() {
		return stop(null);
	}
	public List<String> stop(List<String> configList) {
		List<String> resultList = new ArrayList<String>();
		for(Map<String, SessionConfig> map : configMap.values()) {
			for(SessionConfig config : map.values()) {
				if(null != configList && !configList.contains(config.getChannelIds())) continue;
				if(runningList.contains(config)) {
					try {
						resultList.add(externalScheduleExecutorShutdown(config));
						resultList.add(sessionPoolShutdown(config));
						resultList.add(scheduleExecutorShutdown(config));
						resultList.add(pluginManagerUtilShutdown(config));
						resultList.add(executorServiceShutdown(config));
						resultList.add(clientBootstrapShutdown(config));
						resultList.add(serverBootstrapShutdown(config));
						runningList.remove(config);
					} catch (Exception e) {
						logger.error("stop service [FAILS]", e.getCause());
					}
				}
			}
		}		
		return resultList;
	}
	
	String externalScheduleExecutorShutdown(SessionConfig config) throws Exception{
		String result = String.format("%1$s [%2$s->%3$s]",
				"externalScheduleExecutor contains poolwatch shutdown",
				config.getAttPreffix(), config.getChannelIds());
		try {			
			externalScheduleExecutorMap.get(config).shutdownNow(); 
			result = String.format("%1$s [%2$s]",result, "OK");
			logger.info("{}", result);
		} catch (Exception e) {
			result = String.format("%1$s [%2$s]",result, "FAILS");
			logger.error("{}", result, e.getCause());
			throw e;
		}
		return result;
	}
	String sessionPoolShutdown(SessionConfig config) throws Exception {
		String result = String.format("%1$s [%2$s->%3$s]",
				"close the session of pool",
				config.getAttPreffix(), config.getChannelIds());
		try {			
			sessionPoolMap.get(config).close();
			result = String.format("%1$s [%2$s]",result, "OK");
			logger.info("{}", result);
		} catch (Exception e) {
			result = String.format("%1$s [%2$s]",result, "FAILS");
			logger.error("{}", result, e.getCause());
			throw e;
		}
		return result;		
	}
	String scheduleExecutorShutdown(SessionConfig config) throws Exception{
		String result = String.format("%1$s [%2$s->%3$s]",
				"scheduleExecutorMap contains resubmit shutdown",
				config.getAttPreffix(), config.getChannelIds());
		try {			
			scheduleExecutorMap.get(config).shutdownNow(); 
			result = String.format("%1$s [%2$s]",result, "OK");
			logger.info("{}", result);
		} catch (Exception e) {
			result = String.format("%1$s [%2$s]",result, "FAILS");
			logger.error("{}", result, e.getCause());
			throw e;
		}
		return result;		
	}
	
	String executorServiceShutdown(SessionConfig config) throws Exception{
		String result = String.format("%1$s [%2$s->%3$s]",
				"executorServiceMap contains handle the task shutdown",
				config.getAttPreffix(), config.getChannelIds());
		try {
			executorServiceMap.get(config).shutdownNow();
			result = String.format("%1$s [%2$s]",result, "OK");
			logger.info("{}", result);
		} catch (Exception e) {
			result = String.format("%1$s [%2$s]",result, "FAILS");
			logger.error("{}", result, e.getCause());
			throw e;
		}
		return result;
	}
	String clientBootstrapShutdown(SessionConfig config) throws Exception {
		String result = String.format("%1$s [%2$s->%3$s]",
				"clientBootstrapMap shutdown",
				config.getAttPreffix(), config.getChannelIds());
		try {
			if(clientBootstrapMap.get(config) != null) clientBootstrapMap.get(config).shutdown();
			result = String.format("%1$s [%2$s]",result, "OK");
			logger.info("{}", result);
		} catch (Exception e) {
			result = String.format("%1$s [%2$s]",result, "FAILS");
			logger.error("{}", result, e.getCause());
			throw e;
		}
		return result;
	}
	String serverBootstrapShutdown(SessionConfig config) throws Exception {
		String result = String.format("%1$s [%2$s->%3$s]",
				"serverBootstrapMap shutdown",
				config.getAttPreffix(), config.getChannelIds());
		try {
			if(serverBootstrapMap.get(config) != null) serverBootstrapMap.get(config).shutdown();
			result = String.format("%1$s [%2$s]",result, "OK");
			logger.info("{}", result);
		} catch (Exception e) {
			result = String.format("%1$s [%2$s]",result, "FAILS");
			logger.error("{}", result, e.getCause());
			throw e;
		}
		return result;
	}
	String pluginManagerUtilShutdown(SessionConfig config) throws Exception {
		String result = String.format("%1$s [%2$s->%3$s]",
				"pluginManagerUtil shutdown",
				config.getAttPreffix(), config.getChannelIds());
		try {
			if(pluginManagerUtilMap.get(config) != null) pluginManagerUtilMap.get(config).shutdown();
			result = String.format("%1$s [%2$s]",result, "OK");
			logger.info("{}", result);
		} catch (Exception e) {
			result = String.format("%1$s [%2$s]",result, "FAILS");
			logger.error("{}", result, e.getCause());
			throw e;
		}
		return result;		
	}
	@Override
	public void run() {
	}

	@Override
	public void process() throws Exception {
	}

}

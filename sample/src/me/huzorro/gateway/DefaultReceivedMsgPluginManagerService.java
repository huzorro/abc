/**
 * 
 */
package me.huzorro.gateway;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

import net.xeoh.plugins.base.util.PluginManagerUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author huzorro(huzorro@gmail.com)
 * 
 */
public class DefaultReceivedMsgPluginManagerService implements Service {
	private Logger logger = LoggerFactory
			.getLogger(DefaultReceivedMsgPluginManagerService.class);
	private Map<String, SessionConfig> configMap;
	private Map<Object, BdbQueueMap<Long, QFuture<Message>>> receiveMsgQueueMap;
	private Map<SessionConfig, ExecutorService> executorServiceMap;
	private Map<SessionConfig, PluginManagerUtil> pluginManagerUtilMap;
	private List<String> configList;

	public DefaultReceivedMsgPluginManagerService(
			Map<String, SessionConfig> configMap,
			Map<Object, BdbQueueMap<Long, QFuture<Message>>> receiveMsgQueueMap,
			Map<SessionConfig, ExecutorService> executorServiceMap,
			Map<SessionConfig, PluginManagerUtil> pluginManagerUtilMap) {
		this(configMap, receiveMsgQueueMap, executorServiceMap,
				pluginManagerUtilMap, null);
	}

	public DefaultReceivedMsgPluginManagerService(
			Map<String, SessionConfig> configMap,
			Map<Object, BdbQueueMap<Long, QFuture<Message>>> receiveMsgQueueMap,
			Map<SessionConfig, ExecutorService> executorServiceMap,
			Map<SessionConfig, PluginManagerUtil> pluginManagerUtilMap,
			List<String> configList) {
		this.configMap = configMap;
		this.receiveMsgQueueMap = receiveMsgQueueMap;
		this.executorServiceMap = executorServiceMap;
		this.pluginManagerUtilMap = pluginManagerUtilMap;
		this.configList = configList;
	}

	@Override
	public void run() {
		try {
			process();
		} catch (Exception e) {
			logger.error("deliver the message failed to run plug-management services", e.getCause());
		}
	}

	@Override
	public void process() throws Exception {
        for(final SessionConfig config : configMap.values()) {
        	if(configList != null && !configList.contains(config.getChannelIds())) continue;
        	create(config);
        }	
	}

	protected void create(SessionConfig config) {
		Collection<ReceivedMessageServicePlugin> plugins = pluginManagerUtilMap
				.get(config).getPlugins(ReceivedMessageServicePlugin.class);
		for(ReceivedMessageServicePlugin plugin : plugins) {
			plugin.handler(receiveMsgQueueMap.get(config), executorServiceMap.get(config));
		}
	}
}

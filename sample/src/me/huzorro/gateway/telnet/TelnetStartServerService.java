/**
 * 
 */
package me.huzorro.gateway.telnet;

import java.util.ArrayList;
import java.util.List;

import me.huzorro.gateway.DefaultShutdownHook;
import me.huzorro.gateway.GlobalVars;
import me.huzorro.gateway.ServerServices;
import me.huzorro.gateway.Service;

import org.apache.commons.configuration.CombinedConfiguration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author huzorro(huzorro@gmail.com)
 *
 */
public class TelnetStartServerService implements Service {
	private final Logger logger = LoggerFactory.getLogger(TelnetStartServerService.class);
	private CombinedConfiguration  config;
	private List<ServerServices> services = new ArrayList<ServerServices>();
	private String configName;
	private String configKey;
	/**
	 * 
	 */
	public TelnetStartServerService(CombinedConfiguration config) {
		this(config, "service", "telnet.service");
	}
	
	
	/**
	 * @param config
	 * @param configName
	 * @param configKey
	 */
	public TelnetStartServerService(CombinedConfiguration config,
			String configName, String configKey) {
		this.config = config;
		this.configName = configName;
		this.configKey = configKey;
		configInit();
		Runtime.getRuntime().addShutdownHook(
				new Thread(
						new DefaultShutdownHook(
						GlobalVars.upstreamSessionConfigMap,
						GlobalVars.sessionPoolMap,
						GlobalVars.executorServiceMap,
						GlobalVars.scheduleExecutorMap,
						GlobalVars.externalScheduleExecutorMap,
						GlobalVars.clientBootstrapMap,
						GlobalVars.serverBootstrapMap,
						GlobalVars.pluginManagerUtilMap)));
		Runtime.getRuntime().addShutdownHook(
				new Thread(
						new DefaultShutdownHook(
						GlobalVars.downstreamSessionConfigMap,
						GlobalVars.sessionPoolMap,
						GlobalVars.executorServiceMap,
						GlobalVars.scheduleExecutorMap,
						GlobalVars.externalScheduleExecutorMap,
						GlobalVars.clientBootstrapMap,
						GlobalVars.serverBootstrapMap,
						GlobalVars.pluginManagerUtilMap)));
		Runtime.getRuntime().addShutdownHook(
				new Thread(
						new DefaultShutdownHook(
						GlobalVars.duplexSessionConfigMap,
						GlobalVars.sessionPoolMap,
						GlobalVars.executorServiceMap,
						GlobalVars.scheduleExecutorMap,
						GlobalVars.externalScheduleExecutorMap,
						GlobalVars.clientBootstrapMap,
						GlobalVars.serverBootstrapMap,
						GlobalVars.pluginManagerUtilMap)));
	}

	@SuppressWarnings("unchecked")
	private void configInit() {
		PropertiesConfiguration propConfig = (PropertiesConfiguration) config.getConfiguration(configName);
		List<Object> list = propConfig.getList(configKey);
		for(Object service : list) {
			try {
				Class<ServerServices> classz = (Class<ServerServices>) Class.forName((String) service);
				services.add(classz.newInstance());
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				logger.error("start telnetServer to init services fails", e.getCause());
				Runtime.getRuntime().exit(-1);
			} 
		}
	}
	public List<String> duplexstreamService(List<String> configList) {
		List<String> resultList = new ArrayList<String>();
		for(ServerServices service : services) {
			try {
				service.duplexstreamGlobalVarsInit(configList);
				resultList.add("telnet duplex stream globalVars Initialize [OK]");
				logger.info("telnet duplex stream globalVars Initialize [OK]");
			} catch (Exception e) {
				resultList.add("telnet duplex stream globalVars Initialize [FAILS]");
				logger.error("telnet duplex stream globalVars Initialize [FAILS]", e.getCause());
			} 
			try {
				service.duplexstreamServiceInit(configList).process();
				resultList.add("telnet duplex stream service to init [OK]");
				logger.info("telnet duplex stream service to init [OK]");
			} catch (Exception e) {
				resultList.add("telnet duplex stream service to init [FAILS]");
				logger.error("telnet duplex stream service to init [FAILS]", e.getCause());
			}
		}
		return resultList;
	}
	public List<String> duplexstreamService() {
		return duplexstreamService(null);
	}	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see me.huzorro.gateway.Service#process()
	 */
	@Override
	public void process() throws Exception {
		// TODO Auto-generated method stub

	}
	
	public static void main(String[] args) {
		new TelnetStartServerService(GlobalVars.config).duplexstreamService();
	}
}

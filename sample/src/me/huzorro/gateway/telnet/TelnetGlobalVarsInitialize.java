/**
 * 
 */
package me.huzorro.gateway.telnet;

import java.util.List;

import me.huzorro.gateway.DefaultGlobalVarsInitialize;
import me.huzorro.gateway.GlobalVars;
import me.huzorro.gateway.GlobalVarsInitialize;

/**
 * @author huzorro(huzorro@gmail.com)
 *
 */
public class TelnetGlobalVarsInitialize extends DefaultGlobalVarsInitialize {

	public TelnetGlobalVarsInitialize() {
		super("telnetsession");
	}
	/**
	 * @param configName
	 */
	public TelnetGlobalVarsInitialize(String configName) {
		super(configName);
	}
	
	@Override
	public GlobalVarsInitialize duplexstreamSessionConfigInitialize(
			List<String> configList) throws Exception {
		new TelnetSessionConfigMapFactory<>(GlobalVars.config, GlobalVars.duplexSessionConfigMap, configList).create();
		return this;
	}
	
	
	
	
}

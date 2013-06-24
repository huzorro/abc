package me.huzorro.gateway;

import java.util.List;
import java.util.Map;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public class CmppGlobalVarsInitialize extends
        DefaultGlobalVarsInitialize {
	
    public CmppGlobalVarsInitialize() {
    	super("cmppsession");
	}
    
	public CmppGlobalVarsInitialize(String configName) {
		super(configName);
	}

	@Override
	public GlobalVarsInitialize upstreamSessionConfigInitialize(
			List<String> configList) throws Exception {
		new CmppUpstreamSessionConfigMapFactory<Map<String, Map<String,SessionConfig>>, SessionConfig>(GlobalVars.config, GlobalVars.upstreamSessionConfigMap, configList).create();
		return this; 
	}

	@Override
	public GlobalVarsInitialize downstreamSessionConfigInitialize(
			List<String> configList) throws Exception {
		new CmppDownstreamSessionConfigMapFactory<Map<String, Map<String,SessionConfig>>, SessionConfig>(GlobalVars.config, GlobalVars.downstreamSessionConfigMap, configList).create();
		return this; 
	}

	@Override
	public GlobalVarsInitialize duplexstreamSessionConfigInitialize(
			List<String> configList) throws Exception {
		new CmppDuplexstreamSessionConfigMapFactory<Map<String, Map<String,SessionConfig>>, SessionConfig>(GlobalVars.config, GlobalVars.duplexSessionConfigMap, configList).create();
		return this; 
	}

}

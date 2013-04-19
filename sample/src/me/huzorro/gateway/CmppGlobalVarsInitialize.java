package me.huzorro.gateway;

import java.util.Map;

import org.apache.commons.configuration.ConfigurationException;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public class CmppGlobalVarsInitialize extends
        DefaultGlobalVarsInitialize {
    @Override
    public GlobalVarsInitialize sessionConfigInitialize() throws ConfigurationException, InstantiationException, IllegalAccessException { 
        new CmppUpstreamSessionConfigMapFactory<Map<String,SessionConfig>, CmppUpstreamSessionConfig>(GlobalVars.config, GlobalVars.upstreamSessionConfigMap).create();
        new CmppDownstreamSessionConfigMapFactory<Map<String,SessionConfig>, CmppDownstreamSessionConfig>(GlobalVars.config, GlobalVars.downstreamSessionConfigMap).create();
        new CmppDuplexstreamSessionConfigMapFactory<Map<String,SessionConfig>, CmppDuplexstreamSessionConfig>(GlobalVars.config, GlobalVars.duplexSessionConfigMap).create();
        return this;
    }
}

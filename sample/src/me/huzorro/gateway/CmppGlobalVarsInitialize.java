package me.huzorro.gateway;

import java.util.Map;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public class CmppGlobalVarsInitialize extends
        DefaultGlobalVarsInitialize {
    
    @Override
    public GlobalVarsInitialize sessionConfigInitialize() {
        new CmppUpstreamSessionConfigMapFactory<Map<String,SessionConfig>, CmppUpstreamSessionConfig>(GlobalVars.configBuilder, GlobalVars.upstreamSessionConfigMap);
        new CmppDownstreamSessionConfigMapFactory<Map<String,SessionConfig>, CmppDownstreamSessionConfig>(GlobalVars.configBuilder, GlobalVars.downstreamSessionConfigMap);
        new CmppDuplexstreamSessionConfigMapFactory<Map<String,SessionConfig>, CmppDuplexstreamSessionConfig>(GlobalVars.configBuilder, GlobalVars.duplexSessionConfigMap);
        return this;
    }
}

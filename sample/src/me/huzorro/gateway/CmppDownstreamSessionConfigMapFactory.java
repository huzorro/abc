package me.huzorro.gateway;

import java.util.Map;

import org.apache.commons.configuration.DefaultConfigurationBuilder;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public class CmppDownstreamSessionConfigMapFactory<T extends Map<String, SessionConfig>, E extends CmppDownstreamSessionConfig> extends
        DownstreamSessionConfigMapFactory<T, E> {
    
    /**
     * @param configurationBuilder
     * @param sessionConfigMap
     */
    @SuppressWarnings("unchecked")
    public CmppDownstreamSessionConfigMapFactory(
            DefaultConfigurationBuilder configurationBuilder,
            Map<String, SessionConfig> sessionConfigMap) {
            this(configurationBuilder, sessionConfigMap, "downstream", (Class<E>) CmppDownstreamSessionConfig.class);
    }
    /**
     * @param configurationBuilder
     * @param sessionConfigMap
     * @param sessionType
     * @param sessionConfig
     */
    public CmppDownstreamSessionConfigMapFactory(
            DefaultConfigurationBuilder configurationBuilder,
            Map<String, SessionConfig> sessionConfigMap, String sessionType,
            Class<E> sessionConfig) {
        super(configurationBuilder, sessionConfigMap, sessionType, sessionConfig);
    }

}

package me.huzorro.gateway;

import java.util.Map;

import org.apache.commons.configuration.CombinedConfiguration;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public class DownstreamSessionConfigMapFactory<T extends Map<String, SessionConfig>, E extends DownstreamSessionConfig> extends
        SessionConfigMapFactory<T, E> {
    
    @SuppressWarnings("unchecked")
    public DownstreamSessionConfigMapFactory(
            CombinedConfiguration configurationBuilder,
            Map<String, SessionConfig> sessionConfigMap) {
        this(configurationBuilder, sessionConfigMap, "downstream", (Class<E>) DownstreamSessionConfig.class);
    }
    
    /**
     * @param configurationBuilder
     * @param sessionConfigMap
     * @param sessionType
     * @param sessionConfig
     */
    public DownstreamSessionConfigMapFactory(
            CombinedConfiguration configurationBuilder,
            Map<String, SessionConfig> sessionConfigMap, String sessionType,
            Class<E> sessionConfig) {
        super(configurationBuilder, sessionConfigMap, sessionType, sessionConfig);
    }

}

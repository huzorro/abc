package me.huzorro.gateway;

import java.util.Map;

import org.apache.commons.configuration.CombinedConfiguration;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public class DuplexstreamSessionConfigMapFactory<T extends Map<String, SessionConfig>, E extends DuplexstreamSessionConfig> extends
        SessionConfigMapFactory<T, E> {
    @SuppressWarnings("unchecked")
    public DuplexstreamSessionConfigMapFactory(
            CombinedConfiguration configurationBuilder,
            Map<String, SessionConfig> sessionConfigMap) {
        this(configurationBuilder, sessionConfigMap, "duplexstream", (Class<E>) DuplexstreamSessionConfig.class);
    }
    /**
     * @param configurationBuilder
     * @param sessionConfigMap
     * @param sessionType
     * @param sessionConfig
     */
    public DuplexstreamSessionConfigMapFactory(
            CombinedConfiguration configurationBuilder,
            Map<String, SessionConfig> sessionConfigMap, String sessionType,
            Class<E> sessionConfig) {
        super(configurationBuilder, sessionConfigMap, sessionType, sessionConfig);
    }

}

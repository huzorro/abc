package me.huzorro.gateway;

import java.util.Map;

import org.apache.commons.configuration.DefaultConfigurationBuilder;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public class CmppDuplexstreamSessionConfigMapFactory<T extends Map<String, SessionConfig>, E extends DuplexstreamSessionConfig> extends
        DuplexstreamSessionConfigMapFactory<T, E> {

    /**
     * @param configurationBuilder
     * @param sessionConfigMap
     */
    @SuppressWarnings("unchecked")
    public CmppDuplexstreamSessionConfigMapFactory(
            DefaultConfigurationBuilder configurationBuilder,
            Map<String, SessionConfig> sessionConfigMap) {
        this(configurationBuilder, sessionConfigMap, "duplexstream", (Class<E>) CmppDuplexstreamSessionConfig.class);
    }

    /**
     * @param configurationBuilder
     * @param sessionConfigMap
     * @param sessionType
     * @param sessionConfig
     */
    public CmppDuplexstreamSessionConfigMapFactory(
            DefaultConfigurationBuilder configurationBuilder,
            Map<String, SessionConfig> sessionConfigMap, String sessionType,
            Class<E> sessionConfig) {
        super(configurationBuilder, sessionConfigMap, sessionType, sessionConfig);
    }

}

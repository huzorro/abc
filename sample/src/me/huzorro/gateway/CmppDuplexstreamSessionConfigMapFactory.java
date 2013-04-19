package me.huzorro.gateway;

import java.util.Map;

import org.apache.commons.configuration.CombinedConfiguration;

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
            CombinedConfiguration configurationBuilder,
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
            CombinedConfiguration configurationBuilder,
            Map<String, SessionConfig> sessionConfigMap, String sessionType,
            Class<E> sessionConfig) {
        super(configurationBuilder, sessionConfigMap, sessionType, sessionConfig);
    }

}

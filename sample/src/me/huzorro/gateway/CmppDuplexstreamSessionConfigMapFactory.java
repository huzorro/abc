package me.huzorro.gateway;

import java.util.List;
import java.util.Map;

import org.apache.commons.configuration.CombinedConfiguration;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public class CmppDuplexstreamSessionConfigMapFactory<T extends Map<String, Map<String, E>>, E extends SessionConfig> extends
        DuplexstreamSessionConfigMapFactory<T, E> {

    /**
     * @param configurationBuilder
     * @param sessionConfigMap
     */
    @SuppressWarnings("unchecked")
    public CmppDuplexstreamSessionConfigMapFactory(
            CombinedConfiguration configurationBuilder,
            T sessionConfigMap,
            List<String> configList) {
        this(configurationBuilder, sessionConfigMap, "duplexstream", "cmppsession", (Class<E>) CmppDuplexstreamSessionConfig.class, configList);
    }

    /**
     * @param configurationBuilder
     * @param sessionConfigMap
     * @param sessionType
     * @param sessionConfig
     */
    public CmppDuplexstreamSessionConfigMapFactory(
            CombinedConfiguration configurationBuilder,
            T sessionConfigMap, 
            String sessionType,
            String configName,
            Class<E> sessionConfig,
            List<String> configList) {
        super(configurationBuilder, sessionConfigMap, sessionType, configName, sessionConfig, configList);
    }

}

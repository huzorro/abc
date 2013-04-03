package me.huzorro.gateway;

import java.util.Map;

import org.apache.commons.configuration.DefaultConfigurationBuilder;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 * @param <T>
 */
public class UpstreamSessionConfigMapFactory<T extends Map<String, SessionConfig>, E extends UpstreamSessionConfig> extends
        SessionConfigMapFactory<T, E> {
    @SuppressWarnings("unchecked")
    public UpstreamSessionConfigMapFactory(
            DefaultConfigurationBuilder configurationBuilder,
            Map<String, SessionConfig> sessionConfigMap) {
        this(configurationBuilder, sessionConfigMap, "upstream", (Class<E>) UpstreamSessionConfig.class);
    }
    /**
     * @param configurationBuilder
     * @param sessionConfigMap
     * @param sessionType
     * @param classz
     */
    public UpstreamSessionConfigMapFactory(
            DefaultConfigurationBuilder configurationBuilder,
            Map<String, SessionConfig> sessionConfigMap, String sessionType,
            Class<E> classz) {
        super(configurationBuilder, sessionConfigMap, sessionType, classz);
    }

}

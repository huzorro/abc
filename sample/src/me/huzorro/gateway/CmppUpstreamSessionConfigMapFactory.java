package me.huzorro.gateway;

import java.util.Map;

import org.apache.commons.configuration.CombinedConfiguration;
import org.apache.commons.configuration.DefaultConfigurationBuilder;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public class CmppUpstreamSessionConfigMapFactory<T extends Map<String, SessionConfig>, E extends CmppUpstreamSessionConfig> extends
        UpstreamSessionConfigMapFactory<T, E> {
    @SuppressWarnings("unchecked")
    public CmppUpstreamSessionConfigMapFactory(
            CombinedConfiguration configurationBuilder,
            Map<String, SessionConfig> sessionConfigMap) {
            this(configurationBuilder, sessionConfigMap, "upstream", (Class<E>) CmppUpstreamSessionConfig.class);
    }
    /**
     * @param configurationBuilder
     * @param sessionConfigMap
     * @param sessionType
     * @param classz
     */
    public CmppUpstreamSessionConfigMapFactory(
            CombinedConfiguration configurationBuilder,
            Map<String, SessionConfig> sessionConfigMap, String sessionType,
            Class<E> classz) {
        super(configurationBuilder, sessionConfigMap, sessionType, classz);
    }


}

package me.huzorro.gateway;

import java.util.List;
import java.util.Map;

import org.apache.commons.configuration.CombinedConfiguration;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public class CmppUpstreamSessionConfigMapFactory<T extends Map<String, Map<String, E>>, E extends SessionConfig> extends
        UpstreamSessionConfigMapFactory<T, E> {
    @SuppressWarnings("unchecked")
	public CmppUpstreamSessionConfigMapFactory(
            CombinedConfiguration configurationBuilder,
            T sessionConfigMap,
            List<String> configList) {
            this(configurationBuilder, sessionConfigMap, "upstream", "cmppsession", (Class<E>) CmppUpstreamSessionConfig.class, configList);
    }
    /**
     * @param configurationBuilder
     * @param sessionConfigMap
     * @param sessionType
     * @param classz
     */
    public CmppUpstreamSessionConfigMapFactory(
            CombinedConfiguration configurationBuilder,
            T sessionConfigMap, 
            String sessionType,
            String configName,
            Class<E> classz,
            List<String> configList) {
        super(configurationBuilder, sessionConfigMap, sessionType, configName, classz, configList);
    }


}

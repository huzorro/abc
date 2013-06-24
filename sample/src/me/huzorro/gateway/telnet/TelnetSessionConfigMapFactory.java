/**
 * 
 */
package me.huzorro.gateway.telnet;

import java.util.List;
import java.util.Map;

import me.huzorro.gateway.DuplexstreamSessionConfigMapFactory;
import me.huzorro.gateway.SessionConfig;

import org.apache.commons.configuration.CombinedConfiguration;

/**
 * @author huzorro(huzorro@gmail.com)
 *
 */
public class TelnetSessionConfigMapFactory<T extends Map<String, Map<String, E>>, E extends SessionConfig> extends
		DuplexstreamSessionConfigMapFactory<T, E> {

	@SuppressWarnings("unchecked")
	public TelnetSessionConfigMapFactory(
			CombinedConfiguration configurationBuilder, 
			T sessionConfigMap,
			List<String> configList) {
		this(configurationBuilder, sessionConfigMap,  "duplexstream", "telnetsession", (Class<E>) TelnetSessionConfig.class, configList);
	}

	/**
	 * @param configurationBuilder
	 * @param sessionConfigMap
	 * @param sessionType
	 * @param configName
	 * @param sessionConfig
	 */
	public TelnetSessionConfigMapFactory(
			CombinedConfiguration configurationBuilder, 
			T sessionConfigMap,
			String sessionType, 
			String configName, 
			Class<E> sessionConfig,
			List<String> configList) {
		super(configurationBuilder, sessionConfigMap, sessionType, configName,
				sessionConfig, configList);
	}

}

/**
 * 
 */
package me.huzorro.gateway;

import me.huzorro.gateway.DefaultServerSessionConfigFactory;
import me.huzorro.gateway.SessionConfig;

/**
 * 
 * @author huzorro(huzorro@gmail.com)
 *
 * @param <T>
 */
public class DuplexstreamServerSessionConfigFactory<T> extends
		DefaultServerSessionConfigFactory<T> {

	/**
	 * 
	 * @param defaultSessionConfig
	 */
	public DuplexstreamServerSessionConfigFactory(SessionConfig defaultSessionConfig) {
		super(defaultSessionConfig);
	}

}

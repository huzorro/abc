/**
 * 
 */
package me.huzorro.gateway;

/**
 * 
 * @author huzorro(huzorro@gmail.com)
 *
 * @param <T>
 */
public class UpstreamServerSessionConfigFactory<T> extends
		DefaultServerSessionConfigFactory<T> {
	/**
	 * 
	 * @param defaultSessionConfig
	 */
	public UpstreamServerSessionConfigFactory(SessionConfig defaultSessionConfig) {
		super(defaultSessionConfig);
	}

}

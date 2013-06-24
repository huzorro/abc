/**
 * 
 */
package me.huzorro.gateway.cmpp.server;

import me.huzorro.gateway.SessionConfig;
import me.huzorro.gateway.UpstreamServerSessionConfigFactory;

/**
 * 
 * @author huzorro(huzorro@gmail.com)
 *
 * @param <T>
 */
public class CmppUpstreamServerSessionConfigFactory<T> extends
		UpstreamServerSessionConfigFactory<T> {

	/**
	 * 
	 */
	public CmppUpstreamServerSessionConfigFactory(SessionConfig defaultSessionConfig) {
		super(defaultSessionConfig);
	}

}

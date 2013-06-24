/**
 * 
 */
package me.huzorro.gateway.cmpp.server;

import me.huzorro.gateway.DownstreamServerSessionConfigFactory;
import me.huzorro.gateway.SessionConfig;

/**
 * 
 * @author huzorro(huzorro@gmail.com)
 *
 * @param <T>
 */
public class CmppDownstreamServerSessionConfigFactory<T> extends
		DownstreamServerSessionConfigFactory<T> {

	/**
	 * 
	 */
	public CmppDownstreamServerSessionConfigFactory(SessionConfig defaultSessionConfig) {
		super(defaultSessionConfig);
	}

}

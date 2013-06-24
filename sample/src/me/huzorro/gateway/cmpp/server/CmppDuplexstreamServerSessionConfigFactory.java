/**
 * 
 */
package me.huzorro.gateway.cmpp.server;

import me.huzorro.gateway.DuplexstreamServerSessionConfigFactory;
import me.huzorro.gateway.SessionConfig;

/**
 * 
 * @author huzorro(huzorro@gmail.com)
 *
 * @param <T>
 */
public class CmppDuplexstreamServerSessionConfigFactory<T> extends
		DuplexstreamServerSessionConfigFactory<T> {

	/**
	 * 
	 */
	public CmppDuplexstreamServerSessionConfigFactory(SessionConfig defaultSessionConfig) {
		super(defaultSessionConfig);
	}

}

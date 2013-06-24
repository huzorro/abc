/**
 * 
 */
package me.huzorro.gateway.telnet;

import me.huzorro.gateway.DuplexstreamServerSessionConfigFactory;
import me.huzorro.gateway.SessionConfig;

/**
 * @author huzorro(huzorro@gmail.com)
 *
 */
public class TelnetServerSessionConfigFactory<T> extends
		DuplexstreamServerSessionConfigFactory<T> {

	public TelnetServerSessionConfigFactory(SessionConfig defaultSessionConfig) {
		super(defaultSessionConfig);
	}

}

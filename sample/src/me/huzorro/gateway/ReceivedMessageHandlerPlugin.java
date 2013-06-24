/**
 * 
 */
package me.huzorro.gateway;

import net.xeoh.plugins.base.Plugin;

/**
 * @author huzorro(huzorro@gmail.com)
 *
 */
public interface ReceivedMessageHandlerPlugin extends Plugin {
	public boolean received(Message message) throws Exception;
}

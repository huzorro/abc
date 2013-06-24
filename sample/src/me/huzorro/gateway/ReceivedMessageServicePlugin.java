/**
 * 
 */
package me.huzorro.gateway;

import java.util.concurrent.ExecutorService;

import net.xeoh.plugins.base.Plugin;

/**
 * @author huzorro(huzorro@gmail.com)
 *
 */
public interface ReceivedMessageServicePlugin extends Plugin {
	
	public void handler(BdbQueueMap<Long, QFuture<Message>> queue, ExecutorService executor);
}

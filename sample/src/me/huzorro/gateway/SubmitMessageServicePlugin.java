/**
 * 
 */
package me.huzorro.gateway;

import java.util.Map;
import java.util.concurrent.ExecutorService;

import net.xeoh.plugins.base.Plugin;

/**
 * 
 * @author huzorro(huzorro@gmail.com)
 *
 */
public interface SubmitMessageServicePlugin extends Plugin {
	/**
	 * 
	 * @param queueMap
	 * @param executor
	 * @param configMap
	 * @param config
	 */
	public void handler(
			Map<Object, BdbQueueMap<Long, QFuture<Message>>> queueMap,
			ExecutorService executor,
			Map<String, SessionConfig> configMap,
			SessionConfig config);
	/**
	 * 
	 * @param message
	 * @throws Exception
	 */
	public void submit(Message message) throws Exception;
}

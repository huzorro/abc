/**
 * 
 */
package me.huzorro.gateway;

import java.util.List;

/**
 * @author huzorro(huzorro@gmail.com)
 *
 */
public interface StartServices extends Service{
	public List<String> duplexstreamService(List<String> configList);
	public List<String> duplexstreamService() ;
	public List<String> upstreamService(List<String> configList);
	public List<String> upstreamService();
	public List<String> downstreamService(List<String> configList);
	public List<String> downstreamService();
}

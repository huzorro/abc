/**
 * 
 */
package me.huzorro.gateway;

/**
 * @author huzorro(huzorro@gmail.com)
 *
 */
public class DefaultServerSessionConfig extends DefaultSessionConfig {
	private String host;
	private int port;
	private String user;
	private String passwd;
	private short version;
	private int maxRetry;
	private long retryWaitTime;
	private int maxSessions;
	private int windows;
	public DefaultServerSessionConfig() {
	}

	@Override
	public String getHost() {
		return host;
	}

	@Override
	public void setHost(String host) {
		this.host = host;
	}

	@Override
	public void setPort(int port) {
		this.port = port;
	}

	@Override
	public int getPort() {
		return port;
	}

	@Override
	public void setUser(String user) {
		this.user = user; 
	}

	@Override
	public void setPasswd(String passwd) {
		this.passwd = passwd; 
	}

	@Override
	public void setVersion(short version) {
		this.version = version;
	}

	@Override
	public String getUser() {
		return user;
	}

	@Override
	public String getPasswd() {
		return passwd;
	}

	@Override
	public short getVersion() {
		return version;
	}

	@Override
	public void setMaxRetry(int maxRetry) {
		this.maxRetry = maxRetry;
	}

	@Override
	public int getMaxRetry() {
		return maxRetry;
	}

	@Override
	public void setRetryWaitTime(long retryWaitTime) {
		this.retryWaitTime = retryWaitTime;
	}

	@Override
	public long getRetryWaitTime() {
		return retryWaitTime;
	}

	@Override
	public void setMaxSessions(int maxSessions) {
		this.maxSessions = maxSessions;
	}

	@Override
	public int getMaxSessions() {
		return maxSessions;
	}

	@Override
	public void setWindows(int windows) {
		this.windows = windows;
	}

	@Override
	public int getWindows() {
		return windows;
	}

	@Override
	public String toString() {
		return String
				.format("DefaultServerSessionConfig [host=%s, port=%s, user=%s, passwd=%s, version=%s, maxRetry=%s, retryWaitTime=%s, maxSessions=%s, windows=%s]",
						host, port, user, passwd, version, maxRetry,
						retryWaitTime, maxSessions, windows);
	}
	

}

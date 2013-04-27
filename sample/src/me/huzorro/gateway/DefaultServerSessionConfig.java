/**
 * 
 */
package me.huzorro.gateway;

/**
 * @author huzorro
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
	/**
	 * 
	 */
	public DefaultServerSessionConfig() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see me.huzorro.gateway.DefaultSessionConfig#getHost()
	 */
	@Override
	public String getHost() {
		return host;
	}

	/* (non-Javadoc)
	 * @see me.huzorro.gateway.DefaultSessionConfig#setHost(java.lang.String)
	 */
	@Override
	public void setHost(String host) {
		this.host = host;
	}

	/* (non-Javadoc)
	 * @see me.huzorro.gateway.DefaultSessionConfig#setPort(int)
	 */
	@Override
	public void setPort(int port) {
		this.port = port;
	}

	/* (non-Javadoc)
	 * @see me.huzorro.gateway.DefaultSessionConfig#getPort()
	 */
	@Override
	public int getPort() {
		return port;
	}

	/* (non-Javadoc)
	 * @see me.huzorro.gateway.DefaultSessionConfig#setUser()
	 */
	@Override
	public void setUser(String user) {
		this.user = user; 
	}

	/* (non-Javadoc)
	 * @see me.huzorro.gateway.DefaultSessionConfig#setPasswd()
	 */
	@Override
	public void setPasswd(String passwd) {
		this.passwd = passwd; 
	}

	/* (non-Javadoc)
	 * @see me.huzorro.gateway.DefaultSessionConfig#setVersion()
	 */
	@Override
	public void setVersion(short version) {
		this.version = version;
	}

	/* (non-Javadoc)
	 * @see me.huzorro.gateway.DefaultSessionConfig#getUser()
	 */
	@Override
	public String getUser() {
		return user;
	}

	/* (non-Javadoc)
	 * @see me.huzorro.gateway.DefaultSessionConfig#getPasswd()
	 */
	@Override
	public String getPasswd() {
		return passwd;
	}

	/* (non-Javadoc)
	 * @see me.huzorro.gateway.DefaultSessionConfig#getVersion()
	 */
	@Override
	public short getVersion() {
		return version;
	}

	/* (non-Javadoc)
	 * @see me.huzorro.gateway.DefaultSessionConfig#setMaxRetry(int)
	 */
	@Override
	public void setMaxRetry(int maxRetry) {
		this.maxRetry = maxRetry;
	}

	/* (non-Javadoc)
	 * @see me.huzorro.gateway.DefaultSessionConfig#getMaxRetry()
	 */
	@Override
	public int getMaxRetry() {
		return maxRetry;
	}

	/* (non-Javadoc)
	 * @see me.huzorro.gateway.DefaultSessionConfig#setRetryWaitTime(long)
	 */
	@Override
	public void setRetryWaitTime(long retryWaitTime) {
		this.retryWaitTime = retryWaitTime;
	}

	/* (non-Javadoc)
	 * @see me.huzorro.gateway.DefaultSessionConfig#getRetryWaitTime()
	 */
	@Override
	public long getRetryWaitTime() {
		return retryWaitTime;
	}

	/* (non-Javadoc)
	 * @see me.huzorro.gateway.DefaultSessionConfig#setMaxSessions(int)
	 */
	@Override
	public void setMaxSessions(int maxSessions) {
		this.maxSessions = maxSessions;
	}

	/* (non-Javadoc)
	 * @see me.huzorro.gateway.DefaultSessionConfig#getMaxSessions()
	 */
	@Override
	public int getMaxSessions() {
		return maxSessions;
	}

	/* (non-Javadoc)
	 * @see me.huzorro.gateway.DefaultSessionConfig#setWindows(int)
	 */
	@Override
	public void setWindows(int windows) {
		this.windows = windows;
	}

	/* (non-Javadoc)
	 * @see me.huzorro.gateway.DefaultSessionConfig#getWindows()
	 */
	@Override
	public int getWindows() {
		return windows;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String
				.format("DefaultServerSessionConfig [host=%s, port=%s, user=%s, passwd=%s, version=%s, maxRetry=%s, retryWaitTime=%s, maxSessions=%s, windows=%s]",
						host, port, user, passwd, version, maxRetry,
						retryWaitTime, maxSessions, windows);
	}
	

}

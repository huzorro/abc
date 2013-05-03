package me.huzorro.gateway;

import org.apache.commons.configuration.XMLConfiguration;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public class DefaultSessionConfig implements SessionConfig {
    
    private String channelIds;
    private String attPreffix;
    private XMLConfiguration configuration;
    private Object attachment; 
    /* (non-Javadoc)
     * @see me.huzorro.gateway.SessionConfig#getId()
     */
    @Override
    public String getChannelIds() {
        return channelIds;
    }
    /* (non-Javadoc)
     * @see me.huzorro.gateway.SessionConfig#setId(java.lang.String)
     */
    @Override
    public void setChannelIds(String ids) {
        this.channelIds = ids;
    }
	@Override
	public void setUser(String user) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setPasswd(String passwd) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setVersion(short version) {
		// TODO Auto-generated method stub
		
	}    
    /* (non-Javadoc)
     * @see me.huzorro.gateway.SessionConfig#getHost()
     */
    @Override
    public String getHost() {
        return configuration.getString(String.format("%1$s.%2$s", attPreffix, "host"));
    }
    /* (non-Javadoc)
     * @see me.huzorro.gateway.SessionConfig#setHost(java.lang.String)
     */
    @Override
    public void setHost(String host) {
    }
    /* (non-Javadoc)
     * @see me.huzorro.gateway.SessionConfig#setPort(int)
     */
    @Override
    public void setPort(int port) {
    }
    /* (non-Javadoc)
     * @see me.huzorro.gateway.SessionConfig#getPort()
     */
    @Override
    public int getPort() {
        return configuration.getInt(String.format("%1$s.%2$s", attPreffix, "port"));
    } 
    
    /* (non-Javadoc)
     * @see me.huzorro.gateway.SessionConfig#getUser()
     */
    @Override
    public String getUser() {
        return configuration.getString(String.format("%1$s.%2$s", attPreffix, "user"));
    }
    /* (non-Javadoc)
     * @see me.huzorro.gateway.SessionConfig#getPasswd()
     */
    @Override
    public String getPasswd() {
        return configuration.getString(String.format("%1$s.%2$s", attPreffix, "passwd"));
    }   
    
    /* (non-Javadoc)
	 * @see me.huzorro.gateway.SessionConfig#setGateId(int)
	 */
	@Override
	public void setGateId(int gateId) {
	}
	/* (non-Javadoc)
	 * @see me.huzorro.gateway.SessionConfig#getGateId()
	 */
	@Override
	public int getGateId() {
        return configuration.getInt(String.format("%1$s.%2$s", attPreffix, "gateId"));
	}
	/* (non-Javadoc)
     * @see me.huzorro.gateway.SessionConfig#getVersion()
     */
    @Override
    public short getVersion() {
        return configuration.getShort(String.format("%1$s.%2$s", attPreffix, "version"));
    }    
    /* (non-Javadoc)
     * @see me.huzorro.gateway.SessionConfig#getAttPreffix()
     */
    @Override
    public String getAttPreffix() {
        return attPreffix;
    }
    /* (non-Javadoc)
     * @see me.huzorro.gateway.SessionConfig#setAttPreffix(java.lang.String)
     */
    @Override
    public void setAttPreffix(String attPreffix) {
        this.attPreffix = attPreffix;
    }
    /* (non-Javadoc)
     * @see me.huzorro.gateway.SessionConfig#getConfiguration()
     */
    @Override
    public XMLConfiguration getConfiguration() {
        return this.configuration;
    }
    /* (non-Javadoc)
     * @see me.huzorro.gateway.SessionConfig#setConfiguration(org.apache.commons.configuration.XMLConfiguration)
     */
    @Override
    public void setConfiguration(XMLConfiguration configuration) {
        this.configuration = configuration;
    }
    /* (non-Javadoc)
     * @see me.huzorro.gateway.SessionConfig#getAttachment()
     */
    @Override
    public Object getAttachment() {
        return attachment;
    }
    /* (non-Javadoc)
     * @see me.huzorro.gateway.SessionConfig#setAttachment(java.lang.Object)
     */
    @Override
    public void setAttachment(Object attachment) {
        this.attachment = attachment;
    }
    
	@Override
	public void setIdleTime(long seconds) {
	}
	
	@Override
	public long getIdleTime() {
		return configuration.getLong(String.format("%1$s.%2$s", attPreffix, "idleTime"));
	}    
	
	public void setLifeTime(long seconds) {
	}
	public long getLifeTime() {
		return configuration.getLong(String.format("%1$s.%2$s", attPreffix, "lifeTime"));
	}
    /* (non-Javadoc)
     * @see me.huzorro.gateway.SessionConfig#setMaxRetry(int)
     */
    @Override
    public void setMaxRetry(int maxRetry) {
    }
    /* (non-Javadoc)
     * @see me.huzorro.gateway.SessionConfig#getMaxRetry()
     */
    @Override
    public int getMaxRetry() {
        return configuration.getInt(String.format("%1$s.%2$s", attPreffix, "maxRetry")); 
    }
    /* (non-Javadoc)
     * @see me.huzorro.gateway.SessionConfig#setRetryWaitTime(long)
     */
    @Override
    public void setRetryWaitTime(long seconds) {
    }
    /* (non-Javadoc)
     * @see me.huzorro.gateway.SessionConfig#getRetryWaitTime()
     */
    @Override
    public long getRetryWaitTime() {
        return configuration.getInt(String.format("%1$s.%2$s", attPreffix, "retryWaitTime"));
    }
    /* (non-Javadoc)
     * @see me.huzorro.gateway.SessionConfig#setMaxSessions(int)
     */
    @Override
    public void setMaxSessions(int maxSessions) {
    }
    /* (non-Javadoc)
     * @see me.huzorro.gateway.SessionConfig#getMaxSessions()
     */
    @Override
    public int getMaxSessions() {
        return configuration.getInt(String.format("%1$s.%2$s", attPreffix, "maxSessions"));
    }
    /* (non-Javadoc)
     * @see me.huzorro.gateway.SessionConfig#setWindows(int)
     */
    @Override
    public void setWindows(int windows) {
    }
    /* (non-Javadoc)
     * @see me.huzorro.gateway.SessionConfig#getWindows()
     */
    @Override
    public int getWindows() {
        return configuration.getInt(String.format("%1$s.%2$s", attPreffix, "windows"));
    }
    /* (non-Javadoc)
     * @see me.huzorro.gateway.SessionConfig#setGeneralThreadNum(int)
     */
    @Override
    public void setGeneralThreadNum(int generalThreadNum) {
    }
    /* (non-Javadoc)
     * @see me.huzorro.gateway.SessionConfig#getGeneralThreadNum()
     */
    @Override
    public int getGeneralThreadNum() {
        return configuration.getInt(String.format("%1$s.%2$s", attPreffix, "generalThreadNum"));
    }
    /* (non-Javadoc)
     * @see me.huzorro.gateway.SessionConfig#setScheduleThreadNum(int)
     */
    @Override
    public void setScheduleThreadNum(int scheduleThreadNum) {
    }
    /* (non-Javadoc)
     * @see me.huzorro.gateway.SessionConfig#getScheduleThreadNum()
     */
    @Override
    public int getScheduleThreadNum() {
        return configuration.getInt(String.format("%1$s.%2$s", attPreffix, "scheduleThreadNum"));
    }
    @Override
    public String  getRequestQueueName() {
        return configuration.getString(String.format("%1$s.%2$s", attPreffix, "requestQueueName"));
    }
    @Override
    public void setRequestQueueName(String queueName) {
    	
    }
    @Override
    public String getResponseQueueName() {
        return configuration.getString(String.format("%1$s.%2$s", attPreffix, "responseQueueName"));
    }
    @Override
    public void setResponseQueueName(String queueName) {
    }
    @Override
    public String getDeliverQueueName() {
        return configuration.getString(String.format("%1$s.%2$s", attPreffix, "deliverQueueName"));
    }
    @Override
    public void setDeliverQueueName(String queueName) {
    }
    @Override
    public void setRequestQueuePathHome(String pathHome) {
    }
    @Override
    public String getRequestQueuePathHome() {
        return configuration.getString(String.format("%1$s.%2$s", attPreffix, "requestQueuePathHome"));
    }
    @Override
    public void setResponseQueuePathHome(String pathHome) {        
    }
    @Override
    public String getResponseQueuePathHome() {
        return configuration.getString(String.format("%1$s.%2$s", attPreffix, "responseQueuePathHome"));
    }
    @Override
    public void setDeliverQueuePathHome(String pathHome) {        
    }
    @Override
    public String getDeliverQueuePathHome() {
        return configuration.getString(String.format("%1$s.%2$s", attPreffix, "deliverQueuePathHome"));
    }
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String
				.format("DefaultSessionConfig [getChannelIds()=%s, getHost()=%s, getPort()=%s, getUser()=%s, getPasswd()=%s, getVersion()=%s, getAttPreffix()=%s, getConfiguration()=%s, getAttachment()=%s, getIdleTime()=%s, getMaxRetry()=%s, getRetryWaitTime()=%s, getMaxSessions()=%s, getWindows()=%s, getGeneralThreadNum()=%s, getScheduleThreadNum()=%s, getRequestQueueName()=%s, getResponseQueueName()=%s, getDeliverQueueName()=%s, getRequestQueuePathHome()=%s, getResponseQueuePathHome()=%s, getDeliverQueuePathHome()=%s]",
						getChannelIds(), getHost(), getPort(), getUser(),
						getPasswd(), getVersion(), getAttPreffix(),
						getConfiguration(), getAttachment(), getIdleTime(),
						getMaxRetry(), getRetryWaitTime(), getMaxSessions(),
						getWindows(), getGeneralThreadNum(),
						getScheduleThreadNum(), getRequestQueueName(),
						getResponseQueueName(), getDeliverQueueName(),
						getRequestQueuePathHome(), getResponseQueuePathHome(),
						getDeliverQueuePathHome());
	}
 
}

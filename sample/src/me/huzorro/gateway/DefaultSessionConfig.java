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
    public void setRetryWaitTime(long retryWaitTime) {
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
    /* (non-Javadoc)
     * @see me.huzorro.gateway.SessionConfig#getRequestQueueSize()
     */
    @Override
    public int getRequestQueueSize() {
        return configuration.getInt(String.format("%1$s.%2$s", attPreffix, "requestQueueSize"));
    }
    /* (non-Javadoc)
     * @see me.huzorro.gateway.SessionConfig#setRequestQueueSize(int)
     */
    @Override
    public void setRequestQueueSize(int requestQueueSize) {
    }
    /* (non-Javadoc)
     * @see me.huzorro.gateway.SessionConfig#getResponseQueueSize()
     */
    @Override
    public int getResponseQueueSize() {
        return configuration.getInt(String.format("%1$s.%2$s", attPreffix, "responseQueueSize"));
    }
    /* (non-Javadoc)
     * @see me.huzorro.gateway.SessionConfig#setResponseQueueSize(int)
     */
    @Override
    public void setResponseQueueSize(int responseQueueSize) {
    }
    /* (non-Javadoc)
     * @see me.huzorro.gateway.SessionConfig#getDeliverQueueSize()
     */
    @Override
    public int getDeliverQueueSize() {
        return configuration.getInt(String.format("%1$s.%2$s", attPreffix, "deliverQueueSize"));
    }
    /* (non-Javadoc)
     * @see me.huzorro.gateway.SessionConfig#setDeliverQueueSize(int)
     */
    @Override
    public void setDeliverQueueSize(int deliverQueueSize) {
    }
    /* (non-Javadoc)
     * @see me.huzorro.gateway.SessionConfig#setRequestQueueSequence(int)
     */
    @Override
    public void setRequestQueueSequence(int sequence) {
    }
    /* (non-Javadoc)
     * @see me.huzorro.gateway.SessionConfig#getRequestQueueSequence()
     */
    @Override
    public int getRequestQueueSequence() {
        return configuration.getInt(String.format("%1$s.%2$s", attPreffix, "requestQueueSequence"));
    }
    /* (non-Javadoc)
     * @see me.huzorro.gateway.SessionConfig#setResponseQueueSequence(int)
     */
    @Override
    public void setResponseQueueSequence(int sequence) {        
    }
    /* (non-Javadoc)
     * @see me.huzorro.gateway.SessionConfig#getResponseQueueSequence()
     */
    @Override
    public int getResponseQueueSequence() {
        return configuration.getInt(String.format("%1$s.%2$s", attPreffix, "responseQueueSequence"));
    }
    /* (non-Javadoc)
     * @see me.huzorro.gateway.SessionConfig#setDeliverQueueSequence(int)
     */
    @Override
    public void setDeliverQueueSequence(int sequence) {        
    }
    /* (non-Javadoc)
     * @see me.huzorro.gateway.SessionConfig#getDeliverQueueSequence()
     */
    @Override
    public int getDeliverQueueSequence() {
        return configuration.getInt(String.format("%1$s.%2$s", attPreffix, "deliverQueueSequence"));
    }
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return String
                .format("DefaultSessionConfig [channelIds=%s, attPreffix=%s, configuration=%s, attachment=%s, getChannelIds()=%s, getHost()=%s, getPort()=%s, getAttPreffix()=%s, getConfiguration()=%s, getAttachment()=%s, getMaxRetry()=%s, getRetryWaitTime()=%s, getMaxSessions()=%s, getWindows()=%s, getGeneralThreadNum()=%s, getScheduleThreadNum()=%s, getRequestQueueSize()=%s, getResponseQueueSize()=%s, getDeliverQueueSize()=%s, getRequestQueueSequence()=%s, getResponseQueueSequence()=%s, getDeliverQueueSequence()=%s]",
                        channelIds, attPreffix, configuration, attachment,
                        getChannelIds(), getHost(), getPort(), getAttPreffix(),
                        getConfiguration(), getAttachment(), getMaxRetry(),
                        getRetryWaitTime(), getMaxSessions(), getWindows(),
                        getGeneralThreadNum(), getScheduleThreadNum(),
                        getRequestQueueSize(), getResponseQueueSize(),
                        getDeliverQueueSize(), getRequestQueueSequence(),
                        getResponseQueueSequence(), getDeliverQueueSequence());
    }

    
}

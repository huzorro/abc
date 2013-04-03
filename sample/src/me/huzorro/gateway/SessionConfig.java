package me.huzorro.gateway;

import org.apache.commons.configuration.XMLConfiguration;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public interface SessionConfig {
    public String getChannelIds();
    public void setChannelIds(String ids);
    public String getHost();
    public void setHost(String host);
    public void setPort(int port);
    public int getPort();
    public String getAttPreffix();
    public void setAttPreffix(String attPreffix);
    public XMLConfiguration getConfiguration();
    public void setConfiguration(XMLConfiguration configuration);
    public Object getAttachment();
    public void setAttachment(Object attachment);
    public void setMaxRetry(int maxRetry);
    public int getMaxRetry();
    public void setRetryWaitTime(long retryWaitTime);
    public long getRetryWaitTime();
    public void setMaxSessions(int maxSessions);
    public int getMaxSessions();
    public void setWindows(int windows);
    public int getWindows();
    public void setGeneralThreadNum(int generalThreadNum);
    public int getGeneralThreadNum();
    public void setScheduleThreadNum(int scheduleThreadNum);
    public int getScheduleThreadNum();
    public int getRequestQueueSize();
    public void setRequestQueueSize(int requestQueueSize);
    public int getResponseQueueSize();
    public void setResponseQueueSize(int responseQueueSize);
    public int getDeliverQueueSize();
    public void setDeliverQueueSize(int deliverQueueSize);
    public void setRequestQueueSequence(int sequence);
    public int getRequestQueueSequence();
    public void setResponseQueueSequence(int sequence);
    public int getResponseQueueSequence();
    public void setDeliverQueueSequence(int sequence);
    public int getDeliverQueueSequence();
    
}

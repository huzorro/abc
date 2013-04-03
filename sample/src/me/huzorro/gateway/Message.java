package me.huzorro.gateway;

import java.io.Serializable;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public interface Message<T> extends Serializable  {
    public void setChannelIds(Object ids);
    public Object getChannelIds(); 
    public int incrementAndGetRequests();
    public int getRequests();
    public Message<?> setResponse(Message<?> message);
    public Message<?> getResponse();
    public void setHeader(Header<T> head);
    public Header<T> getHeader();  
    public void setBodyBuffer(T buffer);
    public T getBodyBuffer();
    public void setAttachment(Object attachment);
    public Object getAttachment(); 
}

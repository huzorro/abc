package me.huzorro.gateway;

import java.io.Serializable;

import me.huzorro.gateway.cmpp.PacketType;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public interface Message<T> extends Serializable  {
	public void setPacketType(PacketType packetType);
	public PacketType getPacketType();
	public void setTimestamp(long milliseconds);
	public long getTimestamp();
	public boolean isTerminationLife();
    public void setConfig(SessionConfig config);
    public SessionConfig getConfig(); 
    public int incrementAndGetRequests();
    public int getRequests();
    public Message<?> setResponse(Message<?> message);
    public Message<?> getResponse();
    public Message<?> setRequest(Message<?> message);
    public Message<?> getRequest(); 
    public void setHeader(Header<T> head);
    public Header<T> getHeader();  
    public void setBodyBuffer(T buffer);
    public T getBodyBuffer();
    public void setAttachment(Object attachment);
    public Object getAttachment(); 
}

package me.huzorro.gateway;

import java.util.concurrent.atomic.AtomicInteger;

import me.huzorro.gateway.cmpp.PacketType;

import org.jboss.netty.buffer.ChannelBuffer;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public class DefaultMessage<T extends ChannelBuffer> implements Message<T>  {
    private static final long serialVersionUID = -4245789758843785127L;
    private PacketType packetType;
    private long timestamp = System.currentTimeMillis();
    private SessionConfig config;
    private AtomicInteger requests = new AtomicInteger();
    private Message<?> response;
    private Message<?> request;
    private Header<T> header;
    private T buffer;
    private Object attachment;
    /**
     * 
     */
    public DefaultMessage() {
        // TODO Auto-generated constructor stub
    }
	@Override
	public void setPacketType(PacketType packetType) {
		this.packetType = packetType;
	}
	@Override
	public PacketType getPacketType() {
		return packetType;
	}    
	public void setTimestamp(long milliseconds) {
		this.timestamp = milliseconds;
	}
	public long getTimestamp() {
		return timestamp;
	}
	
	
    /* (non-Javadoc)
	 * @see me.huzorro.gateway.Message#isTerminationLife()
	 */
	@Override
	public boolean isTerminationLife() {
		return (System.currentTimeMillis() - timestamp) > (config == null ? 72 * 3600 * 1000
				: config.getLifeTime() * 1000);
	}
	/* (non-Javadoc)
	 * @see me.huzorro.gateway.Message#setConfig(me.huzorro.gateway.SessionConfig)
	 */
	@Override
	public void setConfig(SessionConfig config) {
		this.config = config;
	}
	/* (non-Javadoc)
	 * @see me.huzorro.gateway.Message#getConfig()
	 */
	@Override
	public SessionConfig getConfig() {
		return config;
	}
	/* (non-Javadoc)
     * @see me.huzorro.gateway.Message#incrementAndGetRequests()
     */
    @Override
    public int incrementAndGetRequests() {
        return requests.incrementAndGet();
    }
    /* (non-Javadoc)
     * @see me.huzorro.gateway.Message#setResponse(me.huzorro.gateway.Message)
     */
    @Override
    public Message<?> setResponse(Message<?> message) {
        this.response = message;
        return this;
        
    }
    /* (non-Javadoc)
     * @see me.huzorro.gateway.Message#getResponse()
     */
    @Override
    public Message<?> getResponse() {
        return response;
    }
	@Override
	public Message<?> setRequest(Message<?> message) {
		this.request = message;
		return this;
	}
	@Override
	public Message<?> getRequest() {
		return request;
	}    
    /* (non-Javadoc)
     * @see me.huzorro.gateway.Message#getRequests()
     */
    @Override
    public int getRequests() {
        return requests.get();
    }
    /* (non-Javadoc)
     * @see me.huzorro.gateway.Message#setHead(me.huzorro.gateway.Head)
     */
    @Override
    public void setHeader(Header<T> header) {
        this.header = header;
    }
    /* (non-Javadoc)
     * @see me.huzorro.gateway.Message#getHead()
     */
    @Override
    public Header<T> getHeader() {
        return header;
    }

    /* (non-Javadoc)
     * @see me.huzorro.gateway.Message#setBodyBuffer(java.lang.Object)
     */
    @Override
    public void setBodyBuffer(T buffer) {
        this.buffer = buffer;
    }
    /* (non-Javadoc)
     * @see me.huzorro.gateway.Message#getBodyBuffer()
     */
    @Override
    public T getBodyBuffer() {
        return buffer;
    }
    /* (non-Javadoc)
     * @see me.huzorro.gateway.Message#setAttachment(java.lang.Object)
     */
    @Override
    public void setAttachment(Object attachment) {
        this.attachment = attachment;
    }
    /* (non-Javadoc)
     * @see me.huzorro.gateway.Message#getAttachment()
     */
    @Override
    public Object getAttachment() {
        return attachment;
    }
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String
				.format("DefaultMessage [packetType=%s, timestamp=%s, config=%s, requests=%s, response=%s, request=%s, header=%s, buffer=%s, attachment=%s, isTerminationLife()=%s]",
						packetType, timestamp, config, requests.get(), response,
						request, header, buffer, attachment,
						isTerminationLife());
	}
    
}

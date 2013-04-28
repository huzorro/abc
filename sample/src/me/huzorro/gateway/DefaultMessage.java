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
    private Object channelIds;
    private AtomicInteger requests = new AtomicInteger();
    private Message<?> response;
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
    /* (non-Javadoc)
     * @see me.huzorro.gateway.Message#setChannelIds(java.lang.Object)
     */
    @Override
    public void setChannelIds(Object ids) {
        this.channelIds = ids;
    }

    /* (non-Javadoc)
     * @see me.huzorro.gateway.Message#getChannelIds()
     */
    @Override
    public Object getChannelIds() {
        return channelIds;
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
                .format("DefaultMessage [channelIds=%s, requests=%s, response=%s, header=%s]",
                        channelIds, requests, response, header);
    }
    
}

/**
 * 
 */
package me.huzorro.gateway;

import me.huzorro.gateway.cmpp.PacketType;

import org.jboss.netty.buffer.ChannelBuffer;

/**
 * @author huzorro
 * @param <T>
 *
 */
public class CmppSubmitResponseMessage<T extends ChannelBuffer> extends DefaultMessage<T> {
	private static final long serialVersionUID = -6806940736604019528L;
	private MsgId msgId = new MsgId();
	private long result = 0;
	
	public CmppSubmitResponseMessage() {
		this(PacketType.CMPPSUBMITRESPONSE);
	}
	
	public CmppSubmitResponseMessage(PacketType packetType) {
		setPacketType(packetType);
	}
	
	/**
	 * @return the msgId
	 */
	public MsgId getMsgId() {
		return msgId;
	}
	/**
	 * @param msgId the msgId to set
	 */
	public void setMsgId(MsgId msgId) {
		this.msgId = msgId;
	}
	/**
	 * @return the result
	 */
	public long getResult() {
		return result;
	}
	/**
	 * @param result the result to set
	 */
	public void setResult(long result) {
		this.result = result;
	}
	
	
}

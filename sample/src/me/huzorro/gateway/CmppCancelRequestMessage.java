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
public class CmppCancelRequestMessage<T extends ChannelBuffer> extends DefaultMessage<T> {
	private static final long serialVersionUID = -4633530203133110407L;
	private MsgId msgId = new MsgId();
	
	public CmppCancelRequestMessage() {
		this(PacketType.CMPPCANCELREQUEST);
	}
	
	public CmppCancelRequestMessage(PacketType packetType) {
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
	
}

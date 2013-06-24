/**
 * 
 */
package me.huzorro.gateway;

import me.huzorro.gateway.cmpp.CmppPacketType;
import me.huzorro.gateway.cmpp.PacketType;

/**
 * @author huzorro(huzorro@gmail.com)
 * @param <T>
 *
 */
public class CmppCancelRequestMessage extends DefaultMessage {
	private static final long serialVersionUID = -4633530203133110407L;
	private MsgId msgId = new MsgId();
	
	public CmppCancelRequestMessage() {
		this(CmppPacketType.CMPPCANCELREQUEST);
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

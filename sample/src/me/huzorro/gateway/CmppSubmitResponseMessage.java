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
public class CmppSubmitResponseMessage extends DefaultMessage {
	private static final long serialVersionUID = -6806940736604019528L;
	private MsgId msgId = new MsgId();
	private long result = 0;
	
	public CmppSubmitResponseMessage() {
		this(CmppPacketType.CMPPSUBMITRESPONSE);
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

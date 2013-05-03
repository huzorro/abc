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
public class CmppCancelResponseMessage<T extends ChannelBuffer> extends DefaultMessage<T> {
	private static final long serialVersionUID = -1111862395776885021L;
	private long successId = 1;
	
	public CmppCancelResponseMessage() {
		this(PacketType.CMPPCANCELRESPONSE);
	}
	
	public CmppCancelResponseMessage(PacketType packetType) {
		setPacketType(packetType);
	}

	/**
	 * @return the successId
	 */
	public long getSuccessId() {
		return successId;
	}

	/**
	 * @param successId the successId to set
	 */
	public void setSuccessId(long successId) {
		this.successId = successId;
	}
	
}

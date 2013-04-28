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
public class CmppTerminateRequestMessage<T extends ChannelBuffer> extends DefaultMessage<T> {
	private static final long serialVersionUID = 814288661389104951L;
	
	public CmppTerminateRequestMessage() {
		this(PacketType.CMPPTERMINATEREQUEST);
	}
	public CmppTerminateRequestMessage(PacketType packetType) {
		setPacketType(packetType);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("CmppTerminateRequestMessage [toString()=%s]",
				super.toString());
	}
	
}

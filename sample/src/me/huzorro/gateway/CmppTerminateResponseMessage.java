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
public class CmppTerminateResponseMessage<T extends ChannelBuffer> extends DefaultMessage<T> {
	private static final long serialVersionUID = -2657187574508760595L;

	public CmppTerminateResponseMessage() {
		this(PacketType.CMPPTERMINATERESPONSE);
	}
	public CmppTerminateResponseMessage(PacketType packetType) {
		setPacketType(packetType);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("CmppTerminateResponseMessage [toString()=%s]",
				super.toString());
	}
	
}

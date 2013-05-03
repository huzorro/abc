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
public class CmppActiveTestResponseMessage<T extends ChannelBuffer> extends DefaultMessage<T> {
	private static final long serialVersionUID = 4300214238350805590L;
	
	public CmppActiveTestResponseMessage() {
		this(PacketType.CMPPACTIVETESTRESPONSE);
	}
	
	public CmppActiveTestResponseMessage(PacketType packetType) {
		setPacketType(packetType);
	}
}

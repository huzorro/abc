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
public class CmppActiveTestRequestMessage<T extends ChannelBuffer> extends DefaultMessage<T> {
	private static final long serialVersionUID = 4496674961657465872L;
	
	public CmppActiveTestRequestMessage() {
		this(PacketType.CMPPACTIVETESTREQUEST);
	}
	
	public CmppActiveTestRequestMessage(PacketType packetType) {
		setPacketType(packetType);		
	}
	
}

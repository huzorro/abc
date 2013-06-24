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
public class CmppActiveTestRequestMessage extends DefaultMessage {
	private static final long serialVersionUID = 4496674961657465872L;
	
	public CmppActiveTestRequestMessage() {
		this(CmppPacketType.CMPPACTIVETESTREQUEST);
	}
	
	public CmppActiveTestRequestMessage(PacketType packetType) {
		setPacketType(packetType);		
	}
	
}

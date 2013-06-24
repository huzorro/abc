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
public class CmppActiveTestResponseMessage extends DefaultMessage{
	private static final long serialVersionUID = 4300214238350805590L;
	
	public CmppActiveTestResponseMessage() {
		this(CmppPacketType.CMPPACTIVETESTRESPONSE);
	}
	
	public CmppActiveTestResponseMessage(PacketType packetType) {
		setPacketType(packetType);
	}
}

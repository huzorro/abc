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
public class CmppTerminateRequestMessage extends DefaultMessage{
	private static final long serialVersionUID = 814288661389104951L;
	
	public CmppTerminateRequestMessage() {
		this(CmppPacketType.CMPPTERMINATEREQUEST);
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

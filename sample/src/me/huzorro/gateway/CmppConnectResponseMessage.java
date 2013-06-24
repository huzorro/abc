package me.huzorro.gateway;

import me.huzorro.gateway.cmpp.CmppConnectResponse;
import me.huzorro.gateway.cmpp.CmppPacketType;
import me.huzorro.gateway.cmpp.PacketType;

import org.apache.commons.codec.binary.Hex;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 * @param <T>
 */
public class CmppConnectResponseMessage extends DefaultMessage{
    private static final long serialVersionUID = -5010314567064353091L;
    private long status = 3;
    private byte[] authenticatorISMG = new byte[CmppConnectResponse.AUTHENTICATORISMG.getLength()];
    private short version = 0x30;
    
    
    public CmppConnectResponseMessage() {
    	this(CmppPacketType.CMPPCONNECTRESPONSE);
	}
    public CmppConnectResponseMessage(PacketType packetType) {
    	setPacketType(packetType);
	}
	/**
     * @return the status
     */
    public long getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(long status) {
        this.status = status;
    }

    /**
     * @return the authenticatorISMG
     */
    public byte[] getAuthenticatorISMG() {
        return authenticatorISMG;
    }

    /**
     * @param authenticatorISMG the authenticatorISMG to set
     */
    public void setAuthenticatorISMG(byte[] authenticatorISMG) {
        this.authenticatorISMG = authenticatorISMG;
    }

    /**
     * @return the version
     */
    public short getVersion() {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(short version) {
        this.version = version;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return String
                .format("CmppConnectResponseMessage [status=%s, authenticatorISMG=%s, version=%s, toString()=%s]",
                        status, Hex.encodeHexString(authenticatorISMG), version,
                        super.toString());
    }
 
}

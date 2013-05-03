package me.huzorro.gateway;

import me.huzorro.gateway.cmpp.PacketStructure;
import me.huzorro.gateway.cmpp.PacketType;

import org.apache.commons.codec.binary.Hex;
import org.jboss.netty.buffer.ChannelBuffer;

/**
 * 
 *
 * @author huzorro(huzorro@gmail.com)
 * @param T extends ChannelBuffer
 */
public class CmppConnectRequestMessage<T extends ChannelBuffer> extends DefaultMessage<T> {
    private static final long serialVersionUID = -4852540410843278872L;
	private String sourceAddr = new String(
			new byte[PacketStructure.ConnectRequest.SOURCEADDR.getLength()],
			GlobalVars.defaultTransportCharset);
	private byte[] authenticatorSource = new byte[PacketStructure.ConnectRequest.AUTHENTICATORSOURCE
			.getLength()];
    private short version = 0x30;
    private long timestamp = 0L;
    
    public CmppConnectRequestMessage() {
    	this(PacketType.CMPPCONNECTREQUEST);
    }
    public CmppConnectRequestMessage(PacketType packetType) {    	
    	setPacketType(packetType);
    }
    /**
     * @return the sourceAddr
     */
    public String getSourceAddr() {
        return sourceAddr;
    }
    /**
     * @param sourceAddr the sourceAddr to set
     */
    public void setSourceAddr(String sourceAddr) {
        this.sourceAddr = sourceAddr;
    }
    /**
     * @return the authenticatorSource
     */
    public byte[] getAuthenticatorSource() {
        return authenticatorSource;
    }
    /**
     * @param authenticatorSource the authenticatorSource to set
     */
    public void setAuthenticatorSource(byte[] authenticatorSource) {
        this.authenticatorSource = authenticatorSource;
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
    /**
     * @return the timestamp
     */
    public long getTimestamp() {
        return timestamp;
    }
    /**
     * @param timestamp the timestamp to set
     */
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return String
                .format("CmppConnectRequestMessage [sourceAddr=%s, authenticatorSource=%s, version=%s, timestamp=%s]",
                        sourceAddr, Hex.encodeHexString(authenticatorSource),
                        version, timestamp, super.toString());
    }
    
}

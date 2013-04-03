package me.huzorro.gateway;

import org.apache.commons.codec.binary.Hex;
import org.jboss.netty.buffer.ChannelBuffer;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 * @param <T>
 */
public class CmppConnectResponseMessage<T extends ChannelBuffer> extends DefaultMessage<T> {
    private static final long serialVersionUID = -5010314567064353091L;
    private int status;
    private byte[] authenticatorISMG;
    private byte version;
    
    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
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
    public byte getVersion() {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(byte version) {
        this.version = version;
    }


    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return String
                .format("CmppConnectResponseMessage [status=%s, authenticatorISMG=%s, version=%s]",
                        status, Hex.encodeHexString(authenticatorISMG), version);
    }
    
}

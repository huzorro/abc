package me.huzorro.gateway;

import java.io.Serializable;

/**
 * 
 * @author huzorro(huzorro@gmail.com)
 *
 */
public interface Header extends Serializable {
    public void setHeadLength(long length);
    public long getHeadLength();
    public void setPacketLength(long length);
    public long getPacketLength();
    public void setBodyLength(long length);
    public long getBodyLength();
    public void setCommandId(Object commandId);
    public Object getCommandId();
    public void setHeadBuffer(byte[] buffer);
    public byte[] getHeadBuffer();
    public void setSequenceId(Object transitionId);
    public Object getSequenceId();
}

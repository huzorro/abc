package me.huzorro.gateway;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public interface Header<T> {
    public void setHeadLength(long length);
    public long getHeadLength();
    public void setPacketLength(long length);
    public long getPacketLength();
    public void setBodyLength(long length);
    public long getBodyLength();
    public void setCommandId(Object commandId);
    public Object getCommandId();
    public void setHeadBuffer(T buffer);
    public T getHeadBuffer();
    public void setSequenceId(Object transitionId);
    public Object getSequenceId();
}

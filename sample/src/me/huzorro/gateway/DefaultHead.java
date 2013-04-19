package me.huzorro.gateway;

import org.jboss.netty.buffer.ChannelBuffer;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public class DefaultHead<T extends ChannelBuffer> implements Header<T> {
    private long headLength;
    private long packetLength;
    private long bodyLength;
    private Object commandId;
    private Object sequenceId;
    private T headBuffer;
    /**
     * 
     */
    public DefaultHead() {
        // TODO Auto-generated constructor stub
    }

    /* (non-Javadoc)
     * @see me.huzorro.gateway.Head#setHeadLength(int)
     */
    @Override
    public void setHeadLength(long length) {
        this.headLength = length;
    }

    /* (non-Javadoc)
     * @see me.huzorro.gateway.Head#getHeadLength()
     */
    @Override
    public long getHeadLength() {
        return headLength;
    }

    /* (non-Javadoc)
     * @see me.huzorro.gateway.Head#setPacketLength(int)
     */
    @Override
    public void setPacketLength(long length) {
        this.packetLength = length;
    }

    /* (non-Javadoc)
     * @see me.huzorro.gateway.Head#getPacketLength()
     */
    @Override
    public long getPacketLength() {
        return packetLength;
    }

    /* (non-Javadoc)
     * @see me.huzorro.gateway.Head#setBodyLength(int)
     */
    @Override
    public void setBodyLength(long length) {
        this.bodyLength = length;
    }

    /* (non-Javadoc)
     * @see me.huzorro.gateway.Head#getBodyLength()
     */
    @Override
    public long getBodyLength() {
        return bodyLength;
    }

    /* (non-Javadoc)
     * @see me.huzorro.gateway.Head#setCommandId(java.lang.Object)
     */
    @Override
    public void setCommandId(Object commandId) {
        this.commandId = commandId;
    }

    /* (non-Javadoc)
     * @see me.huzorro.gateway.Head#getCommandId()
     */
    @Override
    public Object getCommandId() {
        return commandId;
    }


    /* (non-Javadoc)
     * @see me.huzorro.gateway.Head#setSequenceId(java.lang.Object)
     */
    @Override
    public void setSequenceId(Object transitionId) {
        this.sequenceId = transitionId;
    }

    /* (non-Javadoc)
     * @see me.huzorro.gateway.Head#getSequenceId()
     */
    @Override
    public Object getSequenceId() {
        return sequenceId;
    }

    /* (non-Javadoc)
     * @see me.huzorro.gateway.Head#setHeadBuffer(java.lang.Object)
     */
    @Override
    public void setHeadBuffer(T buffer) {
        this.headBuffer = buffer;
    }

    /* (non-Javadoc)
     * @see me.huzorro.gateway.Head#getHeadBuffer()
     */
    @Override
    public T getHeadBuffer() {
        return headBuffer;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return String
                .format("DefaultHead [headLength=%s, packetLength=%s, bodyLength=%s, commandId=%s, sequenceId=%s]",
                        headLength, packetLength, bodyLength, commandId,
                        sequenceId);
    }
    
}

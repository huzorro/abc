package me.huzorro.gateway;

import org.jboss.netty.buffer.ChannelBuffer;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public class DefaultHead<T extends ChannelBuffer> implements Header<T> {

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
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see me.huzorro.gateway.Head#getHeadLength()
     */
    @Override
    public long getHeadLength() {
        // TODO Auto-generated method stub
        return 0;
    }

    /* (non-Javadoc)
     * @see me.huzorro.gateway.Head#setPacketLength(int)
     */
    @Override
    public void setPacketLength(long length) {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see me.huzorro.gateway.Head#getPacketLength()
     */
    @Override
    public long getPacketLength() {
        // TODO Auto-generated method stub
        return 0;
    }

    /* (non-Javadoc)
     * @see me.huzorro.gateway.Head#setBodyLength(int)
     */
    @Override
    public void setBodyLength(long length) {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see me.huzorro.gateway.Head#getBodyLength()
     */
    @Override
    public long getBodyLength() {
        // TODO Auto-generated method stub
        return 0;
    }

    /* (non-Javadoc)
     * @see me.huzorro.gateway.Head#setCommandId(java.lang.Object)
     */
    @Override
    public void setCommandId(Object commandId) {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see me.huzorro.gateway.Head#getCommandId()
     */
    @Override
    public Object getCommandId() {
        // TODO Auto-generated method stub
        return null;
    }


    /* (non-Javadoc)
     * @see me.huzorro.gateway.Head#setSequenceId(java.lang.Object)
     */
    @Override
    public void setSequenceId(Object transitionId) {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see me.huzorro.gateway.Head#getSequenceId()
     */
    @Override
    public Object getSequenceId() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see me.huzorro.gateway.Head#setHeadBuffer(java.lang.Object)
     */
    @Override
    public void setHeadBuffer(T buffer) {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see me.huzorro.gateway.Head#getHeadBuffer()
     */
    @Override
    public T getHeadBuffer() {
        // TODO Auto-generated method stub
        return null;
    }

}

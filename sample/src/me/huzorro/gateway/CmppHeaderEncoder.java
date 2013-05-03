package me.huzorro.gateway;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public  class CmppHeaderEncoder extends OneToOneEncoder {

    /* (non-Javadoc)
     * @see org.jboss.netty.handler.codec.oneone.OneToOneEncoder#encode(org.jboss.netty.channel.ChannelHandlerContext, org.jboss.netty.channel.Channel, java.lang.Object)
     */
    @Override
    @SuppressWarnings("unchecked")
    protected Object encode(ChannelHandlerContext ctx, Channel channel,
            Object msg) throws Exception {
    	if(!(msg instanceof Message<?>)) return msg;
    	
        Message<ChannelBuffer> message = (Message<ChannelBuffer>) msg;
        ChannelBuffer headerBuffer = ChannelBuffers.dynamicBuffer();
        
        headerBuffer.writeInt((int) message.getHeader().getPacketLength());
        headerBuffer.writeInt(((Long) message.getHeader().getCommandId()).intValue());
        headerBuffer.writeInt(((Long) message.getHeader().getSequenceId()).intValue());
        message.getHeader().setHeadBuffer(headerBuffer);
        
        return message;
    }
}

package me.huzorro.gateway;

import org.apache.commons.lang.ArrayUtils;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

import com.google.common.primitives.Longs;

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
        Message<ChannelBuffer> message = (Message<ChannelBuffer>) msg;
        ChannelBuffer headerBuffer = ChannelBuffers.dynamicBuffer();
        byte[] totalLengthLongs = Longs.toByteArray(message.getHeader().getPacketLength());
        byte[] totalLengthUnsignedInts = 
                ArrayUtils.subarray(totalLengthLongs, 4, 8);
        headerBuffer.writeBytes(totalLengthUnsignedInts);
        
        byte[] commandIdLongs = 
                Longs.toByteArray(((Long) message.getHeader().getCommandId()).longValue());
        byte[] commandIdUnsignedInts = 
                ArrayUtils.subarray(commandIdLongs, 4, 8);
        headerBuffer.writeBytes(commandIdUnsignedInts);
        
        byte[] sequenceIdLongs = 
                Longs.toByteArray(((Long) message.getHeader().getSequenceId()).longValue());
        byte[] sequenceIdUnsignedInts = 
                ArrayUtils.subarray(sequenceIdLongs, 4, 8);
        headerBuffer.writeBytes(sequenceIdUnsignedInts);
        message.getHeader().setHeadBuffer(headerBuffer);
        
        return message;
    }
}

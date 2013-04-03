package me.huzorro.gateway;

import me.huzorro.gateway.cmpp.Head;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.FrameDecoder;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public class CmppHeaderDecoder extends FrameDecoder {

    public CmppHeaderDecoder() {
        this(true);
    }

    /**
     * @param unfold
     */
    public CmppHeaderDecoder(boolean unfold) {
        super(unfold);
    }

    /* (non-Javadoc)
     * @see org.jboss.netty.handler.codec.frame.FrameDecoder#decode(org.jboss.netty.channel.ChannelHandlerContext, org.jboss.netty.channel.Channel, org.jboss.netty.buffer.ChannelBuffer)
     */
    @Override
    protected Object decode(ChannelHandlerContext ctx, Channel channel,
            ChannelBuffer buffer) throws Exception {
        if(buffer.readableBytes() < Head.COMMANDID.getHeadLength()) return null;
        buffer.markReaderIndex();
        ChannelBuffer headBuffer = 
                buffer.readBytes(Head.COMMANDID.getHeadLength());
        Header<ChannelBuffer> header = new DefaultHead<ChannelBuffer>();
        header.setHeadBuffer(headBuffer.copy());
        
        header.setCommandId(headBuffer.readUnsignedInt());
        header.setHeadLength(Head.COMMANDID.getHeadLength());
        header.setPacketLength(headBuffer.readUnsignedInt());
        header.setSequenceId(headBuffer.readUnsignedInt());
        header.setBodyLength(header.getPacketLength() - header.getHeadLength());
        
        if(buffer.readableBytes() < header.getBodyLength()) {
            buffer.resetReaderIndex();
            return null;
        }
        
        ChannelBuffer bodyBuffer = 
                buffer.readBytes((int) header.getBodyLength());
        Message<ChannelBuffer> message = new DefaultMessage<ChannelBuffer>();
        message.setBodyBuffer(bodyBuffer);
        message.setHeader(header);
        return message;
    }
   

}

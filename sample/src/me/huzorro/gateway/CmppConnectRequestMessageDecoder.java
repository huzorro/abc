package me.huzorro.gateway;

import java.nio.charset.Charset;

import me.huzorro.gateway.cmpp.PacketStructure;
import me.huzorro.gateway.cmpp.PacketType;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneDecoder;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public class CmppConnectRequestMessageDecoder extends OneToOneDecoder {
    private PacketType packetType;
    /**
     * 
     */
    public CmppConnectRequestMessageDecoder() {
        this(PacketType.CMPPCONNECTREQUEST);
    }
    public CmppConnectRequestMessageDecoder(PacketType packetType) {
        this.packetType = packetType;
    }

    /* (non-Javadoc)
     * @see org.jboss.netty.handler.codec.oneone.OneToOneDecoder#decode(org.jboss.netty.channel.ChannelHandlerContext, org.jboss.netty.channel.Channel, java.lang.Object)
     */
    @Override
    @SuppressWarnings("unchecked")
    protected Object decode(ChannelHandlerContext ctx, Channel channel,
            Object msg) throws Exception {
        Message<ChannelBuffer> message = (Message<ChannelBuffer>) msg;
        long commandId = ((Long) message.getHeader().getCommandId()).longValue();
        if(packetType.getCommandId() != commandId) return msg;
        CmppConnectRequestMessage<ChannelBuffer> requestMessage = 
                new CmppConnectRequestMessage<ChannelBuffer>();
        requestMessage.setBodyBuffer(message.getBodyBuffer());
        requestMessage.setHeader(message.getHeader());
        
        ChannelBuffer bodyBuffer = message.getBodyBuffer().copy();
        
        ChannelBuffer sourceAddrBuffer = 
                bodyBuffer.readBytes(PacketStructure.ConnectRequest.SOURCEADDR.getLength());
        requestMessage.setSourceAddr(sourceAddrBuffer.toString(Charset.forName("GBK")));
        
        byte[] authenticatorSourceBytes = 
                new byte[PacketStructure.ConnectRequest.AUTHENTICATORSOURCE.getLength()];
        bodyBuffer.readBytes(authenticatorSourceBytes);
        
        requestMessage.setAuthenticatorSource(authenticatorSourceBytes);
        
        requestMessage.setVersion(bodyBuffer.readUnsignedByte());
        requestMessage.setTimestamp(bodyBuffer.readUnsignedInt());
        
        
        return requestMessage;
    }

}

package me.huzorro.gateway;

import me.huzorro.gateway.cmpp.CmppConnectRequest;
import me.huzorro.gateway.cmpp.CmppPacketType;
import me.huzorro.gateway.cmpp.PacketType;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
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
        this(CmppPacketType.CMPPCONNECTREQUEST);
    }
    public CmppConnectRequestMessageDecoder(PacketType packetType) {
        this.packetType = packetType;
    }

    /* (non-Javadoc)
     * @see org.jboss.netty.handler.codec.oneone.OneToOneDecoder#decode(org.jboss.netty.channel.ChannelHandlerContext, org.jboss.netty.channel.Channel, java.lang.Object)
     */
    @Override
    protected Object decode(ChannelHandlerContext ctx, Channel channel,
            Object msg) throws Exception {
        Message message = (Message) msg;
        long commandId = ((Long) message.getHeader().getCommandId()).longValue();
        if(packetType.getCommandId() != commandId) return msg;
        CmppConnectRequestMessage requestMessage = 
                new CmppConnectRequestMessage();
        requestMessage.setBodyBuffer(message.getBodyBuffer());
        requestMessage.setHeader(message.getHeader());
        
        ChannelBuffer bodyBuffer = ChannelBuffers.copiedBuffer(message.getBodyBuffer());
 
		requestMessage.setSourceAddr(bodyBuffer.readBytes(
				CmppConnectRequest.SOURCEADDR.getLength())
				.toString(GlobalVars.defaultTransportCharset).trim());
        
       
		requestMessage.setAuthenticatorSource(bodyBuffer.readBytes(
				CmppConnectRequest.AUTHENTICATORSOURCE.getLength())
				.array());
        
        requestMessage.setVersion(bodyBuffer.readUnsignedByte());
        requestMessage.setTimestamp(bodyBuffer.readUnsignedInt());
        
        
        return requestMessage;
    }

}

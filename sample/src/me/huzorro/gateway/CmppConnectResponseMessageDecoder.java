package me.huzorro.gateway;

import me.huzorro.gateway.cmpp.PacketStructure;
import me.huzorro.gateway.cmpp.PacketType;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneDecoder;

import com.sun.xml.internal.bind.v2.runtime.reflect.Lister.Pack;
import com.sun.xml.internal.ws.api.message.Packet;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public class CmppConnectResponseMessageDecoder extends OneToOneDecoder {
    private PacketType packetType;
    /**
     * 
     */
    public CmppConnectResponseMessageDecoder() {
        this(PacketType.CMPPCONNECTRESPONSE);
    }
    public CmppConnectResponseMessageDecoder(PacketType packetType) {
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
        long commandId = ((Integer) message.getHeader().getCommandId()).intValue();
        if(commandId != packetType.getCommandId()) return msg;
        CmppConnectResponseMessage<ChannelBuffer> responseMessage = new CmppConnectResponseMessage<ChannelBuffer>();
        
        responseMessage.setBodyBuffer(message.getBodyBuffer());
        responseMessage.setHeader(message.getHeader());
        
        ChannelBuffer bodyBuffer = message.getBodyBuffer().copy();
        
        responseMessage.setStatus(bodyBuffer.readUnsignedInt());
        byte[] authenticatorISMGBytes = new byte[PacketStructure.ConnectResponse.AUTHENTICATORISMG.getLength()];
        bodyBuffer.readBytes(authenticatorISMGBytes);
        responseMessage.setAuthenticatorISMG(authenticatorISMGBytes);
        responseMessage.setVersion(bodyBuffer.readUnsignedByte());
        return responseMessage;
    }

}

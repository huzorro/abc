package me.huzorro.gateway;

import java.nio.charset.Charset;

import me.huzorro.gateway.cmpp.PacketType;

import org.apache.commons.lang.ArrayUtils;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

import com.google.common.primitives.Longs;
import com.google.common.primitives.Shorts;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public class CmppConnectRequestMessageEncoder extends OneToOneEncoder {
    private PacketType packetType;
    /**
     * 
     */
    public CmppConnectRequestMessageEncoder() {
        this(PacketType.CMPPCONNECTREQUEST);
    }
    public CmppConnectRequestMessageEncoder(PacketType packetType) {
        this.packetType = packetType;
    }
    /* (non-Javadoc)
     * @see org.jboss.netty.handler.codec.oneone.OneToOneEncoder#encode(org.jboss.netty.channel.ChannelHandlerContext, org.jboss.netty.channel.Channel, java.lang.Object)
     */
    @Override
    @SuppressWarnings("unchecked")
    protected Object encode(ChannelHandlerContext ctx, Channel channel,
            Object msg) throws Exception {
        CmppConnectRequestMessage<ChannelBuffer> message = (CmppConnectRequestMessage<ChannelBuffer>) msg;
        long commandId = ((Long) message.getHeader().getCommandId()).longValue();
        if(commandId != packetType.getCommandId()) return msg;
        ChannelBuffer bodyBuffer = ChannelBuffers.dynamicBuffer();
        bodyBuffer.writeBytes(message.getSourceAddr().getBytes(GlobalVars.defaultTransportCharset));
        bodyBuffer.writeBytes(message.getAuthenticatorSource());
        
        byte[] versionBytes = Shorts.toByteArray(message.getVersion());
        byte[] versionUnsignedIns = 
                ArrayUtils.subarray(versionBytes, 1, 2);
        bodyBuffer.writeBytes(versionUnsignedIns);
        byte[] timestampBytes = Longs.toByteArray(message.getTimestamp());
        byte[] timestampUnsignedIns = 
                ArrayUtils.subarray(timestampBytes, 4, 8);
        bodyBuffer.writeBytes(timestampUnsignedIns);
        
        message.setBodyBuffer(bodyBuffer);
        
        ChannelBuffer messageBuffer = ChannelBuffers.dynamicBuffer();
        messageBuffer.writeBytes(message.getHeader().getHeadBuffer());
        messageBuffer.writeBytes(message.getBodyBuffer());
        
        return messageBuffer;
    }

}

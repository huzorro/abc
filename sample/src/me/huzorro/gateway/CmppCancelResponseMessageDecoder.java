/**
 * 
 */
package me.huzorro.gateway;

import me.huzorro.gateway.cmpp.PacketType;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneDecoder;

/**
 * @author huzorro
 *
 */
public class CmppCancelResponseMessageDecoder extends OneToOneDecoder {
	private PacketType packetType;
	
	public CmppCancelResponseMessageDecoder() {
		this(PacketType.CMPPCANCELRESPONSE);
	}

	public CmppCancelResponseMessageDecoder(PacketType packetType) {
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
        
        CmppCancelResponseMessage<ChannelBuffer> responseMessage = new CmppCancelResponseMessage<ChannelBuffer>();
        
        responseMessage.setBodyBuffer(message.getBodyBuffer());
        responseMessage.setHeader(message.getHeader());
        ChannelBuffer bodyBuffer = message.getBodyBuffer().copy();

        responseMessage.setSuccessId(bodyBuffer.readUnsignedInt());
		
        return responseMessage;
	}

}

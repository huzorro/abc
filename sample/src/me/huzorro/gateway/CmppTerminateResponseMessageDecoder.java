/**
 * 
 */
package me.huzorro.gateway;

import me.huzorro.gateway.cmpp.PacketStructure;
import me.huzorro.gateway.cmpp.PacketType;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneDecoder;

/**
 * @author huzorro
 *
 */
public class CmppTerminateResponseMessageDecoder extends OneToOneDecoder {
	private PacketType packetType;
	
	public CmppTerminateResponseMessageDecoder() {
		this(PacketType.CMPPTERMINATERESPONSE);
	}

	public CmppTerminateResponseMessageDecoder(PacketType packetType) {
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
        
        CmppTerminateResponseMessage<ChannelBuffer> responseMessage = new CmppTerminateResponseMessage<ChannelBuffer>();
        responseMessage.setBodyBuffer(message.getBodyBuffer());
        responseMessage.setHeader(message.getHeader());
                       
		return responseMessage;
	}

}

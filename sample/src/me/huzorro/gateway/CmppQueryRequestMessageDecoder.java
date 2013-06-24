/**
 * 
 */
package me.huzorro.gateway;

import me.huzorro.gateway.cmpp.CmppPacketType;
import me.huzorro.gateway.cmpp.CmppQueryRequest;
import me.huzorro.gateway.cmpp.PacketType;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneDecoder;

/**
 * @author huzorro(huzorro@gmail.com)
 *
 */
public class CmppQueryRequestMessageDecoder extends OneToOneDecoder {
	private PacketType packetType;
	
	public CmppQueryRequestMessageDecoder() {
		this(CmppPacketType.CMPPQUERYREQUEST);
	}

	public CmppQueryRequestMessageDecoder(PacketType packetType) {
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
        
        CmppQueryRequestMessage requestMessage = new CmppQueryRequestMessage();
       
        requestMessage.setBodyBuffer(message.getBodyBuffer());
        requestMessage.setHeader(message.getHeader());
        ChannelBuffer bodyBuffer = ChannelBuffers.copiedBuffer(message.getBodyBuffer());
        
		requestMessage.setTime(bodyBuffer.readBytes(
				CmppQueryRequest.TIME.getLength()).toString(
				GlobalVars.defaultTransportCharset));
		requestMessage.setQueryType(bodyBuffer.readUnsignedByte());
		requestMessage.setQueryCode(bodyBuffer.readBytes(
				CmppQueryRequest.QUERYCODE.getLength()).toString(
				GlobalVars.defaultTransportCharset));
		requestMessage.setReserve(bodyBuffer.readBytes(
				CmppQueryRequest.RESERVE.getLength()).toString(
				GlobalVars.defaultTransportCharset));
		
		return requestMessage;
	}

}

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

import com.sun.corba.se.impl.protocol.giopmsgheaders.RequestMessage;

/**
 * @author huzorro
 *
 */
public class CmppQueryResponseMessageDecoder extends OneToOneDecoder {
	private PacketType packetType;
	
	public CmppQueryResponseMessageDecoder() {
		this(PacketType.CMPPQUERYRESPONSE);
	}

	public CmppQueryResponseMessageDecoder(PacketType packetType) {
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
        
        CmppQueryResponseMessage<ChannelBuffer> responseMessage = new CmppQueryResponseMessage<ChannelBuffer>();
       
        responseMessage.setBodyBuffer(message.getBodyBuffer());
        responseMessage.setHeader(message.getHeader());
        ChannelBuffer bodyBuffer = message.getBodyBuffer().copy();		
        
		responseMessage.setTime(bodyBuffer.readBytes(
				PacketStructure.QueryResponse.TIME.getLength()).toString(
				GlobalVars.defaultTransportCharset));
		
		responseMessage.setQueryType(bodyBuffer.readUnsignedByte());
		responseMessage.setQueryCode(bodyBuffer.readBytes(
				PacketStructure.QueryResponse.QUERYCODE.getLength()).toString(
				GlobalVars.defaultTransportCharset));
		responseMessage.setMtTLMsg(bodyBuffer.readUnsignedInt());
		responseMessage.setMtTLUsr(bodyBuffer.readUnsignedInt());
		responseMessage.setMtScs(bodyBuffer.readUnsignedInt());
		responseMessage.setMtWT(bodyBuffer.readUnsignedInt());
		responseMessage.setMtFL(bodyBuffer.readUnsignedInt());
		responseMessage.setMoScs(bodyBuffer.readUnsignedInt());
		responseMessage.setMoWT(bodyBuffer.readUnsignedInt());
		responseMessage.setMoFL(bodyBuffer.readUnsignedInt());
		
		return responseMessage;
	}

}

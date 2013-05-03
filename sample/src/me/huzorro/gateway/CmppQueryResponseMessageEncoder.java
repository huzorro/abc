/**
 * 
 */
package me.huzorro.gateway;

import me.huzorro.gateway.cmpp.PacketStructure;
import me.huzorro.gateway.cmpp.PacketType;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

import com.google.common.primitives.Bytes;

/**
 * @author huzorro
 *
 */
public class CmppQueryResponseMessageEncoder extends OneToOneEncoder {
	private PacketType packetType;
	
	public CmppQueryResponseMessageEncoder() {
		this(PacketType.CMPPQUERYRESPONSE);
	}

	public CmppQueryResponseMessageEncoder(PacketType packetType) {
		this.packetType = packetType;
	}
	/* (non-Javadoc)
	 * @see org.jboss.netty.handler.codec.oneone.OneToOneEncoder#encode(org.jboss.netty.channel.ChannelHandlerContext, org.jboss.netty.channel.Channel, java.lang.Object)
	 */
	@Override
	@SuppressWarnings("unchecked")
	protected Object encode(ChannelHandlerContext ctx, Channel channel,
			Object msg) throws Exception {
		Message<ChannelBuffer> message = (Message<ChannelBuffer>) msg;
		long commandId = ((Long) message.getHeader().getCommandId())
				.longValue();
		if (commandId != packetType.getCommandId())
			return msg;
		CmppQueryResponseMessage<ChannelBuffer> responseMessage = (CmppQueryResponseMessage<ChannelBuffer>) message;

		ChannelBuffer bodyBuffer = ChannelBuffers.dynamicBuffer();
		
		bodyBuffer.writeBytes(Bytes.ensureCapacity(responseMessage.getTime()
				.getBytes(GlobalVars.defaultTransportCharset),
				PacketStructure.QueryResponse.TIME.getLength(), 0));
		
		bodyBuffer.writeByte(responseMessage.getQueryType());
		bodyBuffer.writeInt((int) responseMessage.getMtTLMsg());
		bodyBuffer.writeInt((int) responseMessage.getMtTLUsr());
		bodyBuffer.writeInt((int) responseMessage.getMtScs());
		bodyBuffer.writeInt((int) responseMessage.getMtWT());
		bodyBuffer.writeInt((int) responseMessage.getMtFL());
		bodyBuffer.writeInt((int) responseMessage.getMoScs());
		bodyBuffer.writeInt((int) responseMessage.getMoWT());
		bodyBuffer.writeInt((int) responseMessage.getMoFL());
		
        message.setBodyBuffer(bodyBuffer);
        
        ChannelBuffer messageBuffer = ChannelBuffers.dynamicBuffer();
        
        messageBuffer.writeBytes(message.getHeader().getHeadBuffer());
        messageBuffer.writeBytes(message.getBodyBuffer());
        return messageBuffer;
	}

}

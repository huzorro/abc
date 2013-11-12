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
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

import com.google.common.primitives.Bytes;

/**
 * @author huzorro(huzorro@gmail.com)
 * 
 */
public class CmppQueryRequestMessageEncoder extends OneToOneEncoder {
	private PacketType packetType;

	public CmppQueryRequestMessageEncoder() {
		this(CmppPacketType.CMPPQUERYREQUEST);
	}

	public CmppQueryRequestMessageEncoder(PacketType packetType) {
		this.packetType = packetType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.jboss.netty.handler.codec.oneone.OneToOneEncoder#encode(org.jboss
	 * .netty.channel.ChannelHandlerContext, org.jboss.netty.channel.Channel,
	 * java.lang.Object)
	 */
	@Override
	protected Object encode(ChannelHandlerContext ctx, Channel channel,
			Object msg) throws Exception {
	    if(!(msg instanceof Message)) return msg;
		Message message = (Message) msg;
		long commandId = ((Long) message.getHeader().getCommandId())
				.longValue();
		if (commandId != packetType.getCommandId())
			return msg;
		CmppQueryRequestMessage requestMessage = (CmppQueryRequestMessage) message;

		ChannelBuffer bodyBuffer = ChannelBuffers.dynamicBuffer();

		bodyBuffer.writeBytes(Bytes.ensureCapacity(requestMessage.getTime()
				.getBytes(GlobalVars.defaultTransportCharset),
				CmppQueryRequest.TIME.getLength(), 0));
		bodyBuffer.writeByte(requestMessage.getQueryType());
		bodyBuffer.writeBytes(Bytes.ensureCapacity(requestMessage
				.getQueryCode().getBytes(GlobalVars.defaultTransportCharset),
				CmppQueryRequest.QUERYCODE.getLength(), 0));
		bodyBuffer.writeBytes(Bytes.ensureCapacity(requestMessage.getReserve()
				.getBytes(GlobalVars.defaultTransportCharset),
				CmppQueryRequest.RESERVE.getLength(), 0));
		
        message.setBodyBuffer(bodyBuffer.copy().array());
        
        ChannelBuffer messageBuffer = ChannelBuffers.dynamicBuffer();
        
        messageBuffer.writeBytes(message.getHeader().getHeadBuffer());
        messageBuffer.writeBytes(message.getBodyBuffer());
        return messageBuffer;   	
	}

}

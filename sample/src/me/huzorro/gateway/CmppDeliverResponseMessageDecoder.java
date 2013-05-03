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
public class CmppDeliverResponseMessageDecoder extends OneToOneDecoder {
	private PacketType packetType;
	/**
	 * 
	 */
	public CmppDeliverResponseMessageDecoder() {
		this(PacketType.CMPPDELIVERRESPONSE);
	}

	public CmppDeliverResponseMessageDecoder(PacketType packetType) {
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
        
        CmppDeliverResponseMessage<ChannelBuffer> responseMessage = new CmppDeliverResponseMessage<ChannelBuffer>();
        responseMessage.setBodyBuffer(message.getBodyBuffer());
        responseMessage.setHeader(message.getHeader());
        
        ChannelBuffer bodyBuffer = message.getBodyBuffer().copy();
        
		responseMessage.setMsgId(DefaultMsgIdUtil.bytes2MsgId(bodyBuffer
				.readBytes(PacketStructure.DeliverResponse.MSGID.getLength())
				.array()));
		responseMessage.setResult(0L);
		
		return responseMessage;
	}

}

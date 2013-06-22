/**
 * 
 */
package me.huzorro.gateway;

import me.huzorro.gateway.cmpp.PacketType;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

/**
 * @author huzorro
 *
 */
public class CmppDeliverResponseMessageEncoder extends OneToOneEncoder {
	private PacketType packetType;
	/**
	 * 
	 */
	public CmppDeliverResponseMessageEncoder() {
		this(PacketType.CMPPDELIVERRESPONSE);
	}

	public CmppDeliverResponseMessageEncoder(PacketType packetType) {
		this.packetType = packetType;
	}
	/* (non-Javadoc)
	 * @see org.jboss.netty.handler.codec.oneone.OneToOneEncoder#encode(org.jboss.netty.channel.ChannelHandlerContext, org.jboss.netty.channel.Channel, java.lang.Object)
	 */
	@Override
	@SuppressWarnings("unchecked")
	protected Object encode(ChannelHandlerContext ctx, Channel channel,
			Object msg) throws Exception {
		if(!(msg instanceof Message<?>)) return msg;

    	Message<ChannelBuffer> message = (Message<ChannelBuffer>) msg;

        long commandId = ((Long) message.getHeader().getCommandId()).longValue();
        if(commandId != packetType.getCommandId()) return msg;

        CmppDeliverResponseMessage<ChannelBuffer> responseMessage = (CmppDeliverResponseMessage<ChannelBuffer>) msg;
        
        ChannelBuffer bodyBuffer = ChannelBuffers.dynamicBuffer();	
        bodyBuffer.writeBytes(DefaultMsgIdUtil.msgId2Bytes(responseMessage.getMsgId()));
        bodyBuffer.writeInt((int) responseMessage.getResult());
        
        message.setBodyBuffer(bodyBuffer);
        
        ChannelBuffer messageBuffer = ChannelBuffers.dynamicBuffer();
        messageBuffer.writeBytes(message.getHeader().getHeadBuffer());
        messageBuffer.writeBytes(message.getBodyBuffer());     
		return messageBuffer;
	}

}

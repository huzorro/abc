/**
 * 
 */
package me.huzorro.gateway;

import me.huzorro.gateway.cmpp.PacketType;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

/**
 * @author huzorro
 *
 */
public class CmppSubmitRequestMessageHeaderHandler extends OneToOneEncoder {
	private PacketType packetType;
	/**
	 * 
	 */
	public CmppSubmitRequestMessageHeaderHandler() {
		this(PacketType.CMPPSUBMITREQUEST);
	}
	public CmppSubmitRequestMessageHeaderHandler(PacketType packetType) {
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
        long commandId = ((Long) message.getHeader().getCommandId()).longValue();
        if(commandId != packetType.getCommandId()) return msg;
        
        if(message.getPacketType().getPacketStructures()[0].isFixPacketLength()) {
        	return msg;
        }        
        
        CmppSubmitRequestMessage<ChannelBuffer> requestMessage = (CmppSubmitRequestMessage<ChannelBuffer>) msg;
        
        
        message.getHeader().setBodyLength(message.getHeader().getBodyLength() + requestMessage.getMsgLength());
        message.getHeader().setPacketLength(message.getHeader().getPacketLength() + requestMessage.getMsgLength());
        return message;
	}

}

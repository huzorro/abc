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
public class CmppDeliverRequestMessageHeaderHandler extends OneToOneEncoder {
	private PacketType packetType;
	/**
	 * 
	 */
	public CmppDeliverRequestMessageHeaderHandler() {
		this(PacketType.CMPPDELIVERREQUEST);
	}

	public CmppDeliverRequestMessageHeaderHandler(PacketType packetType) {
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
        CmppDeliverRequestMessage<ChannelBuffer> requestMessage = new CmppDeliverRequestMessage<ChannelBuffer>();
        
        requestMessage.getHeader().setBodyLength(requestMessage.getHeader().getBodyLength() + requestMessage.getMsgLength());
        requestMessage.getHeader().setPacketLength(requestMessage.getHeader().getPacketLength() + requestMessage.getMsgLength());
		return message;
	}

}

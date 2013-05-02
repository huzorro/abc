/**
 * 
 */
package me.huzorro.gateway;

import me.huzorro.gateway.cmpp.Head;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

/**
 * @author huzorro
 *
 */
public class CmppHeaderHandler extends OneToOneEncoder {

	/**
	 * 
	 */
	public CmppHeaderHandler() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.jboss.netty.handler.codec.oneone.OneToOneEncoder#encode(org.jboss.netty.channel.ChannelHandlerContext, org.jboss.netty.channel.Channel, java.lang.Object)
	 */
	@Override
	@SuppressWarnings("unchecked")
	protected Object encode(ChannelHandlerContext ctx, Channel channel,
			Object msg) throws Exception {
        Message<ChannelBuffer> message = (Message<ChannelBuffer>) msg;
        if(!message.getPacketType().getPacketStructures()[0].isFixPacketLength()) {
        	return msg;
        }        
        
        Header<ChannelBuffer> header = new DefaultHead<ChannelBuffer>();
        header.setCommandId(message.getPacketType().getCommandId());
        header.setHeadLength(Head.COMMANDID.getHeadLength());
        header.setBodyLength(message.getPacketType().getPacketStructures()[0].getBodyLength());
        header.setPacketLength(header.getHeadLength() + header.getBodyLength());
        header.setSequenceId(GlobalVars.sequenceId.getAndIncrement());
        message.setHeader(header);
        return message;
	}

}

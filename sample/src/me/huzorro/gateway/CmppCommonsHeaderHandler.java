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
public class CmppCommonsHeaderHandler extends OneToOneEncoder {

	/**
	 * 
	 */
	public CmppCommonsHeaderHandler() {
		// TODO Auto-generated constructor stub
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
              
        Header<ChannelBuffer> header = new DefaultHead<ChannelBuffer>();
        header.setCommandId(message.getPacketType().getCommandId());
        header.setHeadLength(Head.COMMANDID.getHeadLength());
		header.setBodyLength(message.getPacketType().getPacketStructures().length > 0 ? message
				.getPacketType().getPacketStructures()[0].getBodyLength() : 0);
        header.setPacketLength(header.getHeadLength() + header.getBodyLength());
		header.setSequenceId(message.getRequest() != null ? message
				.getRequest().getHeader().getSequenceId()
				: GlobalVars.sequenceId.compareAndSet(Integer.MAX_VALUE, 0) 
				? GlobalVars.sequenceId.getAndIncrement()
				: GlobalVars.sequenceId.getAndIncrement());
        message.setHeader(header);
        message.setConfig(((Session)ctx.getChannel().getAttachment()).getConfig());
        return message;
	}

}

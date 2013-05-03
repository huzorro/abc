/**
 * 
 */
package me.huzorro.gateway;

import me.huzorro.gateway.cmpp.PacketType;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

/**
 * @author huzorro
 *
 */
public class CmppTerminateRequestMessageHandler extends
		SimpleChannelUpstreamHandler {
	private PacketType packetType;
	
	public CmppTerminateRequestMessageHandler() {
		this(PacketType.CMPPTERMINATEREQUEST);
	}
	
	public CmppTerminateRequestMessageHandler(PacketType packetType) {
		this.packetType = packetType;
	}

	/* (non-Javadoc)
	 * @see org.jboss.netty.channel.SimpleChannelUpstreamHandler#messageReceived(org.jboss.netty.channel.ChannelHandlerContext, org.jboss.netty.channel.MessageEvent)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		Message<ChannelBuffer> message = (Message<ChannelBuffer>) e.getMessage();
        long commandId = ((Long) message.getHeader().getCommandId()).longValue();
        if(commandId != packetType.getCommandId()){
            super.messageReceived(ctx, e);
            return;
        }
        CmppTerminateRequestMessage<ChannelBuffer> requestMessage = (CmppTerminateRequestMessage<ChannelBuffer>) message;
		CmppTerminateResponseMessage<ChannelBuffer> responseMessage = new CmppTerminateResponseMessage<ChannelBuffer>();
		
		responseMessage.setRequest(requestMessage);
		
		ctx.getChannel().write(responseMessage);
		ctx.getChannel().close();
	}
	
	
}

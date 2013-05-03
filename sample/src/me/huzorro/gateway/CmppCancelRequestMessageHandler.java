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
public class CmppCancelRequestMessageHandler extends
		SimpleChannelUpstreamHandler {
	private PacketType packetType;
	
	public CmppCancelRequestMessageHandler() {
		this(PacketType.CMPPCANCELREQUEST);
	}

	public CmppCancelRequestMessageHandler(PacketType packetType) {
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
        
        CmppCancelRequestMessage<ChannelBuffer> requestMessage = (CmppCancelRequestMessage<ChannelBuffer>) message;
		CmppCancelResponseMessage<ChannelBuffer> responseMessage = new CmppCancelResponseMessage<ChannelBuffer>();
		
		responseMessage.setRequest(requestMessage);
		
		responseMessage.setSuccessId(0L);
		
		ctx.getChannel().write(responseMessage);
		//TODO requestMessage write in queue
		
		super.messageReceived(ctx, e);
	}
	
}

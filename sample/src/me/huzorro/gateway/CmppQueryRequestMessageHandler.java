/**
 * 
 */
package me.huzorro.gateway;

import me.huzorro.gateway.cmpp.CmppPacketType;
import me.huzorro.gateway.cmpp.PacketType;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

/**
 * @author huzorro
 *
 */
public class CmppQueryRequestMessageHandler extends
		SimpleChannelUpstreamHandler {
	private PacketType packetType;
	
	public CmppQueryRequestMessageHandler() {
		this(CmppPacketType.CMPPQUERYREQUEST);
	}

	public CmppQueryRequestMessageHandler(PacketType packetType) {
		this.packetType = packetType;
	}

	/* (non-Javadoc)
	 * @see org.jboss.netty.channel.SimpleChannelUpstreamHandler#messageReceived(org.jboss.netty.channel.ChannelHandlerContext, org.jboss.netty.channel.MessageEvent)
	 */
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		Message message = (Message) e.getMessage();
        long commandId = ((Long) message.getHeader().getCommandId()).longValue();
        if(commandId != packetType.getCommandId()){
            super.messageReceived(ctx, e);
            return;
        }
        CmppQueryRequestMessage requestMessage = (CmppQueryRequestMessage) message;
		CmppQueryResponseMessage responseMessage = new CmppQueryResponseMessage();
		
		responseMessage.setRequest(requestMessage);
		
		//TODO 
		
		ctx.getChannel().write(responseMessage);
		
		super.messageReceived(ctx, e);
	}
	
	
}

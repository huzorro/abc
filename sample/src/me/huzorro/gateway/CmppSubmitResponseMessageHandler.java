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
 * @author huzorro(huzorro@gmail.com)
 *
 */
public class CmppSubmitResponseMessageHandler extends
		SimpleChannelUpstreamHandler {
	private PacketType packetType;
	/**
	 * 
	 */
	public CmppSubmitResponseMessageHandler() {
		this(CmppPacketType.CMPPSUBMITRESPONSE);
	}

	public CmppSubmitResponseMessageHandler(PacketType packetType) {
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
        
        CmppSubmitResponseMessage responseMessage = (CmppSubmitResponseMessage) message;
        
        ((Session) ctx.getChannel().getAttachment()).responseAndScheduleTask(responseMessage);
		super.messageReceived(ctx, e);
	}
	
	
}

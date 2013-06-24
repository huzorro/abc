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
public class CmppDeliverRequestMessageHandler extends
		SimpleChannelUpstreamHandler {
	private PacketType packetType;
	/**
	 * 
	 */
	public CmppDeliverRequestMessageHandler() {
		this(CmppPacketType.CMPPDELIVERREQUEST);
	}

	public CmppDeliverRequestMessageHandler(PacketType packetType) {
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
        
        CmppDeliverRequestMessage requestMessage = (CmppDeliverRequestMessage) message;
		CmppDeliverResponseMessage responseMessage = new CmppDeliverResponseMessage();
		
		responseMessage.setRequest(requestMessage);
		responseMessage.setMsgId(requestMessage.getMsgId());
		responseMessage.setResult(0L);
		
		ctx.getChannel().write(responseMessage);
		
		
		((Session) ctx.getChannel().getAttachment()).receive(requestMessage.setResponse(responseMessage));
		
		super.messageReceived(ctx, e);
	}

}

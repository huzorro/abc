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
public class CmppDeliverRequestMessageHandler extends
		SimpleChannelUpstreamHandler {
	private PacketType packetType;
	/**
	 * 
	 */
	public CmppDeliverRequestMessageHandler() {
		this(PacketType.CMPPDELIVERREQUEST);
	}

	public CmppDeliverRequestMessageHandler(PacketType packetType) {
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
        
        CmppDeliverRequestMessage<ChannelBuffer> requestMessage = (CmppDeliverRequestMessage<ChannelBuffer>) message;
		CmppDeliverResponseMessage<ChannelBuffer> responseMessage = new CmppDeliverResponseMessage<ChannelBuffer>();
		
		responseMessage.setRequest(requestMessage);
		responseMessage.setMsgId(requestMessage.getMsgId());
		responseMessage.setResult(0L);
		
		ctx.getChannel().write(responseMessage);
		
		
		requestMessage.setBodyBuffer(null);
		requestMessage.getHeader().setHeadBuffer(null);
		
		((Session) ctx.getChannel().getAttachment()).writeDeliver(requestMessage);
		super.messageReceived(ctx, e);
	}

}

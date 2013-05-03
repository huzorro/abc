/**
 * 
 */
package me.huzorro.gateway;

import me.huzorro.gateway.cmpp.PacketType;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

import com.sun.xml.internal.ws.addressing.model.MissingAddressingHeaderException;

/**
 * @author huzorro
 *
 */
public class CmppSubmitRequestMessageHandler extends
		SimpleChannelUpstreamHandler {
	private PacketType packetType;
	/**
	 * 
	 */
	public CmppSubmitRequestMessageHandler() {
		this(PacketType.CMPPSUBMITREQUEST);
	}

	public CmppSubmitRequestMessageHandler(PacketType packetType) {
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
        CmppSubmitRequestMessage<ChannelBuffer> requestMessage = (CmppSubmitRequestMessage<ChannelBuffer>) message;
		CmppSubmitResponseMessage<ChannelBuffer> responseMessage = new CmppSubmitResponseMessage<ChannelBuffer>();
		
		responseMessage.setRequest(requestMessage);
		responseMessage.setMsgId(new MsgId(((Session) ctx.getChannel()
				.getAttachment()).getConfig().getGateId()));
		responseMessage.setResult(0L);
		
		ctx.getChannel().write(responseMessage);
		
		//TODO requestMessage write in queue
		
		super.messageReceived(ctx, e);
	}
	
}

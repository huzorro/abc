package me.huzorro.gateway;

import me.huzorro.gateway.cmpp.PacketType;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public class CmppConnectRequestMessageHandler extends
        SimpleChannelUpstreamHandler {
	private PacketType packetType;
    /**
     * 
     */
    public CmppConnectRequestMessageHandler() {
    	this(PacketType.CMPPCONNECTREQUEST);
    }
    public CmppConnectRequestMessageHandler(PacketType packetType) {
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
        long commandId = ((Integer) message.getHeader().getCommandId()).intValue();
        if(commandId != packetType.getCommandId()){
            super.messageReceived(ctx, e);
            return;
        }        
        CmppConnectRequestMessage<ChannelBuffer> connectRequestMessage = (CmppConnectRequestMessage<ChannelBuffer>) message;
//        Session session = (Session) ctx.getChannel().getAttachment();
        
        connectRequestMessage.setChannelIds(session.getConfig().getChannelIds());
        
        
        if(connectRequestMessage.getStatus() == 0L) {
            session.getLoginFuture().setLogged();
        } else {
            session.close();
        }
        logger.info(message.toString());
        super.messageReceived(ctx, e);    	
        super.messageReceived(ctx, e);
    }
    

}

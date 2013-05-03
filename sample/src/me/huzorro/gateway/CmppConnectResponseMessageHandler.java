package me.huzorro.gateway;

import me.huzorro.gateway.cmpp.PacketType;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public class CmppConnectResponseMessageHandler extends
        SimpleChannelUpstreamHandler {
    private final static Logger logger = LoggerFactory.getLogger(CmppConnectResponseMessageHandler.class);
    private PacketType packetType;
    /**
     * 
     */
    public CmppConnectResponseMessageHandler() {
        this(PacketType.CMPPCONNECTRESPONSE);
    }
    public CmppConnectResponseMessageHandler(PacketType packetType) {
        this.packetType = packetType;
    }
    /* (non-Javadoc)
     * @see org.jboss.netty.channel.SimpleChannelUpstreamHandler#messageReceived(org.jboss.netty.channel.ChannelHandlerContext, org.jboss.netty.channel.MessageEvent)
     */
    @SuppressWarnings("unchecked")
    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
            throws Exception {
        Message<ChannelBuffer> message = (Message<ChannelBuffer>) e.getMessage();
        long commandId = ((Long) message.getHeader().getCommandId()).longValue();
        if(commandId != packetType.getCommandId()){
            super.messageReceived(ctx, e);
            return;
        }        
        CmppConnectResponseMessage<ChannelBuffer> connectResponseMessage = (CmppConnectResponseMessage<ChannelBuffer>) message;
        Session session = (Session) ctx.getChannel().getAttachment();
        
        logger.info(message.toString());
        
        if(connectResponseMessage.getStatus() == 0L) {
            session.getLoginFuture().setLogged();
            super.messageReceived(ctx, e);
        } else {
            session.close();
            return;
        }
    }
    
}

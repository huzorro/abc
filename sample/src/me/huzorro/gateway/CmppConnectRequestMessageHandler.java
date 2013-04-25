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
	private CmppServerSessionFactory<Session> sessionFactory;
    /**
     * 
     */
    public CmppConnectRequestMessageHandler(CmppServerSessionFactory<Session> sessionFactory) {
    	this(PacketType.CMPPCONNECTREQUEST, sessionFactory);
    }
    public CmppConnectRequestMessageHandler(PacketType packetType, CmppServerSessionFactory<Session> sessionFactory) {
    	this.packetType = packetType;
    	this.sessionFactory = sessionFactory;
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
        
        SessionConfig config = new CmppUpstreamServerSessionConfig();  
        
        //TODO 从sqllite获取该client端的配置信息
        
        connectRequestMessage.setChannelIds(config.getChannelIds());

        sessionFactory.setChannel(ctx.getChannel());
        sessionFactory.setConfig(config);
        
        Session session = sessionFactory.create();
        ctx.getChannel().setAttachment(session);
        
        
        
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

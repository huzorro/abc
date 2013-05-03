package me.huzorro.gateway;

import java.net.InetSocketAddress;
import java.util.Arrays;

import me.huzorro.gateway.cmpp.PacketType;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.ArrayUtils;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

import com.google.common.primitives.Bytes;
import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public class CmppConnectRequestMessageHandler extends
        SimpleChannelUpstreamHandler {
	private PacketType packetType;
	private CmppServerSessionFactory<Session> sessionFactory;
	private DefaultServerSessionConfigFactory<SessionConfig> sessionConfigFactory;
    /**
     * 
     */
    public CmppConnectRequestMessageHandler(
    		CmppServerSessionFactory<Session> sessionFactory,
    		DefaultServerSessionConfigFactory<SessionConfig> sessionConfigFactory) {
    	this(PacketType.CMPPCONNECTREQUEST, sessionFactory, sessionConfigFactory);
    }
    public CmppConnectRequestMessageHandler(
    		PacketType packetType, 
    		CmppServerSessionFactory<Session> sessionFactory,
    		DefaultServerSessionConfigFactory<SessionConfig> sessionConfigFactory
    		) {
    	this.packetType = packetType;
    	this.sessionFactory = sessionFactory;
    	this.sessionConfigFactory = sessionConfigFactory;
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
        CmppConnectRequestMessage<ChannelBuffer> connectRequestMessage = (CmppConnectRequestMessage<ChannelBuffer>) message;
		CmppConnectResponseMessage<ChannelBuffer> connectResponseMessage = new CmppConnectResponseMessage<ChannelBuffer>();
		
        connectResponseMessage.setRequest(connectRequestMessage);
		
		SessionConfig config = sessionConfigFactory
				.setHost(
						((InetSocketAddress) ctx.getChannel()
								.getRemoteAddress()).getAddress()
								.getHostAddress())
				.setUser(connectRequestMessage.getSourceAddr())
				.setVersion(connectRequestMessage.getVersion()).create();
		if(null == config) {
			ctx.getChannel().write(connectResponseMessage);
			ctx.getChannel().close();
			return;
		}
		if(null != config) {
	        byte[] userBytes = config.getUser().getBytes(GlobalVars.defaultTransportCharset);
	        byte[] passwdBytes = config.getPasswd().getBytes(GlobalVars.defaultTransportCharset);
	        String timestampStr = Long.toString(connectRequestMessage.getTimestamp());
	        timestampStr = String.format("%010d", timestampStr);

	        byte[] timestampBytes = timestampStr.getBytes(GlobalVars.defaultTransportCharset); 	
			byte[] authBytes = DigestUtils.md5(Bytes.concat(userBytes, new byte[9], passwdBytes, timestampBytes));
			
			if(!Arrays.equals(authBytes, connectRequestMessage.getAuthenticatorSource())) {
				ctx.getChannel().write(connectResponseMessage);
				ctx.getChannel().close();
				return;
			}
		}
		connectResponseMessage.setStatus(0L);

		connectResponseMessage.setAuthenticatorISMG(
				DigestUtils.md5(
						Bytes.concat(
								Ints.toByteArray((int) connectResponseMessage.getStatus()), 
								connectRequestMessage.getAuthenticatorSource(), 
								config.getPasswd().getBytes(GlobalVars.defaultTransportCharset)))				
				);
		
		ctx.getChannel().write(connectResponseMessage);
		
        sessionFactory.setChannel(ctx.getChannel());
        sessionFactory.setConfig(config);
        
        Session session = sessionFactory.create();
        ctx.getChannel().setAttachment(session);
        
        session.getLoginFuture().setLogged();
        
        super.messageReceived(ctx, e);

    }
    

}

package me.huzorro.gateway;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.Arrays;

import me.huzorro.gateway.cmpp.Head;
import me.huzorro.gateway.cmpp.PacketStructure;
import me.huzorro.gateway.cmpp.PacketType;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.ArrayUtils;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

import com.google.common.base.Strings;
import com.google.common.primitives.Bytes;
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
        long commandId = ((Integer) message.getHeader().getCommandId()).intValue();
        if(commandId != packetType.getCommandId()){
            super.messageReceived(ctx, e);
            return;
        }        
        CmppConnectRequestMessage<ChannelBuffer> connectRequestMessage = (CmppConnectRequestMessage<ChannelBuffer>) message;
		CmppConnectResponseMessage<ChannelBuffer> connectResponseMessage = new CmppConnectResponseMessage<ChannelBuffer>();
		
        Header<ChannelBuffer> header = new DefaultHead<ChannelBuffer>();
        header.setCommandId(PacketType.CMPPCONNECTRESPONSE.getCommandId());
        header.setHeadLength(Head.COMMANDID.getHeadLength());
        header.setBodyLength(PacketStructure.ConnectResponse.STATUS.getBodyLength());
        header.setPacketLength(header.getHeadLength() + header.getBodyLength());
        header.setSequenceId(connectRequestMessage.getHeader().getSequenceId());
        
        connectResponseMessage.setHeader(header);
		connectResponseMessage.setAuthenticatorISMG(new byte[16]);
		connectResponseMessage.setStatus(3);
		connectResponseMessage.setVersion(connectRequestMessage.getVersion());
		
		
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
	        byte[] userBytes = config.getUser().getBytes(Charset.forName("GBK"));
	        byte[] passwdBytes = config.getPasswd().getBytes(Charset.forName("GBK"));
	        String timestampStr = Long.toString(connectRequestMessage.getTimestamp());
	        timestampStr = String.format("%1$s%2$s", Strings.repeat("0", 10 - timestampStr.length()), timestampStr);
	        
	        byte[] timestampBytes = timestampStr.getBytes(Charset.forName("GBK")); 	
			byte[] authStr = DigestUtils.md5(Bytes.concat(userBytes, new byte[9], passwdBytes, timestampBytes));
			
			if(!Arrays.equals(authStr, connectRequestMessage.getAuthenticatorSource())) {
				ctx.getChannel().write(connectResponseMessage);
				ctx.getChannel().close();
				return;
			}
		}
		
		connectResponseMessage.setStatus(0L);

        byte[] statusBytes = Longs.toByteArray(connectResponseMessage.getStatus());
        byte[] statusUnsignedIns = ArrayUtils.subarray(statusBytes, 4, 8);
		
		connectResponseMessage.setAuthenticatorISMG(
				DigestUtils.md5(
						Bytes.concat(
								statusUnsignedIns, 
								connectRequestMessage.getAuthenticatorSource(), 
								config.getPasswd().getBytes(Charset.forName("GBK"))))				
				);
		
		ctx.getChannel().write(connectResponseMessage);
		
        connectRequestMessage.setChannelIds(config.getChannelIds());

        sessionFactory.setChannel(ctx.getChannel());
        sessionFactory.setConfig(config);
        
        Session session = sessionFactory.create();
        ctx.getChannel().setAttachment(session);
        
        session.getLoginFuture().setLogged();
        
        super.messageReceived(ctx, e);

    }
    

}

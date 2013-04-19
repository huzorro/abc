package me.huzorro.gateway;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public class CmppConnectRequestMessageHandler extends
        SimpleChannelUpstreamHandler {

    /**
     * 
     */
    public CmppConnectRequestMessageHandler() {
        // TODO Auto-generated constructor stub
    }

    /* (non-Javadoc)
     * @see org.jboss.netty.channel.SimpleChannelUpstreamHandler#messageReceived(org.jboss.netty.channel.ChannelHandlerContext, org.jboss.netty.channel.MessageEvent)
     */
    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
            throws Exception {
        // TODO Auto-generated method stub
        super.messageReceived(ctx, e);
    }
    

}

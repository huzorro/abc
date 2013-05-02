package me.huzorro.gateway;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public class CmppUpstreamClientChannelPipelineFactory implements
        ChannelPipelineFactory {

    /**
     * 
     */
    public CmppUpstreamClientChannelPipelineFactory() {
        // TODO Auto-generated constructor stub
    }

    /* (non-Javadoc)
     * @see org.jboss.netty.channel.ChannelPipelineFactory#getPipeline()
     */
    @Override
    public ChannelPipeline getPipeline() throws Exception {
        ChannelPipeline pipeline = Channels.pipeline();
        
        pipeline.addLast("CmppHeaderDecoder", new CmppHeaderDecoder());


        pipeline.addLast("CmppConnectResponseMessageDecoder", new CmppConnectResponseMessageDecoder());
        pipeline.addLast("CmppConnectRequestMessageEncoder", new CmppConnectRequestMessageEncoder());    
        
        pipeline.addLast("CmppHeaderEncoder", new CmppHeaderEncoder());
        pipeline.addLast("CmppHeaderHandler", new CmppHeaderHandler());
        
        pipeline.addLast("CmppConnectResponseMessageHandler", new CmppConnectResponseMessageHandler());
        return pipeline;
    }

}

/**
 * 
 */
package me.huzorro.gateway;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;

/**
 * @author huzorro
 *
 */
public class CmppUpstreamServerChannelPipelineFactory implements
		ChannelPipelineFactory {
	private CmppServerSessionFactory<Session> sessionFactory;
	/**
	 * 
	 */
	public CmppUpstreamServerChannelPipelineFactory(CmppServerSessionFactory<Session> sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/* (non-Javadoc)
	 * @see org.jboss.netty.channel.ChannelPipelineFactory#getPipeline()
	 */
	@Override
	public ChannelPipeline getPipeline() throws Exception {
        ChannelPipeline pipeline = Channels.pipeline();
        
        pipeline.addLast("CmppHeaderDecoder", new CmppHeaderDecoder());


        pipeline.addLast("CmppConnectRequestMessageDecoder", new CmppConnectRequestMessageDecoder());
        pipeline.addLast("CmppConnectResponseMessageEncoder", new CmppConnectResponseMessageEncoder());    
        
        pipeline.addLast("CmppHeaderEncoder", new CmppHeaderEncoder());
        
        pipeline.addLast("CmppConnectRequestMessageHandler", new CmppConnectRequestMessageHandler(sessionFactory));
        return pipeline;
	}

}

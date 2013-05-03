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
	private DefaultServerSessionConfigFactory<SessionConfig> configFactory;
	/**
	 * 
	 */
	public CmppUpstreamServerChannelPipelineFactory(
			CmppServerSessionFactory<Session> sessionFactory,
			DefaultServerSessionConfigFactory<SessionConfig> configFactory) {
		this.sessionFactory = sessionFactory;
		this.configFactory = configFactory;
	}

	/* (non-Javadoc)
	 * @see org.jboss.netty.channel.ChannelPipelineFactory#getPipeline()
	 */
	@Override
	public ChannelPipeline getPipeline() throws Exception {
        ChannelPipeline pipeline = Channels.pipeline();
        
        pipeline.addLast("CmppHeaderDecoder", new CmppHeaderDecoder());


//        pipeline.addLast("CmppSubmitResponseMessageDecoder", new CmppSubmitResponseMessageDecoder());
//        pipeline.addLast("CmppSubmitRequestMessageEncoder", new CmppSubmitRequestMessageEncoder());
        
        
//        pipeline.addLast("CmppDeliverRequestMessageDecoder", new CmppDeliverRequestMessageDecoder());
//        pipeline.addLast("CmppDeliverResponseMessageEncoder", new CmppDeliverResponseMessageEncoder());
        
        pipeline.addLast("CmppDeliverResponseMessageDecoder", new CmppDeliverResponseMessageDecoder());
        pipeline.addLast("CmppDeliverRequestMessageEncoder", new CmppDeliverRequestMessageEncoder());
        
//        pipeline.addLast("CmppConnectResponseMessageDecoder", new CmppConnectResponseMessageDecoder());
//        pipeline.addLast("CmppConnectRequestMessageEncoder", new CmppConnectRequestMessageEncoder());    
        
        pipeline.addLast("CmppConnectRequestMessageDecoder", new CmppConnectRequestMessageDecoder());
        pipeline.addLast("CmppConnectResponseMessageEncoder", new CmppConnectResponseMessageEncoder());

//        pipeline.addLast("CmppCancelResponseMessageDecoder", new CmppCancelResponseMessageDecoder());
//        pipeline.addLast("CmppCancelRequestMessageEncoder", new CmppCancelRequestMessageEncoder());
        
//        pipeline.addLast("CmppQueryResponseMessageDecoder", new CmppQueryResponseMessageDecoder());
//        pipeline.addLast("CmppQueryRequestMessageEncoder", new CmppQueryRequestMessageEncoder());
        
//        pipeline.addLast("CmppActiveTestResponseMessageDecoder", new CmppActiveTestResponseMessageDecoder());
//        pipeline.addLast("CmppActiveTestRequestMessageEncoder", new CmppActiveTestRequestMessageEncoder());
        
        pipeline.addLast("CmppActiveTestRequestMessageDecoder", new CmppActiveTestRequestMessageDecoder());
        pipeline.addLast("CmppActiveTestResponseMessageEncoder", new CmppActiveTestResponseMessageEncoder());

//        pipeline.addLast("CmppTerminateResponseMessageDecoder", new CmppTerminateResponseMessageDecoder());
//        pipeline.addLast("CmppTerminateRequestMessageEncoder", new CmppTerminateRequestMessageEncoder());
        
        pipeline.addLast("CmppTerminateRequestMessageDecoder", new CmppTerminateRequestMessageDecoder());
        pipeline.addLast("CmppTerminateResponseMessageEncoder", new CmppTerminateResponseMessageEncoder());

        pipeline.addLast("CmppHeaderEncoder", new CmppHeaderEncoder());          
        pipeline.addLast("CmppDeliverRequestMessageHeaderHandler", new CmppDeliverRequestMessageHeaderHandler());
        pipeline.addLast("CmppCommonsHeaderHandler", new CmppCommonsHeaderHandler());
        
//        pipeline.addLast("CmppSubmitResponseMessageHandler", new CmppSubmitResponseMessageHandler());
        
        pipeline.addLast("CmppDeliverResponseMessageHandler", new CmppDeliverResponseMessageHandler());
        pipeline.addLast("CmppConnectResponseMessageHandler", new CmppConnectResponseMessageHandler());
        
//        pipeline.addLast("CmppConnectRequestMessageHandler", new CmppConnectRequestMessageHandler(sessionFactory, configFactory));
        
//        pipeline.addLast("CmppCancelResponseMessageHandler", new CmppCancelResponseMessageHandler());
//        pipeline.addLast("CmppQueryResponseMessageHandler", new CmppQueryResponseMessageHandler());
        pipeline.addLast("CmppActiveTestRequestMessageHandler", new CmppActiveTestRequestMessageHandler());
        pipeline.addLast("CmppTerminateRequestMessageHandler", new CmppTerminateRequestMessageHandler());
        
        
        return pipeline;
	}

}

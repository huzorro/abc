package me.huzorro.gateway;

import java.util.concurrent.TimeUnit;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.handler.timeout.IdleStateHandler;
import org.jboss.netty.util.HashedWheelTimer;
import org.jboss.netty.util.Timer;

/**
 * @author huzorro(huzorro@gmail.com)
 *
 */
public class CmppDownstreamClientChannelPipelineFactory implements ChannelPipelineFactory {
	private final Timer timer;
	private final SessionConfig config;
	/**
	 * 
	 */
	public CmppDownstreamClientChannelPipelineFactory(SessionConfig config) {
		this(config, new HashedWheelTimer());
	}
	
	
	public CmppDownstreamClientChannelPipelineFactory(SessionConfig config,
			Timer timer) {
		this.timer = timer;
		this.config = config;
	}


	@Override
	public ChannelPipeline getPipeline() throws Exception {
        ChannelPipeline pipeline = Channels.pipeline();
        
        pipeline.addLast("IdleStateHandler", new IdleStateHandler(timer, 0, 0, config.getIdleTime(), TimeUnit.SECONDS));
        pipeline.addLast("CmppIdleStateHandler", new CmppIdleStateHandler());
        
        pipeline.addLast("CmppHeaderDecoder", new CmppHeaderDecoder());
        
        pipeline.addLast("CmppSubmitResponseMessageDecoder", new CmppSubmitResponseMessageDecoder());
        pipeline.addLast("CmppSubmitRequestMessageEncoder", new CmppSubmitRequestMessageEncoder());
        
        pipeline.addLast("CmppConnectResponseMessageDecoder", new CmppConnectResponseMessageDecoder());
        pipeline.addLast("CmppConnectRequestMessageEncoder", new CmppConnectRequestMessageEncoder());
        
        pipeline.addLast("CmppCancelResponseMessageDecoder", new CmppCancelResponseMessageDecoder());
        pipeline.addLast("CmppCancelRequestMessageEncoder", new CmppCancelRequestMessageEncoder());
        
        pipeline.addLast("CmppQueryResponseMessageDecoder", new CmppQueryResponseMessageDecoder());
        pipeline.addLast("CmppQueryRequestMessageEncoder", new CmppQueryRequestMessageEncoder());
        
        pipeline.addLast("CmppActiveTestResponseMessageDecoder", new CmppActiveTestResponseMessageDecoder());
        pipeline.addLast("CmppActiveTestRequestMessageEncoder", new CmppActiveTestRequestMessageEncoder());
        
        pipeline.addLast("CmppTerminateResponseMessageDecoder", new CmppTerminateResponseMessageDecoder());
        pipeline.addLast("CmppTerminateRequestMessageEncoder", new CmppTerminateRequestMessageEncoder());
        
        pipeline.addLast("CmppHeaderEncoder", new CmppHeaderEncoder());          
        pipeline.addLast("CmppSubmitRequestMessageHeaderHandler", new CmppSubmitRequestMessageHeaderHandler());
        pipeline.addLast("CmppCommonsHeaderHandler", new CmppCommonsHeaderHandler());
        pipeline.addLast("CmppCommonsMessageHandler", new CmppCommonsMessageHandler());
        
	    pipeline.addLast("CmppSubmitResponseMessageHandler", new CmppSubmitResponseMessageHandler());
	    pipeline.addLast("CmppConnectResponseMessageHandler", new CmppConnectResponseMessageHandler());
	    pipeline.addLast("CmppCancelResponseMessageHandler", new CmppCancelResponseMessageHandler());
	    pipeline.addLast("CmppQueryResponseMessageHandler", new CmppQueryResponseMessageHandler());
	    pipeline.addLast("CmppActiveTestResponseMessageHandler", new CmppActiveTestResponseMessageHandler());
	    pipeline.addLast("CmppTerminateResponseMessageHandler", new CmppTerminateResponseMessageHandler());
		return pipeline;
	}

}

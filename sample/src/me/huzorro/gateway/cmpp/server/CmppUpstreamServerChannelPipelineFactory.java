/**
 * 
 */
package me.huzorro.gateway.cmpp.server;

import java.util.concurrent.TimeUnit;

import me.huzorro.gateway.CmppActiveTestRequestMessageDecoder;
import me.huzorro.gateway.CmppActiveTestRequestMessageHandler;
import me.huzorro.gateway.CmppActiveTestResponseMessageEncoder;
import me.huzorro.gateway.CmppCommonsHeaderHandler;
import me.huzorro.gateway.CmppCommonsMessageHandler;
import me.huzorro.gateway.CmppConnectRequestMessageDecoder;
import me.huzorro.gateway.CmppConnectRequestMessageHandler;
import me.huzorro.gateway.CmppConnectResponseMessageEncoder;
import me.huzorro.gateway.CmppDeliverRequestMessageEncoder;
import me.huzorro.gateway.CmppDeliverRequestMessageHeaderHandler;
import me.huzorro.gateway.CmppDeliverResponseMessageDecoder;
import me.huzorro.gateway.CmppDeliverResponseMessageHandler;
import me.huzorro.gateway.CmppHeaderDecoder;
import me.huzorro.gateway.CmppHeaderEncoder;
import me.huzorro.gateway.CmppTerminateRequestMessageDecoder;
import me.huzorro.gateway.CmppTerminateRequestMessageHandler;
import me.huzorro.gateway.CmppTerminateResponseMessageEncoder;
import me.huzorro.gateway.DefaultServerSessionConfigFactory;
import me.huzorro.gateway.DefaultServerSessionFactory;
import me.huzorro.gateway.Session;
import me.huzorro.gateway.SessionConfig;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.handler.timeout.IdleStateHandler;
import org.jboss.netty.util.HashedWheelTimer;
import org.jboss.netty.util.Timer;
/**
 * 
 * @author huzorro(huzorro@gmail.com)
 *
 */
public class CmppUpstreamServerChannelPipelineFactory implements
		ChannelPipelineFactory {
	private DefaultServerSessionFactory<Session> sessionFactory;
	private DefaultServerSessionConfigFactory<SessionConfig> configFactory;
	private SessionConfig config;
	private Timer timer;
	/**
	 * 
	 */
	public CmppUpstreamServerChannelPipelineFactory(
			CmppServerSessionFactory<Session> sessionFactory,
			DefaultServerSessionConfigFactory<SessionConfig> configFactory,
			SessionConfig config) {
		this(sessionFactory, configFactory, config, new HashedWheelTimer());
	}
	

	/**
	 * @param sessionFactory
	 * @param configFactory
	 * @param config
	 * @param timer
	 */
	public CmppUpstreamServerChannelPipelineFactory(
			DefaultServerSessionFactory<Session> sessionFactory,
			DefaultServerSessionConfigFactory<SessionConfig> configFactory,
			SessionConfig config, Timer timer) {
		this.sessionFactory = sessionFactory;
		this.configFactory = configFactory;
		this.config = config;
		this.timer = timer;
	}


	/* (non-Javadoc)
	 * @see org.jboss.netty.channel.ChannelPipelineFactory#getPipeline()
	 */
	@Override
	public ChannelPipeline getPipeline() throws Exception {
        ChannelPipeline pipeline = Channels.pipeline();
        
		pipeline.addLast("IdleStateHandler", new IdleStateHandler(timer, 0, 0,
				config.getIdleTime(), TimeUnit.SECONDS));
		pipeline.addLast("CmppServerIdleStateHandler", new CmppServerIdleStateHandler());
		
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
        pipeline.addLast("CmppCommonsMessageHandler", new CmppCommonsMessageHandler());

//        pipeline.addLast("CmppSubmitResponseMessageHandler", new CmppSubmitResponseMessageHandler());
        
        pipeline.addLast("CmppDeliverResponseMessageHandler", new CmppDeliverResponseMessageHandler());
//        pipeline.addLast("CmppConnectResponseMessageHandler", new CmppConnectResponseMessageHandler());
        
        pipeline.addLast("CmppConnectRequestMessageHandler", new CmppConnectRequestMessageHandler(sessionFactory, configFactory));
        
//        pipeline.addLast("CmppCancelResponseMessageHandler", new CmppCancelResponseMessageHandler());
//        pipeline.addLast("CmppQueryResponseMessageHandler", new CmppQueryResponseMessageHandler());
        pipeline.addLast("CmppActiveTestRequestMessageHandler", new CmppActiveTestRequestMessageHandler());
        pipeline.addLast("CmppTerminateRequestMessageHandler", new CmppTerminateRequestMessageHandler());
        
        
        return pipeline;
	}

}

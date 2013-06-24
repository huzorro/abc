/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package me.huzorro.gateway.telnet;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import me.huzorro.gateway.DefaultCommandService;
import me.huzorro.gateway.DefaultServerSessionConfigFactory;
import me.huzorro.gateway.DefaultServerSessionFactory;
import me.huzorro.gateway.Session;
import me.huzorro.gateway.SessionConfig;

import org.apache.commons.lang.StringUtils;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Handles a server-side channel.
 */
public class TelnetServerHandler extends SimpleChannelUpstreamHandler {

    private static final Logger logger = LoggerFactory.getLogger(TelnetServerHandler.class); 
    private boolean loggedin;
    private short trylogin;
	private DefaultServerSessionFactory<Session> sessionFactory;
	private DefaultServerSessionConfigFactory<SessionConfig> sessionConfigFactory;
	
	
    /**
	 * @param sessionFactory
	 * @param sessionConfigFactory
	 */
	public TelnetServerHandler(
			DefaultServerSessionFactory<Session> sessionFactory,
			DefaultServerSessionConfigFactory<SessionConfig> sessionConfigFactory) {
		this.sessionFactory = sessionFactory;
		this.sessionConfigFactory = sessionConfigFactory;
	}

	@Override
    public void channelConnected(
            ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        // Send greeting for a new connection.
        e.getChannel().write(
                "Welcome to " + InetAddress.getLocalHost().getHostName() + "!\r\n");
        e.getChannel().write("It is " + new Date() + " now.\r\n");
        e.getChannel().write("login:");
    }

    @Override
    public void messageReceived(
            ChannelHandlerContext ctx, MessageEvent e)  throws Exception {
    	
        // Cast to a String first.
        // We know it is a String because we put some codec in TelnetPipelineFactory.
        String request = (String) e.getMessage();
        
        // Generate and write a response.
        String response;
        boolean close = false;
        if (request.length() == 0) {
            response = "Please type something.\r\n";
        } else if (request.toLowerCase().equals("bye")) {
            response = "Have a good day!\r\n";
            close = true;
        } else {
            response = "Did you say '" + request + "'?\r\n";
        }
        if(!loggedin) {
        	++trylogin;
        	if(trylogin > 3) {
        		response = "login failed\r\n";
        		close = true;
        	} else {
            	String[] auth = request.split("@");
            	if(auth.length != 3) response = "login:";
            	if(auth.length == 3) {
            		SessionConfig config = sessionConfigFactory
            		.setHost(((InetSocketAddress) ctx.getChannel()
    								.getRemoteAddress()).getAddress()
    								.getHostAddress())
    				.setUser(auth[0])
    				.setVersion(Short.valueOf(auth[2])).create();
            		
            		if(config == null || !config.getPasswd().equals(auth[1])) {
            			response = "login:";
            		} else {

            	        sessionFactory.setChannel(ctx.getChannel());
            	        sessionFactory.setConfig(config);
            	        
            	        Session session = sessionFactory.create();
            	        if(session != null) {
	            	        ctx.getChannel().setAttachment(session);            	        
	            	        session.getLoginFuture().setSuccess();
	            			response = "login success\r\n";
	            			loggedin = true;
            	        } else {
            	        	response = "out of the max number of connections";
            	        	close = true;
            	        }
            		}
            	}        		
        	}

        } else {
        	List<String> list = new ArrayList<String>(Arrays.asList(StringUtils.split(request)));
        	List<String> result = null; 
        	if(list.remove("help")) result = DefaultCommandService.help((String[])list.toArray(new String[list.size()]));
        	if(list.remove("start"))  result = DefaultCommandService.start((String[])list.toArray(new String[list.size()]));
        	if(list.remove("stop")) result = DefaultCommandService.stop((String[]) list.toArray(new String[list.size()]));
        	if(list.remove("status")) result = DefaultCommandService.status((String[]) list.toArray(new String[list.size()]));
        	if(list.remove("kick")) result = DefaultCommandService.kick((String[]) list.toArray(new String[list.size()]));
        	if(list.remove("clear")) result = DefaultCommandService.clear((String[]) list.toArray(new String[list.size()]));
        	if(list.remove("bye")) result = DefaultCommandService.bye((String[]) list.toArray(new String[list.size()]));
        	if(list.remove("shutdown")) result = DefaultCommandService.shutdown((String[]) list.toArray(new String[list.size()]));
        	if(result != null) {
	        	for(String info : result) {
	        		response += info;
	        		response += "\r\n";
	        	}
        	} else {
        		response = "command not found\r\n";
        	}
        
        }
        // We do not need to write a ChannelBuffer here.
        // We know the encoder inserted at TelnetPipelineFactory will do the conversion.
        ChannelFuture future = e.getChannel().write(response);

        // Close the connection after sending 'Have a good day!'
        // if the client has sent 'bye'.
        if (close) {
            future.addListener(ChannelFutureListener.CLOSE);
        }
    }

    @Override
    public void exceptionCaught(
            ChannelHandlerContext ctx, ExceptionEvent e) {
        logger.error(
                "Unexpected exception from downstream. {}",
                e.getCause());
        e.getChannel().close();
    }
}

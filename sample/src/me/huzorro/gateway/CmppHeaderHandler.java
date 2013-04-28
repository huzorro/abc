/**
 * 
 */
package me.huzorro.gateway;

import me.huzorro.gateway.cmpp.Head;
import me.huzorro.gateway.cmpp.PacketStructure;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

/**
 * @author huzorro
 *
 */
public class CmppHeaderHandler extends OneToOneEncoder {

	/**
	 * 
	 */
	public CmppHeaderHandler() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.jboss.netty.handler.codec.oneone.OneToOneEncoder#encode(org.jboss.netty.channel.ChannelHandlerContext, org.jboss.netty.channel.Channel, java.lang.Object)
	 */
	@Override
	@SuppressWarnings("unchecked")
	protected Object encode(ChannelHandlerContext ctx, Channel channel,
			Object msg) throws Exception {
        Message<ChannelBuffer> message = (Message<ChannelBuffer>) msg;
        
        long commandId = message.getPacketType().getCommandId();
        
        Header<ChannelBuffer> header = new DefaultHead<ChannelBuffer>();
        header.setCommandId(message.getPacketType().getCommandId());
        header.setHeadLength(Head.COMMANDID.getHeadLength());
        header.setBodyLength(message.getPacketType().getPacketStructure().getBodyLength());
        header.setPacketLength(header.getHeadLength() + header.getBodyLength());
        header.setSequenceId(sequenceId.getAndIncrement());
        
        
        if(commandId != packetType.getCommandId()){
            super.messageReceived(ctx, e);
            return;
        } 		
        Header<ChannelBuffer> header = new DefaultHead<ChannelBuffer>();
        header.setCommandId(packetType.getCommandId());
        header.setHeadLength(Head.COMMANDID.getHeadLength());
        header.setBodyLength(PacketStructure.ConnectRequest.SOURCEADDR.getBodyLength());
        header.setPacketLength(header.getHeadLength() + header.getBodyLength());
        header.setSequenceId(sequenceId.getAndIncrement());
        return null;
	}

}

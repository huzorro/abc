/**
 * 
 */
package me.huzorro.gateway;

import me.huzorro.gateway.cmpp.CmppPacketType;
import me.huzorro.gateway.cmpp.CmppSubmitRequest;
import me.huzorro.gateway.cmpp.PacketType;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneDecoder;

/**
 * @author huzorro(huzorro@gmail.com)
 *
 */
public class CmppSubmitResponseMessageDecoder extends OneToOneDecoder {
	private PacketType packetType;
	/**
	 * 
	 */
	public CmppSubmitResponseMessageDecoder() {
		this(CmppPacketType.CMPPSUBMITRESPONSE);
	}
	public CmppSubmitResponseMessageDecoder(PacketType packetType) {
		this.packetType = packetType;
	}

	/* (non-Javadoc)
	 * @see org.jboss.netty.handler.codec.oneone.OneToOneDecoder#decode(org.jboss.netty.channel.ChannelHandlerContext, org.jboss.netty.channel.Channel, java.lang.Object)
	 */
	@Override
	protected Object decode(ChannelHandlerContext ctx, Channel channel,
			Object msg) throws Exception {
		Message message = (Message) msg;
        long commandId = ((Long) message.getHeader().getCommandId()).longValue();
        if(packetType.getCommandId() != commandId) return msg;
        
        CmppSubmitResponseMessage responseMessage = new CmppSubmitResponseMessage();
        responseMessage.setBodyBuffer(message.getBodyBuffer());
        responseMessage.setHeader(message.getHeader());
        
        ChannelBuffer bodyBuffer = ChannelBuffers.copiedBuffer(message.getBodyBuffer());
                       
        responseMessage.setMsgId(DefaultMsgIdUtil.bytes2MsgId(bodyBuffer
				.readBytes(CmppSubmitRequest.MSGID.getLength())
				.array()));
		responseMessage.setResult(bodyBuffer.readUnsignedInt());
		
		return responseMessage;
	}

}

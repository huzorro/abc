/**
 * 
 */
package me.huzorro.gateway;

import me.huzorro.gateway.cmpp.PacketStructure;
import me.huzorro.gateway.cmpp.PacketType;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

import com.google.common.primitives.Bytes;

/**
 * @author huzorro
 *
 */
public class CmppSubmitRequestMessageEncoder extends OneToOneEncoder {
	private PacketType packetType;
	/**
	 * 
	 */
	public CmppSubmitRequestMessageEncoder() {
		this(PacketType.CMPPSUBMITREQUEST);
	}
	
	public CmppSubmitRequestMessageEncoder(PacketType packetType) {
		this.packetType = packetType;
	}
	/* (non-Javadoc)
	 * @see org.jboss.netty.handler.codec.oneone.OneToOneEncoder#encode(org.jboss.netty.channel.ChannelHandlerContext, org.jboss.netty.channel.Channel, java.lang.Object)
	 */
	@Override
	@SuppressWarnings("unchecked")
	protected Object encode(ChannelHandlerContext ctx, Channel channel,
			Object msg) throws Exception {
    	Message<ChannelBuffer> message = (Message<ChannelBuffer>) msg;
        long commandId = ((Long) message.getHeader().getCommandId()).longValue();
        if(commandId != packetType.getCommandId()) return msg;
        CmppSubmitRequestMessage<ChannelBuffer> requestMessage = (CmppSubmitRequestMessage<ChannelBuffer>) message;
        
        ChannelBuffer bodyBuffer = ChannelBuffers.dynamicBuffer();


        bodyBuffer.writeBytes(DefaultMsgIdUtil.msgId2Bytes(requestMessage.getMsgid()));
        bodyBuffer.writeByte(requestMessage.getPknumber());
        bodyBuffer.writeByte(requestMessage.getPktotal());
        bodyBuffer.writeByte(requestMessage.getRegisteredDelivery());
        bodyBuffer.writeByte(requestMessage.getMsglevel());
        
		bodyBuffer.writeBytes(Bytes.ensureCapacity(requestMessage
				.getServiceId().getBytes(GlobalVars.defaultTransportCharset),
				PacketStructure.SubmitRequest.SERVICEID.getLength(), 0));
		
        bodyBuffer.writeByte(requestMessage.getFeeUserType());
		bodyBuffer.writeBytes(Bytes.ensureCapacity(
						requestMessage.getFeeterminalId().getBytes(
								GlobalVars.defaultTransportCharset),
						PacketStructure.SubmitRequest.FEETERMINALID
								.getBodyLength(), 0));
        bodyBuffer.writeByte(requestMessage.getFeeterminaltype());
        bodyBuffer.writeByte(requestMessage.getTppId());
        bodyBuffer.writeByte(requestMessage.getTpudhi());
        bodyBuffer.writeByte(requestMessage.getMsgFmt());
        
		bodyBuffer.writeBytes(Bytes.ensureCapacity(requestMessage.getMsgsrc()
				.getBytes(GlobalVars.defaultTransportCharset),
				PacketStructure.SubmitRequest.MSGSRC.getLength(), 0));
		
		bodyBuffer.writeBytes(Bytes.ensureCapacity(requestMessage.getFeeType()
				.getBytes(GlobalVars.defaultTransportCharset),
				PacketStructure.SubmitRequest.FEETYPE.getLength(), 0));
		
		bodyBuffer.writeBytes(Bytes.ensureCapacity(requestMessage.getFeeCode()
				.getBytes(GlobalVars.defaultTransportCharset),
				PacketStructure.SubmitRequest.FEECODE.getLength(), 0));
		
		bodyBuffer.writeBytes(Bytes.ensureCapacity(requestMessage
				.getValIdTime().getBytes(GlobalVars.defaultTransportCharset),
				PacketStructure.SubmitRequest.VALIDTIME.getLength(), 0));
		
		bodyBuffer.writeBytes(Bytes.ensureCapacity(requestMessage.getAtTime()
				.getBytes(GlobalVars.defaultTransportCharset),
				PacketStructure.SubmitRequest.ATTIME.getLength(), 0));
		
		bodyBuffer.writeBytes(Bytes.ensureCapacity(requestMessage.getSrcId()
				.getBytes(GlobalVars.defaultTransportCharset),
				PacketStructure.SubmitRequest.SRCID.getLength(), 0));
		
        bodyBuffer.writeByte(requestMessage.getDestUsrtl());
        
		bodyBuffer.writeBytes(Bytes.ensureCapacity(
				requestMessage.getDestterminalId().getBytes(
						GlobalVars.defaultTransportCharset),
				PacketStructure.SubmitRequest.DESTTERMINALID.getLength(), 0));
		
        bodyBuffer.writeByte(requestMessage.getDestterminaltype());
        bodyBuffer.writeByte(requestMessage.getMsgLength());
        bodyBuffer.writeBytes(requestMessage.getMsgContentBytes());
        
		bodyBuffer.writeBytes(Bytes.ensureCapacity(requestMessage.getLinkID()
				.getBytes(GlobalVars.defaultTransportCharset),
				PacketStructure.SubmitRequest.LINKID.getLength(), 0));
        
        message.setBodyBuffer(bodyBuffer);
        
        ChannelBuffer messageBuffer = ChannelBuffers.dynamicBuffer();
        
        messageBuffer.writeBytes(message.getHeader().getHeadBuffer());
        messageBuffer.writeBytes(message.getBodyBuffer());
        return messageBuffer;        
	}

}

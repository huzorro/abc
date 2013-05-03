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
public class CmppDeliverRequestMessageEncoder extends OneToOneEncoder {
	private PacketType packetType;
	/**
	 * 
	 */
	public CmppDeliverRequestMessageEncoder() {
		this(PacketType.CMPPDELIVERREQUEST);
	}
	
	public CmppDeliverRequestMessageEncoder(PacketType packetType) {
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
        
        CmppDeliverRequestMessage<ChannelBuffer> requestMessage = new CmppDeliverRequestMessage<ChannelBuffer>();
        
        ChannelBuffer bodyBuffer = ChannelBuffers.dynamicBuffer();
        
        bodyBuffer.writeBytes(DefaultMsgIdUtil.msgId2Bytes(requestMessage.getMsgId()));
		bodyBuffer.writeBytes(Bytes.ensureCapacity(requestMessage.getDestId()
				.getBytes(GlobalVars.defaultTransportCharset),
				PacketStructure.DeliverRequest.DESTID.getLength(), 0));
		bodyBuffer.writeBytes(Bytes.ensureCapacity(requestMessage
				.getServiceid().getBytes(GlobalVars.defaultTransportCharset),
				PacketStructure.DeliverRequest.SERVICEID.getLength(), 0));
		bodyBuffer.writeByte(requestMessage.getTppid());
		bodyBuffer.writeByte(requestMessage.getTpudhi());
		bodyBuffer.writeByte(requestMessage.getMsgfmt());
		bodyBuffer.writeBytes(Bytes.ensureCapacity(requestMessage
				.getSrcterminalId()
				.getBytes(GlobalVars.defaultTransportCharset),
				PacketStructure.DeliverRequest.SRCTERMINALID.getLength(), 0));
		bodyBuffer.writeByte(requestMessage.getSrcterminalType());
		bodyBuffer.writeByte(requestMessage.getRegisteredDelivery());
		bodyBuffer.writeByte(requestMessage.getMsgLength());
		
		if(!requestMessage.isReport()) {
			bodyBuffer.writeBytes(requestMessage.getMsgContentBytes());
		} else {
			bodyBuffer.writeBytes(DefaultMsgIdUtil.msgId2Bytes(requestMessage
					.getReportRequestMessage().getMsgId()));
			bodyBuffer.writeBytes(Bytes.ensureCapacity(
					requestMessage.getReportRequestMessage().getStat()
							.getBytes(GlobalVars.defaultTransportCharset),
					PacketStructure.ReportRequest.STAT.getLength(), 0));
			bodyBuffer.writeBytes(Bytes.ensureCapacity(
					requestMessage.getReportRequestMessage().getSubmitTime()
							.getBytes(GlobalVars.defaultTransportCharset),
					PacketStructure.ReportRequest.SUBMITTIME.getLength(), 0));
			bodyBuffer.writeBytes(Bytes.ensureCapacity(
					requestMessage.getReportRequestMessage().getDoneTime()
							.getBytes(GlobalVars.defaultTransportCharset),
					PacketStructure.ReportRequest.DONETIME.getLength(), 0));
			bodyBuffer
					.writeBytes(Bytes
							.ensureCapacity(
									requestMessage
											.getReportRequestMessage()
											.getDestterminalId()
											.getBytes(
													GlobalVars.defaultTransportCharset),
									PacketStructure.ReportRequest.DESTTERMINALID
											.getLength(), 0));
			bodyBuffer.writeByte((int) requestMessage.getReportRequestMessage().getSmscSequence());
		}
		
		bodyBuffer.writeBytes(Bytes.ensureCapacity(requestMessage.getLinkid()
				.getBytes(GlobalVars.defaultTransportCharset),
				PacketStructure.DeliverRequest.LINKID.getLength(), 0));
		
        message.setBodyBuffer(bodyBuffer);
        
        ChannelBuffer messageBuffer = ChannelBuffers.dynamicBuffer();
        messageBuffer.writeBytes(message.getHeader().getHeadBuffer());
        messageBuffer.writeBytes(message.getBodyBuffer());     
		return messageBuffer;
	}

}

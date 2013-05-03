/**
 * 
 */
package me.huzorro.gateway;

import me.huzorro.gateway.cmpp.PacketStructure;
import me.huzorro.gateway.cmpp.PacketType;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneDecoder;

/**
 * @author huzorro
 *
 */
public class CmppDeliverRequestMessageDecoder extends OneToOneDecoder {
	private PacketType packetType;
	/**
	 * 
	 */
	public CmppDeliverRequestMessageDecoder() {
		this(PacketType.CMPPDELIVERREQUEST);
	}
	
	public CmppDeliverRequestMessageDecoder(PacketType packetType) {
		this.packetType = packetType;
	}

	/* (non-Javadoc)
	 * @see org.jboss.netty.handler.codec.oneone.OneToOneDecoder#decode(org.jboss.netty.channel.ChannelHandlerContext, org.jboss.netty.channel.Channel, java.lang.Object)
	 */
	@Override
	@SuppressWarnings("unchecked")
	protected Object decode(ChannelHandlerContext ctx, Channel channel,
			Object msg) throws Exception {
        Message<ChannelBuffer> message = (Message<ChannelBuffer>) msg;
        long commandId = ((Long) message.getHeader().getCommandId()).longValue();
        if(packetType.getCommandId() != commandId) return msg;	
        
        CmppDeliverRequestMessage<ChannelBuffer> requestMessage = new CmppDeliverRequestMessage<ChannelBuffer>();
        requestMessage.setBodyBuffer(message.getBodyBuffer());
        requestMessage.setHeader(message.getHeader());
        
        ChannelBuffer bodyBuffer = message.getBodyBuffer().copy();	
        
		requestMessage.setMsgId(DefaultMsgIdUtil.bytes2MsgId(bodyBuffer
				.readBytes(PacketStructure.DeliverRequest.MSGID.getLength())
				.array()));
		requestMessage.setDestId(bodyBuffer.readBytes(
				PacketStructure.DeliverRequest.DESTID.getLength()).toString(
				GlobalVars.defaultTransportCharset).trim());
		requestMessage
				.setServiceid(bodyBuffer
						.readBytes(
								PacketStructure.DeliverRequest.SERVICEID
										.getLength())
						.toString(GlobalVars.defaultTransportCharset).trim());
		requestMessage.setTppid(bodyBuffer.readUnsignedByte());
		requestMessage.setTpudhi(bodyBuffer.readUnsignedByte());
		requestMessage.setMsgfmt(bodyBuffer.readUnsignedByte());
		requestMessage.setSrcterminalId(bodyBuffer
				.readBytes(
						PacketStructure.DeliverRequest.SRCTERMINALID
								.getLength())
				.toString(GlobalVars.defaultTransportCharset).trim());
		requestMessage.setSrcterminalType(bodyBuffer.readUnsignedByte());
		requestMessage.setRegisteredDelivery(bodyBuffer.readUnsignedByte());
		requestMessage.setMsgLength(bodyBuffer.readUnsignedByte());
		
		if(!requestMessage.isReport()) {
			requestMessage.setMsgContent(bodyBuffer.readBytes(requestMessage.getMsgLength()).toString(GlobalVars.defaultTransportCharset).trim());
		} else {
			requestMessage.getReportRequestMessage().setMsgId(
					DefaultMsgIdUtil.bytes2MsgId(bodyBuffer.readBytes(
							PacketStructure.ReportRequest.MSGID.getLength())
							.array()));
			requestMessage.getReportRequestMessage().setStat(
					bodyBuffer
							.readBytes(
									PacketStructure.ReportRequest.STAT
											.getLength())
							.toString(GlobalVars.defaultTransportCharset)
							.trim());
			requestMessage.getReportRequestMessage().setSubmitTime(
					bodyBuffer
							.readBytes(
									PacketStructure.ReportRequest.SUBMITTIME
											.getLength())
							.toString(GlobalVars.defaultTransportCharset)
							.trim());
			requestMessage.getReportRequestMessage().setDoneTime(
					bodyBuffer
							.readBytes(
									PacketStructure.ReportRequest.DONETIME
											.getLength())
							.toString(GlobalVars.defaultTransportCharset)
							.trim());
			requestMessage
					.getReportRequestMessage()
					.setDestterminalId(
							bodyBuffer
									.readBytes(
											PacketStructure.ReportRequest.DESTTERMINALID
													.getLength())
									.toString(
											GlobalVars.defaultTransportCharset)
									.trim());
			requestMessage.getReportRequestMessage().setSmscSequence(bodyBuffer.readUnsignedInt());
		}
		
		requestMessage.setLinkid(bodyBuffer.readBytes(
				PacketStructure.DeliverRequest.LINKID.getLength()).toString(
				GlobalVars.defaultTransportCharset));
		
		return requestMessage;
	}

}

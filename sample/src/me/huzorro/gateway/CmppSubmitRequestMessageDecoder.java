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
public class CmppSubmitRequestMessageDecoder extends OneToOneDecoder {
	private PacketType packetType;
	/**
	 * 
	 */
	public CmppSubmitRequestMessageDecoder() {
		this(PacketType.CMPPSUBMITREQUEST);
	}
	public CmppSubmitRequestMessageDecoder(PacketType packetType) {
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
        
        CmppSubmitRequestMessage<ChannelBuffer> requestMessage = new CmppSubmitRequestMessage<ChannelBuffer>();
        requestMessage.setBodyBuffer(message.getBodyBuffer());
        requestMessage.setHeader(message.getHeader());
        
        ChannelBuffer bodyBuffer = message.getBodyBuffer().copy();
                       
		requestMessage.setMsgid(DefaultMsgIdUtil.bytes2MsgId(bodyBuffer
				.readBytes(PacketStructure.SubmitRequest.MSGID.getLength())
				.array()));
        requestMessage.setPktotal(bodyBuffer.readUnsignedByte());
        requestMessage.setPktotal(bodyBuffer.readUnsignedByte());
        requestMessage.setRegisteredDelivery(bodyBuffer.readUnsignedByte());
        requestMessage.setMsglevel(bodyBuffer.readUnsignedByte());
		requestMessage.setServiceId(bodyBuffer.readBytes(
				PacketStructure.SubmitRequest.SERVICEID.getLength()).toString(
				GlobalVars.defaultTransportCharset).trim());
		requestMessage.setFeeUserType(bodyBuffer.readUnsignedByte());
		
		requestMessage.setFeeterminalId(bodyBuffer
						.readBytes(PacketStructure.SubmitRequest.FEETERMINALID.getLength())
						.toString(GlobalVars.defaultTransportCharset).trim());		
		
		requestMessage.setFeeterminaltype(bodyBuffer.readUnsignedByte());
		requestMessage.setTppId(bodyBuffer.readUnsignedByte());
		requestMessage.setTpudhi(bodyBuffer.readUnsignedByte());
		requestMessage.setMsgFmt(bodyBuffer.readUnsignedByte());
		requestMessage.setMsgsrc(bodyBuffer
				.readBytes(PacketStructure.SubmitRequest.MSGSRC.getLength())
				.toString(GlobalVars.defaultTransportCharset).trim());
		
		requestMessage.setFeeType(bodyBuffer.readBytes(
				PacketStructure.SubmitRequest.FEETYPE.getLength()).toString(
				GlobalVars.defaultTransportCharset).trim());
		
		requestMessage.setFeeCode(bodyBuffer.readBytes(
				PacketStructure.SubmitRequest.FEECODE.getLength()).toString(
				GlobalVars.defaultTransportCharset).trim());
		
		requestMessage.setValIdTime(bodyBuffer.readBytes(
				PacketStructure.SubmitRequest.VALIDTIME.getLength()).toString(
				GlobalVars.defaultTransportCharset).trim());
		
		requestMessage.setAtTime(bodyBuffer
				.readBytes(PacketStructure.SubmitRequest.ATTIME.getLength())
				.toString(GlobalVars.defaultTransportCharset).trim());
		
		requestMessage.setSrcId(bodyBuffer
				.readBytes(PacketStructure.SubmitRequest.SRCID.getLength())
				.toString(GlobalVars.defaultTransportCharset).trim());
		
		requestMessage.setDestUsrtl(bodyBuffer.readUnsignedByte());
		
		requestMessage.setDestterminalId(bodyBuffer
				.readBytes(PacketStructure.SubmitRequest.DESTTERMINALID.getLength())
				.toString(GlobalVars.defaultTransportCharset).trim());
		
		requestMessage.setDestterminaltype(bodyBuffer.readUnsignedByte());
		requestMessage.setMsgLength(bodyBuffer.readUnsignedByte());
		
		requestMessage.setMsgContent(bodyBuffer
				.readBytes(requestMessage.getMsgLength())
				.toString(GlobalVars.defaultTransportCharset).trim());
		
		requestMessage.setLinkID(bodyBuffer
				.readBytes(PacketStructure.SubmitRequest.LINKID.getLength())
				.toString(GlobalVars.defaultTransportCharset).trim());
		
		return requestMessage;
	}

}

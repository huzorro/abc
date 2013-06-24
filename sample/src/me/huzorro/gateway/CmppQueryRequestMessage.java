/**
 * 
 */
package me.huzorro.gateway;

import me.huzorro.gateway.cmpp.CmppPacketType;
import me.huzorro.gateway.cmpp.CmppQueryRequest;
import me.huzorro.gateway.cmpp.PacketType;

/**
 * @author huzorro(huzorro@gmail.com)
 * @param <T>
 *
 */
public class CmppQueryRequestMessage extends DefaultMessage {
	private static final long serialVersionUID = -7762194632879048169L;
	private String time = String.format("%tY%<tm%<td",System.currentTimeMillis());
	private short queryType = 0;
	private String queryCode = new String(
			new byte[CmppQueryRequest.QUERYCODE.getLength()],
			GlobalVars.defaultTransportCharset);
	private String reserve = new String(
			new byte[CmppQueryRequest.RESERVE.getLength()],
			GlobalVars.defaultTransportCharset);
	
	public CmppQueryRequestMessage() {
		this(CmppPacketType.CMPPQUERYREQUEST);
	}
	public CmppQueryRequestMessage(PacketType packetType) {
		setPacketType(packetType);
	}
	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}
	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}
	/**
	 * @return the queryType
	 */
	public short getQueryType() {
		return queryType;
	}
	/**
	 * @param queryType the queryType to set
	 */
	public void setQueryType(short queryType) {
		this.queryType = queryType;
	}
	/**
	 * @return the queryCode
	 */
	public String getQueryCode() {
		return queryCode;
	}
	/**
	 * @param queryCode the queryCode to set
	 */
	public void setQueryCode(String queryCode) {
		this.queryCode = queryCode;
	}
	/**
	 * @return the reserve
	 */
	public String getReserve() {
		return reserve;
	}
	/**
	 * @param reserve the reserve to set
	 */
	public void setReserve(String reserve) {
		this.reserve = reserve;
	}
	
}

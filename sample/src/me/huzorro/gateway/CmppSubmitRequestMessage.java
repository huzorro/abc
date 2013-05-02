package me.huzorro.gateway;

import org.jboss.netty.buffer.ChannelBuffer;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 * @param <T>
 */
public class CmppSubmitRequestMessage<T extends ChannelBuffer> extends DefaultMessage<T> {
    private static final long serialVersionUID = 1369427662600486133L;
    private byte[] msgid;
    private short pktotal;
    private short pknumber;
    private short registeredDelivery;
    private short msglevel;
    private String serviceId;
    private short feeUserType;
    private String feeterminalId;
    private short feeterminaltype;
    private short tppId;
    private short tpudhi;
    private short msgFmt;
    private String msgsrc;
    private String feeType;
    private String feeCode;
    private String valIdTime;
    private String atTime;
    private String srcId;
    private short destUsrtl;
    private String destterminalId;
    private short destterminaltype;
    private short msgLength;
    private String msgContent;
    private String linkID;
    /**
     * @return the msgid
     */
    public byte[] getMsgid() {
        return msgid;
    }
    /**
     * @param msgid the msgid to set
     */
    public void setMsgid(byte[] msgid) {
        this.msgid = msgid;
    }
    /**
     * @return the pktotal
     */
    public short getPktotal() {
        return pktotal;
    }
    /**
     * @param pktotal the pktotal to set
     */
    public void setPktotal(short pktotal) {
        this.pktotal = pktotal;
    }
    /**
     * @return the pknumber
     */
    public short getPknumber() {
        return pknumber;
    }
    /**
     * @param pknumber the pknumber to set
     */
    public void setPknumber(short pknumber) {
        this.pknumber = pknumber;
    }
    /**
     * @return the registeredDelivery
     */
    public short getRegisteredDelivery() {
        return registeredDelivery;
    }
    /**
     * @param registeredDelivery the registeredDelivery to set
     */
    public void setRegisteredDelivery(short registeredDelivery) {
        this.registeredDelivery = registeredDelivery;
    }
    /**
     * @return the msglevel
     */
    public short getMsglevel() {
        return msglevel;
    }
    /**
     * @param msglevel the msglevel to set
     */
    public void setMsglevel(short msglevel) {
        this.msglevel = msglevel;
    }
    /**
     * @return the serviceId
     */
    public String getServiceId() {
        return serviceId;
    }
    /**
     * @param serviceId the serviceId to set
     */
    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }
    /**
     * @return the feeUserType
     */
    public short getFeeUserType() {
        return feeUserType;
    }
    /**
     * @param feeUserType the feeUserType to set
     */
    public void setFeeUserType(short feeUserType) {
        this.feeUserType = feeUserType;
    }
    /**
     * @return the feeterminalId
     */
    public String getFeeterminalId() {
        return feeterminalId;
    }
    /**
     * @param feeterminalId the feeterminalId to set
     */
    public void setFeeterminalId(String feeterminalId) {
        this.feeterminalId = feeterminalId;
    }
    /**
     * @return the feeterminaltype
     */
    public short getFeeterminaltype() {
        return feeterminaltype;
    }
    /**
     * @param feeterminaltype the feeterminaltype to set
     */
    public void setFeeterminaltype(short feeterminaltype) {
        this.feeterminaltype = feeterminaltype;
    }
    /**
     * @return the tppId
     */
    public short getTppId() {
        return tppId;
    }
    /**
     * @param tppId the tppId to set
     */
    public void setTppId(short tppId) {
        this.tppId = tppId;
    }
    /**
     * @return the tpudhi
     */
    public short getTpudhi() {
        return tpudhi;
    }
    /**
     * @param tpudhi the tpudhi to set
     */
    public void setTpudhi(short tpudhi) {
        this.tpudhi = tpudhi;
    }
    /**
     * @return the msgFmt
     */
    public short getMsgFmt() {
        return msgFmt;
    }
    /**
     * @param msgFmt the msgFmt to set
     */
    public void setMsgFmt(short msgFmt) {
        this.msgFmt = msgFmt;
    }
    /**
     * @return the msgsrc
     */
    public String getMsgsrc() {
        return msgsrc;
    }
    /**
     * @param msgsrc the msgsrc to set
     */
    public void setMsgsrc(String msgsrc) {
        this.msgsrc = msgsrc;
    }
    /**
     * @return the feeType
     */
    public String getFeeType() {
        return feeType;
    }
    /**
     * @param feeType the feeType to set
     */
    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }
    /**
     * @return the feeCode
     */
    public String getFeeCode() {
        return feeCode;
    }
    /**
     * @param feeCode the feeCode to set
     */
    public void setFeeCode(String feeCode) {
        this.feeCode = feeCode;
    }
    /**
     * @return the valIdTime
     */
    public String getValIdTime() {
        return valIdTime;
    }
    /**
     * @param valIdTime the valIdTime to set
     */
    public void setValIdTime(String valIdTime) {
        this.valIdTime = valIdTime;
    }
    /**
     * @return the atTime
     */
    public String getAtTime() {
        return atTime;
    }
    /**
     * @param atTime the atTime to set
     */
    public void setAtTime(String atTime) {
        this.atTime = atTime;
    }
    /**
     * @return the srcId
     */
    public String getSrcId() {
        return srcId;
    }
    /**
     * @param srcId the srcId to set
     */
    public void setSrcId(String srcId) {
        this.srcId = srcId;
    }
    /**
     * @return the destUsrtl
     */
    public short getDestUsrtl() {
        return destUsrtl;
    }
    /**
     * @param destUsrtl the destUsrtl to set
     */
    public void setDestUsrtl(short destUsrtl) {
        this.destUsrtl = destUsrtl;
    }
    /**
     * @return the destterminalId
     */
    public String getDestterminalId() {
        return destterminalId;
    }
    /**
     * @param destterminalId the destterminalId to set
     */
    public void setDestterminalId(String destterminalId) {
        this.destterminalId = destterminalId;
    }
    /**
     * @return the destterminaltype
     */
    public short getDestterminaltype() {
        return destterminaltype;
    }
    /**
     * @param destterminaltype the destterminaltype to set
     */
    public void setDestterminaltype(short destterminaltype) {
        this.destterminaltype = destterminaltype;
    }
    /**
     * @return the msgLength
     */
    public short getMsgLength() {
        return msgLength;
    }
    /**
     * @param msgLength the msgLength to set
     */
    public void setMsgLength(short msgLength) {
        this.msgLength = msgLength;
    }
    /**
     * @return the msgContent
     */
    public String getMsgContent() {
        return msgContent;
    }
    /**
     * @param msgContent the msgContent to set
     */
    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }
    /**
     * @return the linkID
     */
    public String getLinkID() {
        return linkID;
    }
    /**
     * @param linkID the linkID to set
     */
    public void setLinkID(String linkID) {
        this.linkID = linkID;
    }
    
}

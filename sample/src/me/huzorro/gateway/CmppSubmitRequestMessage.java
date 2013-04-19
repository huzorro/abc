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
    
}

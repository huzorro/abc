/**
 * 
 */
package me.huzorro.gateway.cmpp;

/**
 * @author huzorro(huzorro@gmail.com)
 *
 */
public enum CmppPacketType implements PacketType {
    CMPPCONNECTREQUEST(0x00000001, CmppConnectRequest.class),
    CMPPCONNECTRESPONSE(0x80000001, CmppConnectResponse.class),
    CMPPTERMINATEREQUEST(0x00000002, CmppTerminateRequest.class),
    CMPPTERMINATERESPONSE(0x80000002, CmppTerminateResponse.class),    
    CMPPSUBMITREQUEST(0x00000004, CmppSubmitRequest.class), 
    CMPPSUBMITRESPONSE(0x80000004, CmppSubmitResponse.class),
    CMPPDELIVERREQUEST(0x00000005, CmppDeliverRequest.class),
    CMPPDELIVERRESPONSE(0x80000005, CmppDeliverResponse.class),    
    CMPPQUERYREQUEST(0x00000006, CmppQueryRequest.class),
    CMPPQUERYRESPONSE(0x80000006, CmppQueryResponse.class),
    CMPPCANCELREQUEST(0x00000007, CmppCancelRequest.class),
    CMPPCANCELRESPONSE(0x80000007, CmppCancelResponse.class),
    CMPPACTIVETESTREQUEST(0x00000008, CmppActiveTestRequest.class),
    CMPPACTIVETESTRESPONSE(0x80000008, CmppActiveTestResponse.class);
    
    private long commandId;
    private Class<? extends PacketStructure> packetStructure;
    
    private CmppPacketType(long commandId, Class<? extends PacketStructure> packetStructure) {
        this.commandId = commandId;
        this.packetStructure = packetStructure;
    }
    public long getCommandId() {
        return commandId;
    }
    public PacketStructure[] getPacketStructures() {
    	return packetStructure.getEnumConstants();
    }

    public long getAllCommandId() {
        long defaultId = 0x0;
        long allCommandId = 0x0;
        for(CmppPacketType packetType : CmppPacketType.values()) {
            allCommandId |= packetType.commandId;
        }
        return allCommandId ^ defaultId;
    }
}

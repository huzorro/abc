package me.huzorro.gateway.cmpp;


/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public enum PacketType {
    CMPPCONNECTREQUEST(0x00000001, PacketStructure.ConnectRequest.class),
    CMPPCONNECTRESPONSE(0x80000001, PacketStructure.ConnectResponse.class),
    CMPPTERMINATEREQUEST(0x00000002, PacketStructure.TerminateRequest.class),
    CMPPTERMINATERESPONSE(0x80000002, PacketStructure.TerminateResponse.class),    
    CMPPSUBMITREQUEST(0x00000004, PacketStructure.SubmitRequest.class), 
    CMPPSUBMITRESPONSE(0x80000004, PacketStructure.SubmitResponse.class),
    CMPPDELIVERREQUEST(0x00000005, PacketStructure.DeliverRequest.class),
    CMPPDELIVERRESPONSE(0x80000005, PacketStructure.DeliverResponse.class),    
    CMPPQUERYREQUEST(0x00000006, PacketStructure.QueryRequest.class),
    CMPPQUERYRESPONSE(0x80000006, PacketStructure.QueryResponse.class),
    CMPPCANCELREQUEST(0x00000007, PacketStructure.CancelRequest.class),
    CMPPCANCELRESPONSE(0x80000007, PacketStructure.CancelResponse.class),
    CMPPACTIVETESTREQUEST(0x00000008, PacketStructure.ActiveTestRequest.class),
    CMPPACTIVETESTRESPONSE(0x80000008, PacketStructure.ActiveTestResponse.class);
    
    private long commandId;
    private Class<? extends PacketStructure> packetStructure;
    
    private PacketType(long commandId, Class<? extends PacketStructure> packetStructure) {
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
        for(PacketType packetType : PacketType.values()) {
            allCommandId |= packetType.commandId;
        }
        return allCommandId ^ defaultId;
    }
}

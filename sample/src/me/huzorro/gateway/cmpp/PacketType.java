package me.huzorro.gateway.cmpp;


/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public enum PacketType {
    CMPPCONNECTREQUEST(0x00000001, PacketStructure.ConnectRequest.class),
    CMPPCONNECTRESPONSE(0x80000001, PacketStructure.ConnectResponse.class),
    CMPPTERMINATEREQUEST(0x00000002, null),
    CMPPTERMINATERESPONSE(0x80000002, null),    
    CMPPSUBMITREQUEST(0x00000004, PacketStructure.SubmitRequest.class), 
    CMPPSUBMITRESPONSE(0x80000004, PacketStructure.SubmitResponse.class),
    CMPPDELIVERREQUEST(0x00000005, null),
    CMPPDELIVERRESPONSE(0x80000005, null),    
    CMPPQUERYREQUEST(0x00000006, null),
    CMPPQUERYRESPONSE(0x80000006, null),
    CMPPCANCELREQUEST(0x00000007, null),
    CMPPCANCELRESPONSE(0x80000007, null),
    CMPPACTIVETESTREQUEST(0x00000008, null),
    CMPPACTIVETESTRESPONSE(0x80000008, null);
    
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

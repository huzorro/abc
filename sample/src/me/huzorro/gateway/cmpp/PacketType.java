package me.huzorro.gateway.cmpp;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public enum PacketType {
    CMPPCONNECTREQUEST(0x00000001), 
    CMPPCONNECTRESPONSE(0x80000001),
    CMPPTERMINATEREQUEST(0x00000002),
    CMPPTERMINATERESPONSE(0x80000002),    
    CMPPSUBMITREQUEST(0x00000004), 
    CMPPSUBMITRESPONSE(0x80000004),
    CMPPDELIVERREQUEST(0x00000005),
    CMPPDELIVERRESPONSE(0x80000005),    
    CMPPQUERYREQUEST(0x00000006),
    CMPPQUERYRESPONSE(0x80000006),
    CMPPCANCELREQUEST(0x00000007),
    CMPPCANCELRESPONSE(0x80000007),
    CMPPACTIVETESTREQUEST(0x00000008),
    CMPPACTIVETESTRESPONSE(0x80000008);
    
    private long commandId;
    private PacketType(long commandId) {
        this.commandId = commandId;
    }
    public long getCommandId() {
        return commandId;
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

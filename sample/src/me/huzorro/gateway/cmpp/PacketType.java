package me.huzorro.gateway.cmpp;


/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public interface PacketType {    
    public long getCommandId();
    public PacketStructure[] getPacketStructures();
    public long getAllCommandId();
}

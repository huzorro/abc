package me.huzorro.gateway.cmpp;




/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public interface PacketStructure {
    public DataType getDataType();
    public boolean isFixFiledLength();
    public boolean isFixPacketLength();
    public int getLength();
    public int getBodyLength();
}

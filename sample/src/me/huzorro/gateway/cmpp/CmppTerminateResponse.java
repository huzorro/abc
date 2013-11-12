/**
 * 
 */
package me.huzorro.gateway.cmpp;


/**
 * @author huzorro(huzorro@gmail.com)
 *
 */
public enum CmppTerminateResponse implements PacketStructure {
    ;
    private DataType dataType;
    private boolean isFixFiledLength; 
    private int length;
    
    private CmppTerminateResponse(DataType dataType, boolean isFixFiledLength, int length) {
        this.dataType = dataType;
        this.isFixFiledLength = isFixFiledLength;
        this.length = length;
    }
    @Override
    public DataType getDataType() {
        return dataType;
    }

    @Override
    public boolean isFixFiledLength() {
        return isFixFiledLength;
    }

    @Override
    public boolean isFixPacketLength() {
        return true;
    }

    @Override
    public int getLength() {
        return length;
    }

    @Override
    public int getBodyLength() {
        int bodyLength = 0;
        for(CmppTerminateResponse response : CmppTerminateResponse.values()) {
            bodyLength += response.getLength();
        }
        return bodyLength;
    }  
}

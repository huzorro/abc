package me.huzorro.gateway.cmpp;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public enum Head {
    COMMANDID(DataType.UNSIGNEDINT, 4), 
    TOTALLENGTH(DataType.UNSIGNEDINT, 4),
    SEQUENCEID(DataType.OCTERSTRING, 4);
    private DataType dataType;
    private int length;
    private Head(DataType dataType, int length){
        this.dataType = dataType;
        this.length = length;
    }
    public DataType getDataType() {
        return dataType; 
    }
    public int getLength() {
        return length;
    }
    public int getHeadLength() {
        int length = 0;
        for(Head head : Head.values()) {
            length += head.getLength();
        }
        return length;
    }
}

package me.huzorro.gateway.cmpp;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public enum DataType {
    UNSIGNEDINT(0x1), OCTERSTRING(0x2);
    private int commandId;
    private DataType(int commandId) {
        this.commandId = commandId;
    }
    public int getCommandId() {
        return commandId;
    }
    
    public int getAllCommandId() {
        int defaultId = 0x0;
        int allCommandId = 0x0;
        for(DataType dataType : DataType.values()) {
            allCommandId |= dataType.commandId;
        }
        return allCommandId ^ defaultId;
    }
}

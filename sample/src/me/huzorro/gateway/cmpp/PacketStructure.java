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
    enum ConnectRequest implements PacketStructure {
        SOURCEADDR(DataType.OCTERSTRING, true, 6),
        AUTHENTICATORSOURCE(DataType.OCTERSTRING, true, 16),
        VERSION(DataType.UNSIGNEDINT, true, 1),
        TIMESTAMP(DataType.UNSIGNEDINT, true, 4);
        private DataType dataType;
        private boolean isFixFiledLength;
        private int length;
        private ConnectRequest(DataType dataType, boolean isFixFiledLength, int length) {
            this.dataType = dataType;
            this.isFixFiledLength = isFixFiledLength;
            this.length = length;
        }
        public DataType getDataType() {
            return dataType;
        }
        public boolean isFixFiledLength() {
            return isFixFiledLength;
        }
        public boolean isFixPacketLength() {
        	return true;
        }
        public int getLength() {
            return length;
        }
        public int getBodyLength() {
            int bodyLength = 0;
            for(ConnectRequest request : ConnectRequest.values()) {
                bodyLength += request.getLength();
            }
            return bodyLength;
        }
    }
    enum ConnectResponse implements PacketStructure {
        STATUS(DataType.UNSIGNEDINT, true, 4),
        AUTHENTICATORISMG(DataType.OCTERSTRING, true, 16),
        VERSION(DataType.UNSIGNEDINT, true, 1);
        private DataType dataType;
        private boolean isFixFiledLength; 
        private int length;
        private ConnectResponse(DataType dataType, boolean isFixFiledLength, int length) {
            this.dataType = dataType;
            this.isFixFiledLength = isFixFiledLength;
            this.length = length;
        }
        public DataType getDataType() {
            return dataType;
        }
        public boolean isFixFiledLength() {
            return isFixFiledLength;
        }
        public boolean isFixPacketLength() {
        	return true;
        }
        public int getLength() {
            return length;
        }
        public int getBodyLength() {
            int bodyLength = 0;
            for(ConnectResponse response : ConnectResponse.values()) {
                bodyLength += response.getLength();
            }
            return bodyLength;
        }
    }
    enum SubmitRequest implements PacketStructure {
        MSGID(DataType.UNSIGNEDINT, true, 8),
        PKTOTAL(DataType.UNSIGNEDINT, true, 1),
        PKNUMBER(DataType.UNSIGNEDINT, true, 1),
        REGISTEREDDELIVERY(DataType.UNSIGNEDINT, true, 1),
        MSGLEVEL(DataType.UNSIGNEDINT, true, 1),
        SERVICEID(DataType.OCTERSTRING, true, 10),
        FEEUSERTYPE(DataType.UNSIGNEDINT, true, 1),
        FEETERMINALID(DataType.OCTERSTRING, true, 32),
        FEETERMINALTYPE(DataType.UNSIGNEDINT, true, 1),
        TPPID(DataType.UNSIGNEDINT, true, 1),
        TPUDHI(DataType.UNSIGNEDINT, true, 1),
        MSGFMT(DataType.UNSIGNEDINT, true, 1),
        MSGSRC(DataType.OCTERSTRING, true, 6),
        FEETYPE(DataType.OCTERSTRING, true, 2),
        FEECODE(DataType.OCTERSTRING, true, 6),
        VALIDTIME(DataType.OCTERSTRING, true, 17),
        ATTIME(DataType.OCTERSTRING, true, 17),
        SRCID(DataType.OCTERSTRING, true, 21),
        DESTUSRTL(DataType.UNSIGNEDINT, true, 1),
        DESTTERMINALID(DataType.OCTERSTRING, true, 32),
        DESTTERMINALTYPE(DataType.UNSIGNEDINT, true, 1),
        MSGLENGTH(DataType.UNSIGNEDINT, true, 1),
        MSGCONTENT(DataType.OCTERSTRING, false, 0),
        LINKID(DataType.OCTERSTRING, true, 20);
        private DataType dataType;
        private boolean isFixFiledLength; 
        private int length;
        private SubmitRequest(DataType dataType, boolean isFixFiledLength, int length) {
            this.dataType = dataType;
            this.isFixFiledLength = isFixFiledLength;
            this.length = length;
        }
        public DataType getDataType() {
            return dataType;
        }
        public boolean isFixFiledLength() {
            return isFixFiledLength;
        }
        public boolean isFixPacketLength() {
        	return false;
        }
        public int getLength() {
            return length;
        }
        public int getBodyLength() {
            int bodyLength = 0;
            for(SubmitRequest request : SubmitRequest.values()) {
                bodyLength += request.getLength();
            }
            return bodyLength;
        }
    }
    enum SubmitResponse implements PacketStructure {
        MSGID(DataType.UNSIGNEDINT, true, 8),
        RESULT(DataType.UNSIGNEDINT, true, 4);
        private DataType dataType;
        private boolean isFixFiledLength; 
        private int length;
        private SubmitResponse(DataType dataType, boolean isFixFiledLength, int length) {
            this.dataType = dataType;
            this.isFixFiledLength = isFixFiledLength;
            this.length = length;
        }
        public DataType getDataType() {
            return dataType;
        }
        public boolean isFixFiledLength() {
            return isFixFiledLength;
        }
        public boolean isFixPacketLength() {
        	return true;
        }
        public int getLength() {
            return length;
        } 
        public int getBodyLength() {
            int bodyLength = 0;
            for(SubmitResponse response : SubmitResponse.values()) {
                bodyLength += response.getLength();
            }
            return bodyLength;
        }
    } 
}

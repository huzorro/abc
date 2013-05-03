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
    enum DeliverRequest implements PacketStructure {
		MSGID(DataType.UNSIGNEDINT, true, 8),
		DESTID(DataType.OCTERSTRING, true, 21),
		SERVICEID(DataType.OCTERSTRING, true, 10),
		TPPID(DataType.UNSIGNEDINT, true, 1),
		TPUDHI(DataType.UNSIGNEDINT, true, 1),
		MSGFMT(DataType.UNSIGNEDINT, true, 1),
		SRCTERMINALID(DataType.OCTERSTRING, true, 32),
		SRCTERMINALTYPE(DataType.UNSIGNEDINT, true, 1),
		REGISTEREDDELIVERY(DataType.UNSIGNEDINT, true, 1),
		MSGLENGTH(DataType.UNSIGNEDINT, true, 1),
		MSGCONTENT(DataType.OCTERSTRING, false, 0),
		LINKID(DataType.OCTERSTRING, true, 20);
		
        private DataType dataType;
        private boolean isFixFiledLength; 
        private int length;
        
        private DeliverRequest(DataType dataType, boolean isFixFiledLength, int length) {
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
			return false;
		}

		@Override
		public int getLength() {
			return length;
		}

		@Override
		public int getBodyLength() {
            int bodyLength = 0;
            for(DeliverRequest response : DeliverRequest.values()) {
                bodyLength += response.getLength();
            }
            return bodyLength;
		}		    	
    }
    enum DeliverResponse implements PacketStructure {
		MSGID(DataType.UNSIGNEDINT, true, 8),
		RESULT(DataType.UNSIGNEDINT, true, 4);
		
        private DataType dataType;
        private boolean isFixFiledLength; 
        private int length;
        
        private DeliverResponse(DataType dataType, boolean isFixFiledLength, int length) {
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
            for(DeliverResponse response : DeliverResponse.values()) {
                bodyLength += response.getLength();
            }
            return bodyLength;
		}
    }
    enum ReportRequest implements PacketStructure {
    	MSGID(DataType.UNSIGNEDINT, true, 8),
    	STAT(DataType.OCTERSTRING, true, 7),
    	SUBMITTIME(DataType.OCTERSTRING, true, 10),
    	DONETIME(DataType.OCTERSTRING, true, 10),
    	DESTTERMINALID(DataType.OCTERSTRING, true, 32),
    	SMSCSEQUENCE(DataType.UNSIGNEDINT, true, 4);

        private DataType dataType;
        private boolean isFixFiledLength; 
        private int length;
        
        private ReportRequest(DataType dataType, boolean isFixFiledLength, int length) {
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
            for(ReportRequest response : ReportRequest.values()) {
                bodyLength += response.getLength();
            }
            return bodyLength;
		}
    }
    enum ActiveTestRequest implements PacketStructure {
        ;
        private DataType dataType;
        private boolean isFixFiledLength; 
        private int length;
        
        private ActiveTestRequest(DataType dataType, boolean isFixFiledLength, int length) {
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
            for(ActiveTestRequest response : ActiveTestRequest.values()) {
                bodyLength += response.getLength();
            }
            return bodyLength;
        }
    }
    enum ActiveTestResponse implements PacketStructure {
        ;
        private DataType dataType;
        private boolean isFixFiledLength; 
        private int length;
        
        private ActiveTestResponse(DataType dataType, boolean isFixFiledLength, int length) {
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
            for(ActiveTestResponse response : ActiveTestResponse.values()) {
                bodyLength += response.getLength();
            }
            return bodyLength;
        }        
    }
    enum TerminateRequest implements PacketStructure {
        ;
        private DataType dataType;
        private boolean isFixFiledLength; 
        private int length;
        
        private TerminateRequest(DataType dataType, boolean isFixFiledLength, int length) {
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
            for(TerminateRequest response : TerminateRequest.values()) {
                bodyLength += response.getLength();
            }
            return bodyLength;
        }        
    }
    enum TerminateResponse implements PacketStructure {
        ;
        private DataType dataType;
        private boolean isFixFiledLength; 
        private int length;
        
        private TerminateResponse(DataType dataType, boolean isFixFiledLength, int length) {
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
            for(TerminateResponse response : TerminateResponse.values()) {
                bodyLength += response.getLength();
            }
            return bodyLength;
        }  
    }
    enum CancelRequest implements PacketStructure {
    	MSGID(DataType.UNSIGNEDINT, true, 8);
        private DataType dataType;
        private boolean isFixFiledLength; 
        private int length;
        
        private CancelRequest(DataType dataType, boolean isFixFiledLength, int length) {
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
            for(CancelRequest response : CancelRequest.values()) {
                bodyLength += response.getLength();
            }
            return bodyLength;
        } 
    }
    enum CancelResponse implements PacketStructure {
    	SUCCESSID(DataType.UNSIGNEDINT, true, 4),;
        private DataType dataType;
        private boolean isFixFiledLength; 
        private int length;
        
        private CancelResponse(DataType dataType, boolean isFixFiledLength, int length) {
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
            for(CancelResponse response : CancelResponse.values()) {
                bodyLength += response.getLength();
            }
            return bodyLength;
        }        
    }
    enum QueryRequest implements PacketStructure {
        TIME(DataType.OCTERSTRING, true, 8),
        QUERYTYPE(DataType.UNSIGNEDINT, true, 1),
        QUERYCODE(DataType.OCTERSTRING, true, 10),
        RESERVE(DataType.OCTERSTRING, true, 8);
    	
        private DataType dataType;
        private boolean isFixFiledLength; 
        private int length;
        
        private QueryRequest(DataType dataType, boolean isFixFiledLength, int length) {
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
            for(QueryRequest response : QueryRequest.values()) {
                bodyLength += response.getLength();
            }
            return bodyLength;
        }          
    }
    enum QueryResponse implements PacketStructure {
    	TIME(DataType.OCTERSTRING, true, 8),    	
    	QUERYTYPE(DataType.UNSIGNEDINT, true, 1),
    	QUERYCODE(DataType.OCTERSTRING, true, 10),
    	MTTLMSG(DataType.UNSIGNEDINT, true, 4), 
    	MTTLUSR(DataType.UNSIGNEDINT, true, 4), 
    	MTSCS(DataType.UNSIGNEDINT, true, 4),   
    	MTWT(DataType.UNSIGNEDINT, true, 4),    
    	MTFL(DataType.UNSIGNEDINT, true, 4),    
    	MOSCS(DataType.UNSIGNEDINT, true, 4),   
    	MOWT(DataType.UNSIGNEDINT, true, 4),    
    	MOFL(DataType.UNSIGNEDINT, true, 4);    
    	
        private DataType dataType;
        private boolean isFixFiledLength; 
        private int length;
        
        private QueryResponse(DataType dataType, boolean isFixFiledLength, int length) {
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
            for(QueryResponse response : QueryResponse.values()) {
                bodyLength += response.getLength();
            }
            return bodyLength;
        }          
    }    
}

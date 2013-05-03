package me.huzorro.gateway;

import static java.lang.System.out;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.configuration.CombinedConfiguration;
import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.DefaultConfigurationBuilder;
import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.configuration.event.ConfigurationEvent;
import org.apache.commons.configuration.event.ConfigurationListener;
import org.apache.commons.lang.ArrayUtils;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Strings;
import com.google.common.hash.HashFunction;
import com.google.common.primitives.Bytes;
import com.google.common.primitives.Ints;
import com.google.common.primitives.Shorts;
import com.google.common.primitives.UnsignedLongs;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public class CommonsConfigTest {
    private final Logger log = LoggerFactory.getLogger(CommonsConfigTest.class);
    private CompositeConfiguration config = new CompositeConfiguration();;
    private DefaultConfigurationBuilder configBuilder = GlobalVars.configBuilder;
    /**
     * 
     */
    public CommonsConfigTest() {
        // TODO Auto-generated constructor stub
    }
    public CommonsConfigTest initConfig() {
        try {            
            config.addConfiguration(new XMLConfiguration("session.xml"));
        } catch (ConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return this;
    }
    
    public void t() {
        XMLConfiguration xmlConfig = (XMLConfiguration) config.getConfiguration(0);
        HierarchicalConfiguration sub = xmlConfig.configurationAt("sessions");
        log.info(sub.toString());
        List<HierarchicalConfiguration> h = sub.configurationsAt("session");
        for(HierarchicalConfiguration hs : h) {
            log.info(hs.toString());
            log.info(hs.getString("id"));
        }
        
    }
    public String useBuilder() {
//        configBuilder.setFileName("configuration.xml");
        CombinedConfiguration cc = null;
        try {
            cc = configBuilder.getConfiguration(true);
        } catch (ConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        XMLConfiguration xmlConfig = (XMLConfiguration) cc
                .getConfiguration("session");
        xmlConfig.addConfigurationListener(new ConfigurationListener() {
            
            @Override
            public void configurationChanged(ConfigurationEvent event) {
                // TODO Auto-generated method stub
                log.info("changed");
            }
        });
        HierarchicalConfiguration sub = xmlConfig.configurationAt("sessions");
        log.info(sub.toString());
        List<HierarchicalConfiguration> h = sub.configurationsAt("session");
            
            
            while(true) {
            for(int i = 0; i < h.size(); i++) {
                log.debug(xmlConfig.getString(String.format("sessions.session(%1$d).channelIds", i)));
                
            }
            try {
                Thread.sleep(10 * 1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
    }
    
    public void useBuilderProp() {
//        configBuilder.setFileName("configuration.xml");
        CombinedConfiguration cc = null;
        try {
            cc = configBuilder.getConfiguration(true);
        } catch (ConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        PropertiesConfiguration propConfig = (PropertiesConfiguration) cc.getConfiguration("dev");
        log.info(propConfig.getString("spid"));
        int[] s = {1,2,3,4};
        for(int i = 0; i < 10; i++) {
            try{
                log.info("elem is {}");
            } catch (Exception e) {
                log.error("this is {}", e);
            }
        }
        
    }
    public void tM(M m) {
        M s = m;
        System.out.println(m.getMint());
        s.setMint(123);
    }
    static class M {
        private int mint;
        public void setMint(int i) {
            this.mint = i;
        }
        public int getMint() {
            return mint;
        }
    }
    public static Long md5HashingAlg(String key) {
        MessageDigest md5 = null;
        if(md5==null) {
            try {
                md5 = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                throw new IllegalStateException( "++++ no md5 algorythm found");            
            }
        }

        md5.reset();
        md5.update(key.getBytes());
        byte[] bKey = md5.digest();
        long res = ((long)(bKey[3]&0xFF) << 24) | ((long)(bKey[2]&0xFF) << 16) | ((long)(bKey[1]&0xFF) << 8) | (long)(bKey[0]&0xFF);
        return res;
    }


    static class ConsistentHash<T> {

     private final HashFunction hashFunction;
     private final int numberOfReplicas;
     private final SortedMap<Long, T> circle = new TreeMap<Long, T>();

     public ConsistentHash(HashFunction hashFunction, int numberOfReplicas,
         Collection<T> nodes) {
       this.hashFunction = hashFunction;
       this.numberOfReplicas = numberOfReplicas;

       for (T node : nodes) {
         add(node);
       }
     }

     public void add(T node) {
       for (int i = 0; i < numberOfReplicas; i++) {
//           HashCode hc = hashFunction.newHasher().putString(node.toString() + i).hash();
         circle.put(hashFunction.hashString(node.toString() + i).asLong(), node);
       }
     }

     public void remove(T node) {
       for (int i = 0; i < numberOfReplicas; i++) {
//           HashCode hc = hashFunction.newHasher().putString(node.toString() + i).hash();
         circle.remove(hashFunction.hashString(node.toString() + i).asLong());
       }
     }

     public T get(Object key) {
       if (circle.isEmpty()) {
         return null;
       }
//       HashCode hc = hashFunction.newHasher().putString(key).hash();
       Long hash = hashFunction.hashString(key.toString()).asLong();
       if (!circle.containsKey(hash)) {
         SortedMap<Long, T> tailMap = circle.tailMap(hash);
         hash = tailMap.isEmpty() ? circle.firstKey() : tailMap.firstKey();
       }
       return circle.get(hash);
     }

    }
    
    public static String strToMD5(String str) {     
        byte[] auth = null;
        try {
            auth = str.getBytes("GBK");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        byte[] md5 = null;
        try {
            MessageDigest alga = MessageDigest.getInstance("MD5");
            alga.update(auth);
            md5 = alga.digest();
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }        
        StringBuilder md5Str = new StringBuilder();
        for (byte b : md5) {
            md5Str.append(String.format("%02X", b));
        }
        System.out.println(md5.length + "@");
        return md5Str.toString();
    }
    
    public static String decodeMsgid(byte[] buffer) {
        byte[] dateBuf = new byte[4];
        byte[] gateWayIDBuf = new byte[4];
        byte[] serialBuf = new byte[3];
        BigInteger aa = new BigInteger(buffer);

        System.arraycopy(buffer, 0, dateBuf, 0, 4);
        System.arraycopy(buffer, 3, gateWayIDBuf, 1, 3);
        System.arraycopy(buffer, 6, serialBuf, 1, 2);

        BigInteger bigIntdate = new BigInteger(dateBuf);
        BigInteger bigIntGateID = new BigInteger(gateWayIDBuf);
        BigInteger bigIntSerial = new BigInteger(serialBuf);

        int intDate = bigIntdate.intValue();
        int intGateID = bigIntGateID.intValue();
        int intSerial = bigIntSerial.intValue();

        intGateID = (intGateID) & 0x3fffff;
        intSerial = (intSerial) & 0xffff;

        int mon = intDate >>> 28;
        int day = (intDate >>> 23) & 0x1f;
        int hh = (intDate >>> 18) & 0x1f;
        int mm = (intDate >>> 12) & 0x3f;
        int ss = (intDate >>> 6) & 0x3f;

        StringBuffer msgIDBuf = new StringBuffer();
        DecimalFormat df = new DecimalFormat("#");
        df.setMinimumIntegerDigits(2);

        msgIDBuf.append(df.format(mon));
        msgIDBuf.append(df.format(day));
        msgIDBuf.append(df.format(hh));
        msgIDBuf.append(df.format(mm));
        msgIDBuf.append(df.format(ss));

        df.setMinimumIntegerDigits(6);

        msgIDBuf.append(df.format(intGateID));
        df.setMinimumIntegerDigits(5);
        msgIDBuf.append(df.format(intSerial));
        return msgIDBuf.toString();
      }  
    public static String parseMsgId(byte[] msgId) {
        long result = ByteBuffer.wrap(msgId).getLong();
        out.println(result);
        int month = (int)((result >>> 60) & 0xf);
        int day = (int)((result >>> 55) & 0x1f);
        int hour = (int)((result >>> 50) & 0x1f);
        int min = (int)((result >>> 44) & 0x3f);
        int sec = (int)((result >>> 38) & 0x3f);
        int gate = (int)((result >>> 16) & 0x3fffff);
        int sequence = (int)(result & 0xffff);
        return String.format("%1$02d%2$02d%3$02d%4$02d%5$02d%6$07d%7$05d", month, day, hour, min, sec, gate, sequence);
    }    
    public static byte[] createMsgId(int...msgId) {
        byte[] tmp = new byte[8];
        long result = 0;
        result |= (long)msgId[0] << 60L;
        result |= (long)msgId[1] << 55L;
        result |= (long)msgId[2] << 50L;
        result |= (long)msgId[3] << 44L;
        result |= (long)msgId[4] << 38L;
        result |= (long)msgId[5] << 16L;
        result |= (long)msgId[6] & 0xffffL;
        ByteBuffer.wrap(tmp).putLong(result);           
        return tmp;
    }
    
    public static void linkedListTest() {
        List<String> list = new LinkedList<String>();
        
        list.add("a");
        list.add("b");
        
        ListIterator<String> nit = list.listIterator();
        ListIterator<String> pit = list.listIterator(list.size());
        while(nit.hasNext()) {
            out.println(nit.next());
        }
        
        while(pit.hasPrevious()) {
            out.println(pit.previous());
        }
    }
    
    public static long getSequenceId() {
    	return GlobalVars.sequenceId.compareAndSet(Integer.MAX_VALUE, 0) 
    			? GlobalVars.sequenceId.getAndIncrement() 
    			: GlobalVars.sequenceId.getAndIncrement();
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
    	int a = 123;
    	long b = 123;
    	Object o = Long.valueOf(Integer.toString(a)); 
    	System.out.println(o);
    	System.out.println(((long) o) == b);
    	
    	byte[] bytes = "abc".getBytes();
    	
    	String hexStr = Hex.encodeHexString(bytes);
    	out.println(hexStr);
    	
    	
    	try {
    		byte[] bs = Hex.decodeHex(hexStr.toCharArray());
    		out.println(new String(ArrayUtils.subarray(bs, 0, 3)) + "~~");
		} catch (DecoderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	byte[] ints = Ints.toByteArray(123);
    	
    	out.println(Ints.fromByteArray(ints) + "##");
    	
    	out.println(Ints.fromByteArray(ArrayUtils.subarray(ints, 0, 4)));
    	
    	
    	
    	out.println(Strings.repeat("-", 30));
    	byte[] mixBytes = Bytes.concat(bytes, ints); 
    	
    	String mixString = Hex.encodeHexString(mixBytes);
    	out.println(mixString + "#%");
    	byte[] convMixBytes = null;
    	try {
			convMixBytes = Hex.decodeHex(mixString.toCharArray());
			out.println(convMixBytes.length);
		} catch (DecoderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	out.println(new String(ArrayUtils.subarray(convMixBytes, 0, 3)));
    	
    	out.println(Ints.fromByteArray(ArrayUtils.subarray(convMixBytes, 3, 7)));
    	
    	out.println(15 & 0x01);
    	
    	byte[] bb = new byte[8];
    	
    	out.println(Hex.encodeHexString(bb));
    	
    	out.println(Strings.repeat("==", 50));
    	
    	String msgidHexStr = "572806C003F22471";
    	
    	try {
    		out.println(Strings.repeat("=", 100));
			byte[] msgidBytes = Hex.decodeHex(msgidHexStr.toCharArray());
			String msgStr = CommonsConfigTest.decodeMsgid(msgidBytes);
			out.println(msgStr);
			out.println(CommonsConfigTest.parseMsgId(msgidBytes));
			out.println(Strings.repeat("=", 100));
			
		} catch (DecoderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	out.println(UnsignedLongs.parseUnsignedLong(msgidHexStr, 16));
    	
    	out.println(Hex.encodeHexString(CommonsConfigTest.createMsgId(5,14,10,0,27,1010,9329)));
    	
    	out.println(Hex.encodeHexString(new byte[]{0xf}));
    	
//    	信息标识。
//    	生成算法如下：
//    	采用64位（8字节）的整数：
//    	（1）	时间（格式为MMDDHHMMSS，即月日时分秒）：bit64~bit39，其中
//    	bit64~bit61：月份的二进制表示；
//    	bit60~bit56：日的二进制表示；
//    	bit55~bit51：小时的二进制表示；
//    	bit50~bit45：分的二进制表示；
//    	bit44~bit39：秒的二进制表示；
//    	（2）	短信网关代码：bit38~bit17，把短信网关的代码转换为整数填写到该字段中；
//    	（3）	序列号：bit16~bit1，顺序增加，步长为1，循环使用。
//    	各部分如不能填满，左补零，右对齐。
    	
    	out.println(String.format("%010d", 123));
    	
    	short sh = -123 & 0xff;
    	byte by = (byte)133;

    	out.println(by);
    	
    	byte[] shs = Shorts.toByteArray(sh);
    	
    	byte[] bys = ArrayUtils.subarray(shs, 1, 8);
    	
    	out.println(ByteBuffer.wrap(bys).get());
    	
    	CommonsConfigTest.linkedListTest();
    	
    	GlobalVars.sequenceId.set(Long.MAX_VALUE);
    	out.println(GlobalVars.sequenceId.get());
    	for(int i = 0 ; i < 10; i++) {
        	out.println(GlobalVars.sequenceId.incrementAndGet());
    	}
    	
    	
    	out.println(Strings.repeat("=", 100));
    	out.println(GlobalVars.sequenceId.get());
    	out.println(CommonsConfigTest.getSequenceId());
    	out.println(CommonsConfigTest.getSequenceId());
    	
    	out.println(System.currentTimeMillis());
    	
    	String str = String.format("%TF %<TT", System.currentTimeMillis());
    	
    	out.println(str);
    	
    	out.println(String.format("%tY-%<tm-%<td %<tH:%<tM:%<tS", System.currentTimeMillis()));
    	
    	
    	
    	out.println(Strings.repeat("//", 60));
    	
    	ChannelBuffer _dc = ChannelBuffers.dynamicBuffer();
    	
    	
    	
    	byte[] _gb = Bytes.ensureCapacity("我a".getBytes(GlobalVars.defaultLocalCharset), 16, 0);
    	
    	out.println(_gb.length);
    	
    	out.println(new String(_gb, GlobalVars.defaultTransportCharset).trim() + "//");
    	
    	_dc.writeBytes(_gb);
    	
    	out.println(_dc.toString(GlobalVars.defaultLocalCharset) + "//");
    	
    	ChannelBuffer _cBuffer = ChannelBuffers.dynamicBuffer();
    	
    	_cBuffer.writeBytes("Abc".getBytes(GlobalVars.defaultTransportCharset));
    	
    	out.println(_cBuffer.readBytes(0).array().length);
    	out.println(_cBuffer.readBytes(0).toString(GlobalVars.defaultTransportCharset) + "//");
    	out.println(_cBuffer.readableBytes() + "//");
    	
    	
    	String s1 = new MsgId().toString();
    	String s2 = new MsgId().toString(); 
    	
    	out.println(s1.compareTo(s2));
    	
    	Long lastKey = Long.valueOf(1);
    	lastKey = (lastKey == null) ? 1L : ++lastKey;
    	
    	out.println(lastKey);
//    	System.out.println("@" + new String(
//    			new byte[0],
//    			GlobalVars.defaultTransportCharset) + "@");
//    	System.out.println(new byte[0].length);
//    	System.out.println("\0".equals(new String(new byte[1], GlobalVars.defaultLocalCharset)));
//        System.out.println(DateFormatUtils.format(System.currentTimeMillis(), "MMddHHmmss"));
//
//        System.out.println(Long.parseLong(DateFormatUtils.format(System.currentTimeMillis(), "MMddHHmmss")));
//        
//        long t = Long.parseLong(DateFormatUtils.format(System.currentTimeMillis(), "MMddHHmmss"));
//        
//        System.out.println(Long.toString(t).length());
//        Message<ChannelBuffer> m = new DefaultMessage<ChannelBuffer>();
//        DefaultHead<ChannelBuffer> h = new DefaultHead<ChannelBuffer>();
//        m.setHeader(h);
//        m.getHeader().getHeadBuffer();
//        MessageFuture mf = new MessageFuture(m);
//        Collection<Integer> nodes = new LinkedList<Integer>();
//        for(int i = 0; i < 1; i++) {
//            nodes.add(new Integer(i));
//        }
//        ConsistentHash<Integer> c = new ConsistentHash<Integer>(Hashing.md5(), 100, nodes);
//        for(int i = 0; i < 10; i++) {
//            System.out.println(c.get(System.nanoTime()));

//            System.out.println(CommonsConfigTest.md5HashingAlg(Integer.toString(i)) % 10);
//            System.out.println(Hashing.consistentHash(i, 10));
//        }
//        new CommonsConfigTest().initConfig().t();
//        new CommonsConfigTest().useBuilder();
//        new CommonsConfigTest().useBuilderProp();
//        try {
//            Map<String, SessionConfig> m = new CmppUpstreamSessionConfigMapBuilder(
//                    GlobalVars.configBuilder,
//                    GlobalVars.upstreamSessionConfigMap);
//            
//            while(true) {
//                for(Entry<String, SessionConfig> e : m.entrySet()) {                    
//                    System.out.println(String.format("%1$s=%2$s", e.getKey(), e.getValue()));
//                }
//                try {
//                    Thread.sleep(10 * 1000);
//                } catch (InterruptedException e1) {
//                    // TODO Auto-generated catch block
//                    e1.printStackTrace();
//                }
//            }
//        } catch (ConfigurationException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        M m = new M();
//        m.setMint(789);
//        new CommonsConfigTest().tM(m);
//        System.out.println(m.getMint());        
//        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
//        ExecutorService s = Executors.newScheduledThreadPool(0);
//        
//        service.shutdown();
        
        
//        //测试MD5
//        System.out.println(CommonsConfigTest.strToMD5("abc"));
//        System.out.println(DigestUtils.md5Hex("abc"));
//        byte[] b = DigestUtils.md5("abc");
//        System.out.println(Hex.encodeHexString(b));
////        ArrayUtils.addAll(array1, array2)
//        CmppConnectRequestMessage<ChannelBuffer> r = new CmppConnectRequestMessage<ChannelBuffer>();
//        r.setResponse(new CmppConnectResponseMessage<ChannelBuffer>());
//        //
//        ChannelBuffer buffer = ChannelBuffers.dynamicBuffer();
//        
//        buffer.writeInt(Integer.MAX_VALUE);
//        buffer.writeBytes("中国人".getBytes(Charset.forName("GBK")));
//        buffer.writeBytes("abc".getBytes(Charset.forName("GBK")));
//        buffer.writeInt(456);
//        buffer.markReaderIndex();
//        //---------------------------------------------
//        out.println("~~~~~~");
//        out.println(buffer.readInt());
//        out.println(buffer.toString(4, 6, Charset.forName("GBK")));
//        buffer.skipBytes(6);
//        out.println(buffer.toString(10, 3, Charset.forName("GBK")));
//        buffer.skipBytes(3);
//        out.println(buffer.readInt());
//        out.println("~~~~~~");
//        long act = 4147483647L;
//        
//        byte[] actBytes = Longs.toByteArray(act);
//        
//        byte[] actUnsignedInts = ArrayUtils.subarray(actBytes, 4, 8);
//        
//        ChannelBuffer cbs = ChannelBuffers.dynamicBuffer();
//        
//        cbs.writeBytes(actUnsignedInts);
//        
//        out.println(cbs.readUnsignedInt());
//        
//        //--------------------------------
//        
//        byte[] a = new byte[4];
//        buffer.readBytes(a);
//        buffer.resetReaderIndex();
//        String ss = Hex.encodeHexString(a);
//        System.out.println(ss);
//        System.out.println(UnsignedLongs.parseUnsignedLong(ss, 16));
//        System.out.println(buffer.readUnsignedInt());
//        buffer.resetReaderIndex();
//        System.out.println(buffer.readInt());
//        
//        System.out.println(UnsignedLongs.toString(10000L, 2));
//        
//        String t = UnsignedLongs.toString(10000L, 2);
//        
//        System.out.println(UnsignedLongs.parseUnsignedLong(t, 2));
//        
//        byte[] ba = Ints.toByteArray(Integer.MAX_VALUE);
//        
//        System.out.println(ba.length + "#");
//        byte[] la = Longs.toByteArray(Integer.MAX_VALUE);
//        System.out.println(la.length);
//        
////        ArrayUtils.remove(la, 32);
//        byte[] lb = ArrayUtils.subarray(la, 4, 8);
//        out.println("-------------------lb" + lb.length);
//        int i = Ints.fromByteArray(lb);
//        
//        
//        out.println("--------------" + i);
//        
//        //-----------------------------------------
//        BinaryCodec bc = new BinaryCodec();
//        String as = BinaryCodec.toAsciiString(a);
//        System.out.println(as);
//        System.out.println(UnsignedLongs.parseUnsignedLong(as, 2));                
//        //-----------------------------------------------------
//        
//        System.out.println(DataType.UNSIGNEDINT.getAllCommandId() ^ DataType.UNSIGNEDINT.getCommandId());
//        
//        System.out.println(me.huzorro.gateway.cmpp.Head.COMMANDID.getHeadLength());
//        
//        out.println(PacketType.CMPPCANCELREQUEST.getAllCommandId() & PacketType.CMPPACTIVETESTREQUEST.getCommandId());
//        
//        //---------------------
//        
//        
//        @SuppressWarnings("rawtypes")
//        ConsistentHashQueueGroup<BlockingQueue<Message>, Message> cq = 
//                new ConsistentHashQueueGroup<BlockingQueue<Message>, Message>(
//                        new LinkedList<BlockingQueue<Message>>());
        
    }
    

}

package me.huzorro.gateway;

import java.io.UnsupportedEncodingException;
import static java.lang.System.out;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import me.huzorro.gateway.cmpp.DataType;
import me.huzorro.gateway.cmpp.PacketStructure;
import me.huzorro.gateway.cmpp.PacketType;

import org.apache.commons.codec.binary.BinaryCodec;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.configuration.CombinedConfiguration;
import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.ConfigurationFactory;
import org.apache.commons.configuration.DefaultConfigurationBuilder;
import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.configuration.event.ConfigurationEvent;
import org.apache.commons.configuration.event.ConfigurationListener;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;
import com.google.common.primitives.UnsignedInts;
import com.google.common.primitives.UnsignedLongs;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public class CommonsConfigTest {
    private final Logger log = LoggerFactory.getLogger(CommonsConfigTest.class);
    private CompositeConfiguration config = GlobalVars.config;
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
    public void useBuilder() {
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
                log.debug(xmlConfig.getString(String.format("sessions.session(%1$d).id", i)));
                
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
    
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        Message<ChannelBuffer> m = new DefaultMessage<ChannelBuffer>();
        DefaultHead<ChannelBuffer> h = new DefaultHead<ChannelBuffer>();
        m.setHeader(h);
        m.getHeader().getHeadBuffer();
        MessageFuture mf = new MessageFuture(m);
        Collection<Integer> nodes = new LinkedList<Integer>();
        for(int i = 0; i < 1; i++) {
            nodes.add(new Integer(i));
        }
        ConsistentHash<Integer> c = new ConsistentHash<Integer>(Hashing.md5(), 100, nodes);
        for(int i = 0; i < 10; i++) {
            System.out.println(c.get(System.nanoTime()));

//            System.out.println(CommonsConfigTest.md5HashingAlg(Integer.toString(i)) % 10);
//            System.out.println(Hashing.consistentHash(i, 10));
        }
//        new CommonsConfigTest().initConfig().t();
//        new CommonsConfigTest().useBuilder();
//        new CommonsConfigTest().useBuilderProp();
//        try {
//            Map<String, SessionConfig> m = new CmppUpstreamSessionConfigMapBuilder(
//                    GlobalVars.configBuilder,
//                    GlobalVars.upstreamSessionConfigMap).builder();
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
        
        
        //测试MD5
        System.out.println(CommonsConfigTest.strToMD5("abc"));
        System.out.println(DigestUtils.md5Hex("abc"));
        byte[] b = DigestUtils.md5("abc");
        System.out.println(Hex.encodeHexString(b));
//        ArrayUtils.addAll(array1, array2)
        CmppConnectRequestMessage<ChannelBuffer> r = new CmppConnectRequestMessage<ChannelBuffer>();
        r.setResponse(new CmppConnectResponseMessage<ChannelBuffer>());
        //
        ChannelBuffer buffer = ChannelBuffers.dynamicBuffer();
        
        buffer.writeInt(Integer.MAX_VALUE);
        buffer.writeBytes("中国人".getBytes(Charset.forName("GBK")));
        buffer.writeBytes("abc".getBytes(Charset.forName("GBK")));
        buffer.writeInt(456);
        buffer.markReaderIndex();
        //---------------------------------------------
        out.println("~~~~~~");
        out.println(buffer.readInt());
        out.println(buffer.toString(4, 6, Charset.forName("GBK")));
        buffer.skipBytes(6);
        out.println(buffer.toString(10, 3, Charset.forName("GBK")));
        buffer.skipBytes(3);
        out.println(buffer.readInt());
        out.println("~~~~~~");
        long act = 4147483647L;
        
        byte[] actBytes = Longs.toByteArray(act);
        
        byte[] actUnsignedInts = ArrayUtils.subarray(actBytes, 4, 8);
        
        ChannelBuffer cbs = ChannelBuffers.dynamicBuffer();
        
        cbs.writeBytes(actUnsignedInts);
        
        out.println(cbs.readUnsignedInt());
        
        //--------------------------------
        
        byte[] a = new byte[4];
        buffer.readBytes(a);
        buffer.resetReaderIndex();
        String ss = Hex.encodeHexString(a);
        System.out.println(ss);
        System.out.println(UnsignedLongs.parseUnsignedLong(ss, 16));
        System.out.println(buffer.readUnsignedInt());
        buffer.resetReaderIndex();
        System.out.println(buffer.readInt());
        
        System.out.println(UnsignedLongs.toString(10000L, 2));
        
        String t = UnsignedLongs.toString(10000L, 2);
        
        System.out.println(UnsignedLongs.parseUnsignedLong(t, 2));
        
        byte[] ba = Ints.toByteArray(Integer.MAX_VALUE);
        
        System.out.println(ba.length + "#");
        byte[] la = Longs.toByteArray(Integer.MAX_VALUE);
        System.out.println(la.length);
        
//        ArrayUtils.remove(la, 32);
        byte[] lb = ArrayUtils.subarray(la, 4, 8);
        out.println("-------------------lb" + lb.length);
        int i = Ints.fromByteArray(lb);
        
        
        out.println("--------------" + i);
        
        //-----------------------------------------
        BinaryCodec bc = new BinaryCodec();
        String as = BinaryCodec.toAsciiString(a);
        System.out.println(as);
        System.out.println(UnsignedLongs.parseUnsignedLong(as, 2));                
        //-----------------------------------------------------
        
        System.out.println(DataType.UNSIGNEDINT.getAllCommandId() ^ DataType.UNSIGNEDINT.getCommandId());
        
        System.out.println(me.huzorro.gateway.cmpp.Head.COMMANDID.getHeadLength());
        
        out.println(PacketType.CMPPCANCELREQUEST.getAllCommandId() & PacketType.CMPPACTIVETESTREQUEST.getCommandId());
        
        //---------------------
        
        
        @SuppressWarnings("rawtypes")
        ConsistentHashQueueGroup<BlockingQueue<Message>, Message> cq = 
                new ConsistentHashQueueGroup<BlockingQueue<Message>, Message>(
                        new LinkedList<BlockingQueue<Message>>());
        
    }
    

}

package me.huzorro.gateway;

import java.nio.charset.Charset;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.jboss.netty.buffer.ChannelBuffer;

import com.google.common.primitives.Bytes;
import com.google.common.primitives.Longs;

import me.huzorro.gateway.cmpp.Head;
import me.huzorro.gateway.cmpp.PacketStructure;
import me.huzorro.gateway.cmpp.PacketType;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 * @param <T>
 */
public class CmppConnectRequestMessageFactory<T> implements Factory<T> {
    private PacketType packetType;
    private AtomicLong sequenceId = new AtomicLong();
    private CmppUpstreamSessionConfig config;
    /**
     * 
     */
    public CmppConnectRequestMessageFactory(CmppUpstreamSessionConfig config) {
        this(PacketType.CMPPCONNECTREQUEST, config);
    }
    public CmppConnectRequestMessageFactory(PacketType packetType, CmppUpstreamSessionConfig config) {
        this.packetType = packetType;
        this.config = config;
    }

    /* (non-Javadoc)
     * @see me.huzorro.gateway.Factory#create()
     */
    @Override
    @SuppressWarnings("unchecked")
    public T create() throws Exception {
        Header<ChannelBuffer> header = new DefaultHead<ChannelBuffer>();
        header.setCommandId(packetType.getCommandId());
        header.setHeadLength(Head.COMMANDID.getHeadLength());
        header.setBodyLength(PacketStructure.ConnectRequest.SOURCEADDR.getBodyLength());
        header.setPacketLength(header.getHeadLength() + header.getBodyLength());
        header.setSequenceId(sequenceId.getAndIncrement());
        
        CmppConnectRequestMessage<ChannelBuffer> message = new CmppConnectRequestMessage<ChannelBuffer>();
        message.setSourceAddr(config.getUser());
        message.setChannelIds(config.getChannelIds());
        message.setVersion(config.getVersion());
        String timestamp = DateFormatUtils.format(System.currentTimeMillis(), "MMddHHmmss");
        message.setTimestamp(Long.parseLong(timestamp));
        
        byte[] userBytes = config.getUser().getBytes(Charset.forName("GBK"));
        byte[] passwdBytes = config.getPasswd().getBytes(Charset.forName("GBK"));
        
        byte[] timestampBytes = timestamp.getBytes(Charset.forName("GBK"));
        
        message.setAuthenticatorSource(DigestUtils.md5(Bytes.concat(userBytes, new byte[9], passwdBytes, timestampBytes)));
        message.setHeader(header);
        return (T) message;
    }

}

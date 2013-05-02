package me.huzorro.gateway;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.jboss.netty.buffer.ChannelBuffer;

import com.google.common.primitives.Bytes;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 * @param <T>
 */
public class CmppConnectRequestMessageFactory<T> implements Factory<T> {
    private CmppUpstreamSessionConfig config;
    public CmppConnectRequestMessageFactory(CmppUpstreamSessionConfig config) {
        this.config = config;
    }

    /* (non-Javadoc)
     * @see me.huzorro.gateway.Factory#create()
     */
    @Override
    @SuppressWarnings("unchecked")
    public T create() throws Exception {
        
        CmppConnectRequestMessage<ChannelBuffer> message = new CmppConnectRequestMessage<ChannelBuffer>();
        message.setSourceAddr(config.getUser());
        message.setChannelIds(config.getChannelIds());
        message.setVersion(config.getVersion());
        String timestamp = DateFormatUtils.format(System.currentTimeMillis(), "MMddHHmmss");
        message.setTimestamp(Long.parseLong(timestamp));
        
        byte[] userBytes = config.getUser().getBytes(GlobalVars.defaultTransportCharset);
        byte[] passwdBytes = config.getPasswd().getBytes(GlobalVars.defaultTransportCharset);
        
        byte[] timestampBytes = timestamp.getBytes(GlobalVars.defaultTransportCharset);
        
        message.setAuthenticatorSource(DigestUtils.md5(Bytes.concat(userBytes, new byte[9], passwdBytes, timestampBytes)));
        return (T) message;
    }

}

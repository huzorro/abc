/**
 * 
 */
package me.huzorro.gateway;

import org.jboss.netty.buffer.ChannelBuffer;

/**
 * @author huzorro
 * @param <T>
 *
 */
public class CmppTerminateRequestMessage<T extends ChannelBuffer> extends DefaultMessage<T> {
	private static final long serialVersionUID = 814288661389104951L;

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("CmppTerminateRequestMessage [toString()=%s]",
				super.toString());
	}
	
}

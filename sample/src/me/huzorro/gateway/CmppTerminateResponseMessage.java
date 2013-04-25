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
public class CmppTerminateResponseMessage<T extends ChannelBuffer> extends DefaultMessage<T> {
	private static final long serialVersionUID = -2657187574508760595L;

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("CmppTerminateResponseMessage [toString()=%s]",
				super.toString());
	}
	
}

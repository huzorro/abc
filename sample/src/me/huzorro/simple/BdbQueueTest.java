package me.huzorro.simple;

import static java.lang.System.out;

import org.jboss.netty.buffer.ChannelBuffer;

import com.sun.jndi.url.iiopname.iiopnameURLContextFactory;

import me.huzorro.gateway.CmppSubmitRequestMessage;
import me.huzorro.gateway.MessageFuture;

public class BdbQueueTest {

	public BdbQueueTest() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		BdbQueueMap<Long, CmppSubmitRequestMessage<ChannelBuffer>> bdbQueue = new BdbQueueMap<Long, CmppSubmitRequestMessage<ChannelBuffer>>("Z:\\", "m1");
//		for (int i = 0; i < 100; i++) {
//			try {
//				bdbQueue.put(new CmppSubmitRequestMessage<ChannelBuffer>());
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
////		
////		bdbQueue.remove(Long.valueOf(97), null);
//		bdbQueue.close();

		
		BdbQueueMap<Long, MessageFuture> bdbQueue = new BdbQueueMap<Long, MessageFuture>("z:\\temp", "deliver1");
		out.println(bdbQueue.size());
		for(int i = 0; i < 10; i++) {
			try {
				out.println(bdbQueue.take().getMessage());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}

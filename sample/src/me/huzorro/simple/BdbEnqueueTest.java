/**
 * 
 */
package me.huzorro.simple;

import static java.lang.System.out;

import java.io.File;
import java.util.Iterator;

import org.jboss.netty.buffer.ChannelBuffer;

import com.sun.org.apache.bcel.internal.generic.NEW;

import me.huzorro.gateway.CmppDeliverRequestMessage;
import me.huzorro.gateway.Message;
import me.huzorro.gateway.CmppSubmitRequestMessage;
import me.huzorro.gateway.MsgId;

/**
 * @author huzorro
 *
 */
public class BdbEnqueueTest {

	/**
	 * 
	 */
	public BdbEnqueueTest() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BdbEnvironment bdbEnvironment = new BdbEnvironment("Z:\\");
		BdbEnvironment bdbEnvironment2 = new BdbEnvironment("Z:\\");
		final BdbMessageQueue<Message> bdbMessageQueue = new BdbMessageQueue<Message>(bdbEnvironment, "message");
		final BdbMessageQueue<Message> _bdbMessageQueue = new BdbMessageQueue<Message>(bdbEnvironment2, "message");
		
		out.println(bdbEnvironment.getEnvironment().getConfig().isConfigParamSet("je.log.fileMax"));
		out.println(bdbEnvironment.getEnvironment().getConfig());
//		new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				for(int i = 0; i< 100; i++) {
//					bdbMessageQueue.enqueue(new CmppSubmitRequestMessage<ChannelBuffer>());
//				}				
//			}
//		}).start();
		
//		for(int i = 0; i< 100; i++) {
//			bdbMessageQueue.enqueue(new CmppSubmitRequestMessage<ChannelBuffer>());
//			_bdbMessageQueue.enqueue(new CmppDeliverRequestMessage<ChannelBuffer>());
//		}	
//		bdbEnvironment.getEnvironment().cleanLog();
//		bdbEnvironment.getJavaCatalog().close();
//		bdbMessageQueue.close();
//		bdbEnvironment.getEnvironment().close();
//		
//		bdbEnvironment2.getEnvironment().cleanLog();
//		bdbEnvironment2.getJavaCatalog().close();
//		_bdbMessageQueue.close();
//		bdbEnvironment2.getEnvironment().close();
		
//		for(int i = 0; i< 100; i++) {
//		bdbMessageQueue.enqueue(new CmppSubmitRequestMessage<ChannelBuffer>());
//		_bdbMessageQueue.enqueue(new CmppDeliverRequestMessage<ChannelBuffer>());
//	}
		
//
//		for (int i = 0; i < 100; i++) {
//			bdbMessageQueue.dequeue();
//			_bdbMessageQueue.dequeue();
//		}
//		bdbEnvironment.getEnvironment().cleanLog();
//		bdbEnvironment.getJavaCatalog().close();
//		bdbMessageQueue.close();
//		bdbEnvironment.getEnvironment().close();	
		
//		new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				while (true) {
//					bdbMessageQueue.dequeue();
//				}
//			}
//		}).start();
//		
//		new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				while (true) {
//					_bdbMessageQueue.dequeue();
//				}
//			}
//		}).start();
		
//		Thread[] threads = new Thread[100];
//		for (int i = 0; i < threads.length; i++) {
//			threads[i] = new Thread(new Runnable() {
//				
//				@Override
//				public void run() {
//					bdbMessageQueue.dequeue();
//				}
//			});			
//		}
//		
//		for (Thread thread : threads) {
//			thread.start();
//		}
		
	}
	
	

}

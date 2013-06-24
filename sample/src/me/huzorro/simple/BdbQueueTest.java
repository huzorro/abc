package me.huzorro.simple;

import me.huzorro.gateway.CmppSubmitRequestMessage;
import me.huzorro.gateway.DefaultFuture;
import me.huzorro.gateway.Message;
import me.huzorro.gateway.QFuture;

public class BdbQueueTest {

	public BdbQueueTest() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BdbQueueMap<Long, QFuture<Message>> bdbQueue = new BdbQueueMap<Long, QFuture<Message>>("Z:\\temp", "request3");
		
		for (int i = 0; i < 1000; i++) {
			try {
				CmppSubmitRequestMessage cmppSubmitRequestMessage = new CmppSubmitRequestMessage();
				cmppSubmitRequestMessage.setChannelIds("901077");
				cmppSubmitRequestMessage.setMsgContent("a");
				cmppSubmitRequestMessage.setRegisteredDelivery((short)0);
				QFuture<Message> messageFuture = new DefaultFuture<Message>(cmppSubmitRequestMessage);
				bdbQueue.put(messageFuture);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
//		BdbQueueMap<Long, ByteBufferTest> bdbQueue = new BdbQueueMap<Long, ByteBufferTest>("Z:\\temp", "abc");
//		for (int i = 0; i < 1; i++) {
//			try {
//				ByteBufferTest byteBufferTest = new ByteBufferTest();
//				byte[] bytes = "abc".getBytes();
//				byteBufferTest.setByteBuffer(bytes);
//				bdbQueue.put(byteBufferTest);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		
//		bdbQueue.remove(Long.valueOf(97), null);
		bdbQueue.close();
//
//		BdbQueueMap<Long, MessageFuture> bdbQueue2 = new BdbQueueMap<Long, MessageFuture>("Z:\\temp", "deliver1");
//		
//		System.out.println(bdbQueue2.size());
//		
//		bdbQueue2.close();
//		BdbQueueMap<Long, MessageFuture> bdbQueue = new BdbQueueMap<Long, MessageFuture>("z:\\temp", "deliver1");
//		out.println(bdbQueue.size());
//		for(int i = 0; i < 10; i++) {
//			try {
//				out.println(bdbQueue.take().getMessage());
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		
	}

}

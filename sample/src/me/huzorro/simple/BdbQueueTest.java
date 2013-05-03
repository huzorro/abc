package me.huzorro.simple;

import static java.lang.System.out;

public class BdbQueueTest {

	public BdbQueueTest() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BdbQueueMap<Long, String> bdbQueue = new BdbQueueMap<Long, String>("Z:\\", "m1");
//		for (int i = 0; i < 100; i++) {
//			try {
//				bdbQueue.put(i +  "Q");
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		
//		bdbQueue.remove(Long.valueOf(97), null);
//		bdbQueue.close();

		while(true) {
			try {
				out.println(bdbQueue.take());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}

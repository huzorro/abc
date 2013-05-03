package me.huzorro.simple;

import static java.lang.System.out;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {

	private ReentrantLock lock;
	private Condition condition;
	public LockTest() {
		lock = new ReentrantLock();
		condition = lock.newCondition();
	}
	
	public void take() {
		lock.lock();
		for(int i = 0; i < 100; i++) {
//			try {
//				Thread.sleep(60 * 1000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			out.println("take");
			try {
				condition.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		lock.unlock();
	}
	
	public void put() {
		lock.lock();
		out.println("put");
		condition.signal();
		lock.unlock();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final LockTest lockTest = new LockTest();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				lockTest.take();
			}
		}).start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				lockTest.put();
			}
		}).start();
	}

}

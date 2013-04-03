package me.huzorro.simple.thread;
import static java.lang.System.out;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public class ThreadLocalExample1 extends Thread{
    public static ThreadLocal<Boolean>  var; 
    private CountDownLatch latch;
    /**
     * 
     */
    public ThreadLocalExample1(ThreadLocal<Boolean> var, CountDownLatch latch) {
        // TODO Auto-generated constructor stub
        this.var = var;
        this.latch = latch;
    }

    /* (non-Javadoc)
     * @see java.lang.Thread#run()
     */
    @Override
    public void run() {
        // TODO Auto-generated method stub
        try {
            latch.await();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       if(var.get()) {
          var.set(false);
          out.println("==========");
       } else {
           var.set(true);
           out.println(var.get() + "-----------");
       }       
       out.println(var.get()+ "==========" + Thread.currentThread().getName());
    }
    public static void main(String[] args) {
        ThreadLocal<Boolean>  var = new ThreadLocal<Boolean>() {

            /* (non-Javadoc)
             * @see java.lang.ThreadLocal#initialValue()
             */
            @Override
            protected Boolean initialValue() {
                // TODO Auto-generated method stub
                return true;
            }
           
        }; 
        CountDownLatch latch = new CountDownLatch(1);
        out.println(var.get());
        Thread t[] = new Thread[20];
        for(int i = 0; i < 20; i++) {
            t[i] = new ThreadLocalExample1(var, latch);
        }
        for(Thread tt : t) {
            tt.start();
            out.println(ThreadLocalExample1.var.get() + "-----/------");
        }
        out.println("ready go.......");
        latch.countDown();
    }
    
}

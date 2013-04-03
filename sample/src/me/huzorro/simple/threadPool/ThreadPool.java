package me.huzorro.simple.threadPool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public class ThreadPool {

    /**
     * 
     */
    public ThreadPool() {
        // TODO Auto-generated constructor stub
    }
    public void threadRemove() {
        ExecutorService service = Executors.newCachedThreadPool();
        ScheduledThreadPoolExecutor s = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(1024);
        
        ScheduledFuture<?> fs = s.scheduleAtFixedRate(new Runnable() {
            
            @Override
            public void run()  {
                // TODO Auto-generated method stub
                int count = 0;
                System.out.println(count);
                count++;                    
            }
        }, 0, 1000, TimeUnit.MILLISECONDS);
        
        try {
            System.out.println(fs.get() + "!!!!");
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
//        Future<String> f = service.submit(new Callable<String>() {
//
//            @Override
//            public  String call() throws Exception {
//                // TODO Auto-generated method stub
//                while(true) {
//                    Thread.sleep(1000 * 10);
//                    System.out.println(1);
//                }
//            }
//        });
//        
//        Future<String> f1 = service.submit(new Callable<String>() {
//
//            @Override
//            public  String call() throws Exception {
//                // TODO Auto-generated method stub
//                while(true) {
//                    Thread.sleep(1000);
//                    System.out.println(2);
//                }
//            }
//        });        
        

        System.out.println("Thread cancel starting.....");
//        fs.cancel(true);
        System.out.println("Thread cancel end...........");
        
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new ThreadPool().threadRemove();
        
    }

}

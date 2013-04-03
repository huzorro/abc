package me.huzorro.simple.threadPool;

import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import me.huzorro.simple.enumt.WrapStructureDetail;


import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public class GuavaThreadPool {

    /**
     * 
     */
    public GuavaThreadPool() {
        // TODO Auto-generated constructor stub
    }
    public static void guavaThreadPoolTest() {
        final ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
        ListenableFuture<String> explosion = service.submit(new Callable<String>() {
          public String call() throws InterruptedException {
            ListenableFuture<String> fs = service.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    // TODO Auto-generated method stub
                    return "123";
                }
            });
              
            Futures.addCallback(fs,new FutureCallback<String>() {

                @Override
                public void onFailure(Throwable arg0) {
                    // TODO Auto-generated method stub
                    
                }

                @Override
                public void onSuccess(String arg0) {
                    // TODO Auto-generated method stub
                    System.out.println(arg0);
                }
            } , service);
            
            return "abc";              
          }
        });
        
        
        Futures.addCallback(explosion, new FutureCallback<String>() {
          // we want this handler to run immediately after we push the big red button!
          public void onSuccess(String explosion) {
              System.out.println(explosion);
          }
          public void onFailure(Throwable thrown) {
          }
        }, service);
       
    }
    
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
//        GuavaThreadPool.guavaThreadPoolTest();
        WrapStructureDetail.RequestMap map = WrapStructureDetail.RequestMap.SubmitRequestMap;
        System.out.println(map.getMap().hashCode());
        WrapStructureDetail.RequestMap map1 = WrapStructureDetail.RequestMap.SubmitRequestMap;
        System.out.println(map1.getMap().hashCode());
        
        System.out.println(map.getMap().equals(map.getMap())); 
    }

}

package me.huzorro.simple.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public class BlockQueue {

    /**
     * 
     */
    public BlockQueue() {
        // TODO Auto-generated constructor stub
    }
    public static void queuePeekTest() throws InterruptedException {
        BlockingQueue<String> queue = new LinkedBlockingQueue<String>();
        String s = "abc";
        for(int i = 0; i < 3; i++) {
            if(!queue.contains(s))
            queue.put(s);
        }
        while(true) {
            System.out.println(queue.take());
        }
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        try {
            BlockQueue.queuePeekTest();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}

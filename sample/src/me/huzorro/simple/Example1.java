package me.huzorro.simple;

import java.util.HashSet;
import java.util.LinkedList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public class Example1 {
	private final Logger logger = LoggerFactory.getLogger(Example1.class);
    /**
     * 
     * @param i
     * @return result
     */
    public int add(int ...i) {
        int result = 0;
        for(int j : i) {
           result += j; 
        }
        return result;
    }
    /**
     * 
     * @param i
     * @param j
     * @return result
     */
    public int div(int i, int j) {
        return  i / j;
    }
    /**
     * 
     * @param i
     * @param j
     * @return result
     */
    public int multiply(int i, int j) {
        return i * j;
    }
    public void t() {
        HashSet<Object> h = new HashSet<Object>();
        LinkedList<String> t = new  LinkedList<String>();
        for(Object s : h) {
            
        }
    }
    
    public void tt(final String s) {
    	new Thread(new Runnable() {
			
			@Override
			public void run() {
//				while(true) {
//					System.out.println(s);
//				}
			}
		}).start();
    	logger.error("abc {} ", 123, new InterruptedException("current thread"));
    }
    public static void main(String[] args) {
    	Example1 example1 = new Example1();
    	
    	example1.tt("abc");
    	example1.tt("123");
    }
}

package me.huzorro.simple;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public class Example1 {
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
    
}

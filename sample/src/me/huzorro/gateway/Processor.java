package me.huzorro.gateway;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public interface Processor extends Runnable {
    public void process() throws Exception;
}

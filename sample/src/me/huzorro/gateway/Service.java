package me.huzorro.gateway;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public interface Service extends Runnable {
    public void process() throws Exception;
}

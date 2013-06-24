package me.huzorro.gateway;

/**
 * 
 * @author huzorro(huzorro@gmail.com)
 *
 * @param <T>
 */
public interface TcpClient<T> {
    public T connect();
}

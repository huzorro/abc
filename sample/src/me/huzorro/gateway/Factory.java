package me.huzorro.gateway;

/**
 * 
 * @author huzorro(huzorro@gmail.com)
 *
 * @param <T>
 */
public interface Factory<T> {
    public T create() throws Exception;
}

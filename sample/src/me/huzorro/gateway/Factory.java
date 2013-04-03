package me.huzorro.gateway;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public interface Factory<T> {
    public T create() throws Exception;
}

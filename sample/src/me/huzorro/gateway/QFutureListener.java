package me.huzorro.gateway;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public interface QFutureListener<T> {
    public void onComplete(QFuture<T> future);
}

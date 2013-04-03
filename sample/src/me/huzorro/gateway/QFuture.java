package me.huzorro.gateway;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public interface QFuture {
    public void setSuccess();
    public boolean isSuccess();
    public void addListener(QFutureListener listener);
    public void removeListener(QFutureListener listenner);
}

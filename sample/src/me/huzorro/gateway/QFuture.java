package me.huzorro.gateway;

import java.io.Serializable;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public interface QFuture extends Serializable {
    public void setSuccess();
    public boolean isSuccess();
    public void addListener(QFutureListener listener);
    public void removeListener(QFutureListener listenner);
}

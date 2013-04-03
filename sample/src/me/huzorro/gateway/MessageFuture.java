package me.huzorro.gateway;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public class MessageFuture implements QFuture {
    private Message<?> message;
    private Queue<QFutureListener> listeners = new ConcurrentLinkedQueue<QFutureListener>();
    private AtomicBoolean done = new AtomicBoolean(false);
    /**
     * 
     */
    public MessageFuture(Message<?> message) {
        this.message = message;
    }
    
    /**
     * @return the message
     */
    public Message<?> getMessage() {
        return message;
    }
    /* (non-Javadoc)
     * @see me.huzorro.gateway.QFuture#setSuccess()
     */
    @Override
    public void setSuccess() {
        done.compareAndSet(false, true);
        for (QFutureListener listener : listeners) {
            listener.onComplete(this);
        }
    }

    /* (non-Javadoc)
     * @see me.huzorro.gateway.QFuture#isSuccess()
     */
    @Override
    public boolean isSuccess() {
        return done.get();
    }

    /* (non-Javadoc)
     * @see me.huzorro.gateway.QFuture#addListener(me.huzorro.gateway.QFutureListener)
     */
    @Override
    public void addListener(QFutureListener listener) {
        listeners.add(listener);
    }

    /* (non-Javadoc)
     * @see me.huzorro.gateway.QFuture#removeListener(me.huzorro.gateway.QFutureListener)
     */
    @Override
    public void removeListener(QFutureListener listenner) {
        listeners.remove(listenner);
    }

}

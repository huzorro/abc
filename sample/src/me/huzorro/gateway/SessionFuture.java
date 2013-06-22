package me.huzorro.gateway;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public class SessionFuture implements QFuture {
	private static final long serialVersionUID = 565093414478321808L;
	private Session session;
    private Queue<QFutureListener> listeners = new ConcurrentLinkedQueue<QFutureListener>();
    private AtomicBoolean done = new AtomicBoolean(false);
    /**
     * 
     */
    public SessionFuture(Session session) {
        this.session = session;
    }

    /**
     * @return the session
     */
    public Session getSession() {
        return session;
    }

    /* (non-Javadoc)
     * @see me.huzorro.gateway.QFuture#setSuccess()
     */
    @Override
    public void setSuccess() {
        done.compareAndSet(false, true);
        for(QFutureListener listener : listeners) {
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
    static class SessionCloseFuture extends SessionFuture {
		private static final long serialVersionUID = -2449672555157804491L;
		/**
         * @param session
         */
        public SessionCloseFuture(Session session) {
            super(session);
        }
        public boolean isClosed() {
            return super.isSuccess();
        }
        public void setClose() {
            super.setSuccess();
        }
    }
    static class SessionLoginFuture extends SessionFuture {
		private static final long serialVersionUID = -5648554101493670419L;
		/**
         * @param session
         */
        public SessionLoginFuture(Session session) {
            super(session);
        }
        public boolean isLogged() {
            return super.isSuccess();
        }
        public void setLogged() {
            super.setSuccess();
        }
    }
}

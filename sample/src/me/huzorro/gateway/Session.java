package me.huzorro.gateway;

import me.huzorro.gateway.SessionFuture.SessionCloseFuture;
import me.huzorro.gateway.SessionFuture.SessionLoginFuture;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public interface Session {
    public SessionConfig getConfig();
    public void setConfig(SessionConfig config);
    public void writeRequest(MessageFuture messageFuture) throws Exception;
    public void writeResponse(Message<?> message) throws Exception;
    public void writeRequestAndScheduleTask(MessageFuture messageFuture) throws Exception;
    public void writeResponseAndScheduleTask(Message<?> message) throws Exception;
    public void writeDeliver(Message<?> message) throws Exception;
    public void setAttachment(Object attachment);
    public Object getAttachment();
    public void close() throws Exception;
    public boolean isClosed();
    public boolean isWindowFull();
    public SessionCloseFuture getCloseFuture();
    public SessionLoginFuture getLoginFuture();
}

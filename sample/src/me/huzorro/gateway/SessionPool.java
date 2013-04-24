package me.huzorro.gateway;

import java.util.Map;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public interface SessionPool {
    public Session take(Object channelIds) throws Exception;
    public void put(Session session, boolean channelIdsAsKey) throws Exception;
    public Session checkout(Object channelIds) throws Exception;
    public void checkin(Session session, boolean channelIdsAsKey) throws Exception;
    public void remove(Session session);
    public void close() throws Exception;
    public void close(Object channelIds) throws Exception;
    public Map<Object, Integer> size();
    public int size(Object channelIds);

}

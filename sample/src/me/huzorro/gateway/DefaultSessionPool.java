package me.huzorro.gateway;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public class DefaultSessionPool implements SessionPool {
    private static final Logger logger = LoggerFactory.getLogger(DefaultSessionPool.class);
    private ConcurrentMap<Object, BlockingQueue<Session>> pool = new ConcurrentHashMap<Object, BlockingQueue<Session>>();
    private ConcurrentMap<Object, BlockingQueue<Session>> sessionGroup = new ConcurrentHashMap<Object, BlockingQueue<Session>>();
    
    public DefaultSessionPool() {        
    }
    
    @Override
    public Session take(Object channelIds) throws InterruptedException {
    	while(true) {
    		if(pool.get(channelIds) != null) return pool.get(channelIds).take();    				
    	}
    }
	@Override
	public Session checkout(Object channelIds) throws Exception {
		
		while(true) {
			if(pool.get(channelIds) != null) return pool.get(channelIds).poll();
		}

	}
	
	@Override
	public Session checkout(Object channelIds, long timeout, TimeUnit timeUnit)
			throws Exception {
		while(true) {
			if(pool.get(channelIds) != null) return pool.get(channelIds).poll(timeout, timeUnit);
		}
	}

    @Override
    public void put(final Session session, boolean channelIdsAsKey) throws InterruptedException {
        session.getCloseFuture().addListener(new QFutureListener<Session>() {
            @Override
            public void onComplete(QFuture<Session> future) {
                if(future.isSuccess()) remove(future.getMaster());
            }
        });
        Object asKey = channelIdsAsKey ? 
                session.getConfig().getChannelIds() : 
                    session.getConfig();
        BlockingQueue<Session> queueOfPool = pool.get(asKey);
        if(null != queueOfPool) {
            queueOfPool.put(session);
            sessionGroup.get(asKey).put(session);
        } else {
            queueOfPool = new LinkedBlockingQueue<Session>(session.getConfig().getMaxSessions());
            queueOfPool.put(session);
            pool.put(asKey, queueOfPool);
            BlockingQueue<Session> queueOfGroup = new LinkedBlockingQueue<Session>(session.getConfig().getMaxSessions());
            queueOfGroup.put(session);
            sessionGroup.put(asKey, queueOfGroup);
        }
    }
    
	@Override
	public void checkin(Session session, boolean channelIdsAsKey)
			throws Exception {
		if(null == session) return;
        Object asKey = channelIdsAsKey ? 
                session.getConfig().getChannelIds() : 
                    session.getConfig();
        BlockingQueue<Session> queueOfPool = pool.get(asKey);
        queueOfPool.put(session);
	}  
    @Override
    public void remove(Session session) {
        sessionGroup.get(session.getConfig().getChannelIds()).remove(session);
        pool.get(session.getConfig().getChannelIds()).remove(session);
    }

    @Override
    public void close() throws Exception {
        for(BlockingQueue<Session> queueOfPool : sessionGroup.values()) {
            for(Session session : queueOfPool) {
                session.close();
            }
        }
    }

    @Override
    public void close(Object channelIds) throws Exception {
        BlockingQueue<Session> queueOfGroup = sessionGroup.get(channelIds);
        for(Session session : queueOfGroup) {
            session.close();
        }
    }

    @Override
    public Map<Object, Integer> size() {
        Map<Object, Integer> sizeMap = new HashMap<Object, Integer>();
        for(Entry<Object, BlockingQueue<Session>> entry : sessionGroup.entrySet()) {
            sizeMap.put(entry.getKey(), entry.getValue().size());
            logger.debug("{} currentSize={}", entry.getKey().toString(), entry.getValue().size());
        }
        return sizeMap;
    }

    @Override
    public int size(Object channelIds) {
		return sessionGroup.get(channelIds) == null 
				? 0 
				:sessionGroup.get(channelIds).size();
    }

}

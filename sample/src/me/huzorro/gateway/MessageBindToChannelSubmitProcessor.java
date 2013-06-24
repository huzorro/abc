package me.huzorro.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author huzorro(huzorro@gmail.com)
 *
 */
public class MessageBindToChannelSubmitProcessor implements Processor {
    private static final Logger logger = LoggerFactory.getLogger(MessageBindToChannelSubmitProcessor.class);
    private BdbQueueMap<Long, QFuture<Message>> reserveQueue;
    private SessionPool sessionPool;
    private Session session;
    private QFuture<Message> messageFuture;
    /**
     * 
     * @param reserveQueue
     * @param sessionPool
     * @param session
     * @param messageFuture
     */
	public MessageBindToChannelSubmitProcessor(
			BdbQueueMap<Long, QFuture<Message>> reserveQueue, 
			SessionPool sessionPool, 
			Session session,
			QFuture<Message> messageFuture) {
		this.reserveQueue = reserveQueue;
		this.sessionPool = sessionPool;
		this.session = session;
		this.messageFuture = messageFuture;
	}

	@Override
	public void run() {
		try {
			process();
		} catch (Exception e) {
			logger.error("write or put the MessageFuture fails", e.getCause());
		}
	}

	@Override
	public void process() throws Exception {
		if(messageFuture.getMaster().isTerminationLife()) return;
		
        if(null != session && !session.isWindowFull())
        	session.deliverAndScheduleTask(messageFuture);
        else 
        	reserveQueue.put(messageFuture);
        sessionPool.checkin(session, true);		
	}
}

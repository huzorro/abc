package me.huzorro.gateway;

import java.util.concurrent.BlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public class DefaultRequestMessageSubmitProcessor implements Processor {
    private static final Logger logger = LoggerFactory.getLogger(DefaultRequestMessageSubmitProcessor.class);
    private BlockingQueue<MessageFuture> requestQueue;
    private Session session;
    /**
     * @return 
     * 
     */
    public void RequestSubmitProcessor(BlockingQueue<MessageFuture> requestQueue, Session session) {
        this.session = session;
        this.requestQueue = requestQueue;
    }

    /* (non-Javadoc)
     * @see me.huzorro.gateway.Processor#process()
     */
    @Override
    public void process() throws Exception {
        while(null != session && !session.isClosed()) {
            MessageFuture future = requestQueue.take();
            session.writeRequestAndScheduleTask(future);
        }
    }

    /* (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        try {
            process();
        } catch (Exception e) {
            logger.error("requestQueue take or session write for failed {}", e);
        }
    }

}

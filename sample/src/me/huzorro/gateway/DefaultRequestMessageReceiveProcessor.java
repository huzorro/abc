package me.huzorro.gateway;

import java.util.concurrent.BlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public class DefaultRequestMessageReceiveProcessor implements Processor {
    private static final Logger logger = LoggerFactory.getLogger(DefaultRequestMessageReceiveProcessor.class);
    private BlockingQueue<MessageFuture> requestQueue;
    /**
     * 
     */
    public DefaultRequestMessageReceiveProcessor(BlockingQueue<MessageFuture> requestQueue) {
        this.requestQueue = requestQueue;
    }

    /* (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        
    }

    /* (non-Javadoc)
     * @see me.huzorro.gateway.Processor#process()
     */
    @Override
    public void process() throws Exception {
        //处理数据读取服务
    }

}

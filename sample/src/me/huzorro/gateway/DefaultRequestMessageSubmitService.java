package me.huzorro.gateway;

import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public class DefaultRequestMessageSubmitService implements Service {
    private static final Logger logger = LoggerFactory.getLogger(DefaultRequestMessageSubmitService.class);
    private Map<String, SessionConfig>[] configMaps;
    private Map<Map<String, SessionConfig>, SessionPool> sessionPoolMap;
    private Map<SessionConfig, BlockingQueue<MessageFuture>> requestMsgQueueMap;
    private Map<SessionConfig, ExecutorService> executorServiceMap; 
    private Class<Processor> requestSubmitProcessor;
    /**
     * 
     */
    public DefaultRequestMessageSubmitService(            
            Map<Map<String, SessionConfig>, SessionPool> sessionPoolMap,
            Map<SessionConfig, BlockingQueue<MessageFuture>> requestMsgQueueMap,
            Map<SessionConfig, ExecutorService> executorServiceMap, 
            Class<Processor> requestSubmitProcessor,
            Map<String, SessionConfig>... configMaps) {
        this.configMaps = configMaps;
        this.sessionPoolMap = sessionPoolMap;
        this.requestMsgQueueMap = requestMsgQueueMap;
        this.executorServiceMap = executorServiceMap;
        this.requestSubmitProcessor = requestSubmitProcessor;
    }

    /* (non-Javadoc)
     * @see me.huzorro.gateway.Service#process()
     */
    @Override
    public void process() throws Exception {
        for(Map<String, SessionConfig> configMap : configMaps) {
            final SessionPool pool = sessionPoolMap.get(configMap);
            for(final SessionConfig config : configMap.values()) {
                executorServiceMap.get(config).execute(new Runnable() {
                    @Override
                    public void run() {
                        while(true) {
                            try {
                                Session session = pool.take(config);
                                Constructor<?>  constructor = requestSubmitProcessor.getConstructor(BlockingQueue.class, Session.class);
                                Processor processor = (Processor) constructor.newInstance(requestMsgQueueMap.get(config), session);
                                executorServiceMap.get(config).execute(processor);
                            } catch (Exception e) {
                                logger.error("take session failed {}", e);
                            }
                        }
                    }
                });
            }
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
            logger.error("submit the request processing failed {}", e);
        }
    }

}

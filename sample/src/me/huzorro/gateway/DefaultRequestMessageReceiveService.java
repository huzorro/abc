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
public class DefaultRequestMessageReceiveService implements Service {
    private static final Logger logger = LoggerFactory.getLogger(DefaultRequestMessageReceiveService.class);
    private Map<String, SessionConfig>[] configMaps;
    private Map<SessionConfig, BlockingQueue<MessageFuture>> requestMsgQueueMap;
    private Map<SessionConfig, ExecutorService> executorServiceMap; 
    private Class<Processor> requestReceiveProcessor;
    /**
     * 
     */
    public DefaultRequestMessageReceiveService(
            Map<SessionConfig, BlockingQueue<MessageFuture>> requestMsgQueueMap,
            Map<SessionConfig, ExecutorService> executorServiceMap,
            Class<Processor> requestReceiveProcessor
            ) {
        this.requestMsgQueueMap = requestMsgQueueMap;
        this.executorServiceMap = executorServiceMap;
        this.requestReceiveProcessor = requestReceiveProcessor;
    }

    /* (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see me.huzorro.gateway.Service#process()
     */
    @Override
    public void process() throws Exception {
        for(Map<String, SessionConfig> configMap : configMaps) {
            for(final SessionConfig config : configMap.values()) {
                for(int i = 0; i < config.getMaxSessions(); i++){
                    executorServiceMap.get(config).execute(new Runnable() {
                        @Override
                        public void run() {
                            while(true) {
                                try {
                                    Constructor<?>  constructor = requestReceiveProcessor.getConstructor(BlockingQueue.class);
                                    Processor processor = (Processor) constructor.newInstance(requestMsgQueueMap.get(config));
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
    }

}

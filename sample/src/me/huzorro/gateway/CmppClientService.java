package me.huzorro.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public class CmppClientService implements Service {
    private final Logger logger = LoggerFactory.getLogger(CmppClientService.class);
    /**
     * 
     */
    public CmppClientService() {
        
        Runtime.getRuntime().addShutdownHook(new ServiceShutdownHook());        
    }

    /* (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        try {
            process();
        } catch (Exception e) {
            logger.error("CmppClientService start failed {}", e);
            Runtime.getRuntime().exit(1);
        }
    }

    /* (non-Javadoc)
     * @see me.huzorro.gateway.Service#process()
     */
    @Override
    public void process() throws Exception {
        
    }
    /**
     * 
     * @param args
     */
    public static void main(String[] args) {
            new Thread(new CmppClientService()).start();
    }
}

package me.huzorro.gateway;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.configuration.ConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public class CmppClientService implements Service {
    private final Logger logger = LoggerFactory.getLogger(CmppClientService.class);
    private Collection<Service> services = new ArrayList<Service>();            
    /**
     * 
     */
    public CmppClientService() {
        globalVarsInit().clientServiceInit().messageServiceInit().managerServiceInit();
        Runtime.getRuntime().addShutdownHook(new Thread(new ServiceShutdownHook()));        
    }

    protected CmppClientService globalVarsInit() {
        try {
            new CmppGlobalVarsInitialize().
            sessionConfigInitialize().
            sessionPoolInitialize().
            messageQueueInitialize().
            threadPoolInitialize().
            clientBootstrapInitialize().
            serverBootstrapInitialize();
        } catch (ConfigurationException e) {
            logger.error("GlobalVars Initialize failed {}", e);
            Runtime.getRuntime().exit(-1);
        } catch (InstantiationException e) {
            logger.error("GlobalVars Initialize failed {}", e);
            Runtime.getRuntime().exit(-1);
        } catch (IllegalAccessException e) {
            logger.error("GlobalVars Initialize failed {}", e);
            Runtime.getRuntime().exit(-1);
        }
        return this;
    }
    protected CmppClientService clientServiceInit() {
        Service upstream = new CmppUpstreamClientService(
                CmppGlobalVars.upstreamSessionConfigMap, 
                CmppGlobalVars.clientBootstrapMap, 
                CmppGlobalVars.requestMsgQueueMap, 
                CmppGlobalVars.responseMsgQueueMap, 
                CmppGlobalVars.deliverMsgQueueMap, 
                CmppGlobalVars.messageQueueMap, 
                CmppGlobalVars.scheduleExecutorMap, 
                CmppGlobalVars.sessionPoolMap);
        services.add(upstream);
        return this;
    }
    protected CmppClientService messageServiceInit() {
        return this;
    }
    protected CmppClientService managerServiceInit() {
        return this;
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
            Runtime.getRuntime().exit(-1);
        }
    }

    /* (non-Javadoc)
     * @see me.huzorro.gateway.Service#process()
     */
    @Override
    public void process() throws Exception {
        for(Service service : services) {
            service.process();
        }
    }
    /**
     * 
     * @param args
     */
    public static void main(String[] args) {
            new Thread(new CmppClientService()).start();
            try {
                Thread.sleep(5000L);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            new Thread(new Runnable() {
                
                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    while(true) {
                        int s = GlobalVars.sessionPoolMap.get(GlobalVars.upstreamSessionConfigMap).size("901077");
                        System.out.println(s);
                    }
                }
            }).start();
    }
}

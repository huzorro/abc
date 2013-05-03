/**
 * 
 */
package me.huzorro.gateway;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.configuration.ConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author huzorro
 *
 */
public class CmppServerService implements Service {
    private final Logger logger = LoggerFactory.getLogger(CmppClientService.class);
    private Collection<Service> services = new ArrayList<Service>();  
	/**
	 * 
	 */
	public CmppServerService() {
        globalVarsInit().clientServiceInit().messageServiceInit().managerServiceInit();
	}

    protected CmppServerService globalVarsInit() {
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
    protected CmppServerService clientServiceInit() {

        Service upstream = new CmppUpstreamServerService(
                CmppGlobalVars.upstreamSessionConfigMap, 
                CmppGlobalVars.serverBootstrapMap, 
                CmppGlobalVars.requestMsgQueueMap, 
                CmppGlobalVars.responseMsgQueueMap, 
                CmppGlobalVars.deliverMsgQueueMap, 
                CmppGlobalVars.scheduleExecutorMap, 
                CmppGlobalVars.sessionPoolMap);
        
        services.add(upstream);
        return this;
    }
    protected CmppServerService messageServiceInit() {
        return this;
    }
    protected CmppServerService managerServiceInit() {
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
    public static void main(String[] args) {
    	new Thread(new CmppServerService()).start();
    }
}

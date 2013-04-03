package me.huzorro.gateway;

import java.util.List;
import java.util.Map;

import org.apache.commons.configuration.CombinedConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.DefaultConfigurationBuilder;
import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.configuration.event.ConfigurationEvent;
import org.apache.commons.configuration.event.ConfigurationListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public class SessionConfigMapFactory<T extends Map<String, SessionConfig>, E extends SessionConfig> implements Factory<T> {
    private final Logger logger = LoggerFactory.getLogger(SessionConfigMapFactory.class);
    private DefaultConfigurationBuilder configBuilder;
    private Map<String, SessionConfig> sessionConfigMap;
    private String sessionType;
    private Class<E> sessionConfig;

    public SessionConfigMapFactory(
            DefaultConfigurationBuilder configurationBuilder,
            Map<String, SessionConfig> sessionConfigMap,
            String sessionType,
            Class<E> sessionConfig) {
        this.configBuilder = configurationBuilder;
        this.sessionConfigMap = sessionConfigMap;
        this.sessionType = sessionType;
        this.sessionConfig = sessionConfig;
    }
    

    /* (non-Javadoc)
     * @see me.huzorro.gateway.Factory#create()
     */
    @Override
    @SuppressWarnings("unchecked")
    public T create() throws ConfigurationException, InstantiationException, IllegalAccessException {
        CombinedConfiguration cc = null;        
        cc = configBuilder.getConfiguration(true);
        XMLConfiguration xmlConfig = (XMLConfiguration) cc.getConfiguration("session");
        xmlConfig.addConfigurationListener(new ConfigurationListener() {
            @Override
            public void configurationChanged(ConfigurationEvent event) {
                if(!event.isBeforeUpdate()) {                   
                    logger.info("{}", "session config changed");
                }
            }
        });
        HierarchicalConfiguration sub = xmlConfig.configurationAt("sessions");
        List<HierarchicalConfiguration>  subList= sub.configurationsAt("session");
        for(int i = 0; i < subList.size(); i++) {
            E config = sessionConfig.newInstance();
            config.setAttPreffix(String.format("sessions.session(%1$d).%2$s", i, sessionType));
            config.setConfiguration(xmlConfig);
            config.setChannelIds(subList.get(i).getString("id"));
            sessionConfigMap.put(config.getChannelIds(), config);
            logger.debug("{}", config);
        }
        return (T) sessionConfigMap;
    }
}

package me.huzorro.gateway;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public interface GlobalVarsInitialize {

    
    public GlobalVarsInitialize sessionConfigInitialize() throws Exception;
    
    public GlobalVarsInitialize sessionPoolInitialize(); 
    
    public GlobalVarsInitialize messageQueueInitialize(); 
    
    public GlobalVarsInitialize threadPoolInitialize(); 
    
    public GlobalVarsInitialize clientBootstrapInitialize();
   
    public GlobalVarsInitialize serverBootstrapInitialize();
}
/**
 * 
 */
package me.huzorro.gateway.telnet;

import java.util.List;

import me.huzorro.gateway.GlobalVars;
import me.huzorro.gateway.GlobalVarsInitialize;
import me.huzorro.gateway.ServerServices;
import me.huzorro.gateway.Service;

/**
 * @author huzorro(huzorro@gmail.com)
 *
 */
public class TelnetServerService implements ServerServices {
    private String configName;
    private static final GlobalVarsInitialize globalVarsInitialize = new TelnetGlobalVarsInitialize();

	public TelnetServerService() {
		this("telnetsession");
	}

	/**
	 * @param configName
	 */
	public TelnetServerService(String configName) {
		this.configName = configName;
	}
	
	@Override
	public ServerServices duplexstreamGlobalVarsInit() throws Exception {
		return duplexstreamGlobalVarsInit(null);
	}
	
	@Override
	public ServerServices duplexstreamGlobalVarsInit(List<String> configList) throws Exception {
		globalVarsInitialize
		.duplexstreamSessionConfigInitialize(configList)
		.duplexstreamSessionPoolInitialize(configList)
		.duplexstreamThreadPoolInitialize(configList)
		.duplexstreamServerBootstrapInitialize(configList);
		return this;
	}
	
	@Override
	public Service duplexstreamServiceInit(List<String> configList) {
		return new TelnetDuplexstreamServerService(
				GlobalVars.duplexSessionConfigMap.get(configName),
				GlobalVars.serverBootstrapMap, 
				GlobalVars.receiveMsgQueueMap, 
				GlobalVars.responseMsgQueueMap,
				GlobalVars.deliverMsgQueueMap, 
				GlobalVars.scheduleExecutorMap, 
				GlobalVars.sessionPoolMap);
	}	
	
	@Override
	public ServerServices upstreamGlobalVarsInit() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServerServices upstreamGlobalVarsInit(List<String> configList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServerServices downstreamGlobalVarsInit() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServerServices downstreamGlobalVarsInit(List<String> configList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Service upstreamServiceInit() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Service upstreamServiceInit(List<String> configList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Service downstreamServiceInit() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Service downstreamServiceInit(List<String> configList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Service duplexstreamServiceInit() {
		return duplexstreamServiceInit(null);
	}


	@Override
	public Service duplexstreamResponseMsgPluginManagerServiceInit() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Service duplexstreamResponseMsgPluginManagerServiceInit(
			List<String> configList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Service upstreamDeliverMsgPluginManagerServiceInit() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Service upstreamDeliverMsgPluginManagerServiceInit(
			List<String> configList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Service duplexstreamDeliverMsgPluginManagerServiceInit() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Service duplexstreamDeliverMsgPluginManagerServiceInit(
			List<String> configList) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void process() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Service downstreamReceiveMsgPluginManagerServiceInit() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Service downstreamReceiveMsgPluginManagerServiceInit(
			List<String> configList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Service duplexstreamReceiveMsgPluginManagerServiceInit() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Service duplexstreamReceiveMsgPluginManagerServiceInit(
			List<String> confiList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Service upstreamResponseMsgPluginManagerServiceInit() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Service upstreamResponseMsgPluginManagerServiceInit(
			List<String> configList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Service upstreamDeliverServiceInit() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Service upstreamDeliverServiceInit(List<String> configList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Service upstreamReserveDeliverServiceInit() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Service upstreamReserveDeliverServiceInit(List<String> configList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Service duplexstreamDeliverServiceInit() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Service duplexstreamDeliverServiceInit(List<String> configList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Service duplexstreamReserveDeliverServiceInit() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Service duplexstreamReserveDeliverServiceInit(List<String> configList) {
		// TODO Auto-generated method stub
		return null;
	}


}

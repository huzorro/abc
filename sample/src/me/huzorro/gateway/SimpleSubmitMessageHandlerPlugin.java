/**
 * 
 */
package me.huzorro.gateway;

import net.xeoh.plugins.base.annotations.PluginImplementation;
import net.xeoh.plugins.base.annotations.injections.InjectPlugin;
import net.xeoh.plugins.base.annotations.meta.Author;
import net.xeoh.plugins.base.annotations.meta.Version;

/**
 * @author huzorro(huzorro@gmail.com)
 *
 */
@Author(name = "huzorro(huzorro@gmail.com)")
@Version(version = 10000)
@PluginImplementation
public class SimpleSubmitMessageHandlerPlugin implements
		SubmitMessageHandlerPlugin {

	@InjectPlugin
	public SubmitMessageServicePlugin submitMessageServicePlugin;
	/* (non-Javadoc)
	 * @see me.huzorro.gateway.SubmitMessageHandlerPlugin#submit()
	 */
	@Override
	public void submit() throws Exception {
		for (int i = 0; i < 1; i++) {
				CmppSubmitRequestMessage cmppSubmitRequestMessage = new CmppSubmitRequestMessage();
				cmppSubmitRequestMessage.setChannelIds("901077");
				cmppSubmitRequestMessage.setMsgContent("中文");
				cmppSubmitRequestMessage.setRegisteredDelivery((short)0);
				submitMessageServicePlugin.submit(cmppSubmitRequestMessage);
		}
		for(int i = 0; i < 1; i++) {
			CmppDeliverRequestMessage msg = new CmppDeliverRequestMessage();
			msg.setChannelIds("901077");
			msg.setMsgContent("哈哈Ok");
			msg.setDestId("10669501");
			msg.setSrcterminalId("13800138000");
			msg.setServiceid("abc");
			msg.setRegisteredDelivery((byte)0x1);
			msg.setReport(true);
			CmppReportRequestMessage report = msg.getReportRequestMessage();
			report.setStat("DELIVERED");
			report.setSubmitTime("131029180843");
			report.setDoneTime("1310291808");
			report.setSmscSequence(123);
			msg.setReportRequestMessage(report);
			submitMessageServicePlugin.submit(msg);
		}
	}

}

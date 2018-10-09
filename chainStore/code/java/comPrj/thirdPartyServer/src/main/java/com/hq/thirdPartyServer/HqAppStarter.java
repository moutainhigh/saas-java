package com.hq.thirdPartyServer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import com.hq.thirdPartyServer.common.config.ThirdPartyServerCfg;
import com.hq.thirdPartyServer.common.config.ThirdPartyServerCfgMgr;
import com.hq.thirdPartyServer.common.log.LogModule;
import com.hq.thirdPartyServer.common.log.MainLog;
import com.hq.thirdPartyServer.rabbitmq.LoggerSenderMgr;

@RefreshScope
@Service
public class HqAppStarter implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	// 注入接口服务
	private ThirdPartyServerCfg smsMSCfg;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		MainLog.info(LogModule.StartInfo, "StartInfo[init]", "config used:" + smsMSCfg.getEnvMark());
		ThirdPartyServerCfgMgr.setCfg(smsMSCfg);
		
		MainLog.info(LogModule.StartInfo, "LoggerSenderMgr[init]", "LoggerSenderMgr.getInstance().init(smsMSCfg)");
		LoggerSenderMgr.getInstance().init(smsMSCfg);
	}

}

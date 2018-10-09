package com.hq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import com.hq.common.config.StoreFileMSCfg;
import com.hq.common.config.StoreFileMSCfgMgr;
import com.hq.common.log.LogModule;
import com.hq.common.log.MainLog;
import com.hq.rabbitmq.LoggerSenderMgr;
@RefreshScope
@Service
public class HqFileAppStarter  implements ApplicationListener<ContextRefreshedEvent>{
	
	@Autowired // 注入接口服务  
	private StoreFileMSCfg storeFileMSCfg; 
	 
	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		MainLog.info(LogModule.StartInfo, "StartInfo[init]", "config used:"+storeFileMSCfg.getEnvMark());
		StoreFileMSCfgMgr.setCfg(storeFileMSCfg);
		
		MainLog.info(LogModule.StartInfo, "LoggerSenderMgr[init]", "LoggerSenderMgr.init(rabbitProperties,storeFileMSCfg)");
		LoggerSenderMgr.getInstance().init(storeFileMSCfg);
	}
}

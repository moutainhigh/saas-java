package com.hq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import com.hq.common.config.StoreLogMSCfg;
import com.hq.common.config.StoreLogMSCfgMgr;
import com.hq.common.log.LogModule;
import com.hq.common.log.MainLog;
import com.hq.storeLog.logHandle.LogAsynHandler;
import com.hq.storeLog.logHandle.StoreLogRabbitReceiver4TextMgr;

@RefreshScope
@Service
public class HqAppStarter  implements ApplicationListener<ContextRefreshedEvent>{
	
	@Autowired // 注入接口服务  
	private StoreLogMSCfg storeMSCfg;  
	 
	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		MainLog.info(LogModule.StartInfo, "StartInfo[init]", "config used:"+storeMSCfg.getEnvMark());
		StoreLogMSCfgMgr.setCfg(storeMSCfg);
		
		MainLog.info(LogModule.StartInfo, "StartInfo[init]", "StoreLogRabbitReceiver4TextMgr.getInstance().init()");
		StoreLogRabbitReceiver4TextMgr.getInstance().init();
		
		MainLog.info(LogModule.StartInfo, "StartInfo[init]", "LogAsynHandler.getInstance().init()");
		LogAsynHandler.getInstance().init();
	}
}

package com.hq.taskMS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import com.hq.taskMS.common.config.StoreTaskMSCfg;
import com.hq.taskMS.common.config.StoreTaskMSCfgMgr;
import com.hq.taskMS.common.log.LogModule;
import com.hq.taskMS.common.log.MainLog;
import com.hq.taskMS.storeEvent.OrderTmpRcdMgr;
import com.hq.taskMS.storeEvent.ScheduledLeaguerNoticeMgr;
import com.hq.taskMS.storeEvent.StoreEventSenderMgr;
import com.hq.taskMS.storeTask.StoreTaskRabbitReceiver4TextMgr;
@RefreshScope
@Service
public class TaskAppStarter  implements ApplicationListener<ContextRefreshedEvent>{
	
	@Autowired // 注入接口服务  
	private StoreTaskMSCfg storeMSCfg;  
	 
	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		MainLog.info(LogModule.StartInfo, "StartInfo[init]", "config used:"+storeMSCfg.getEnvMark());
		StoreTaskMSCfgMgr.setCfg(storeMSCfg);
		
		MainLog.info(LogModule.StartInfo, "StartInfo[init]", "StoreTaskRabbitReceiver4TextMgr.getInstance().init()");
		StoreTaskRabbitReceiver4TextMgr.getInstance().init();
		
		MainLog.info(LogModule.StartInfo, "StartInfo[init]", "ScheduledLeaguerNoticeMgr.getInstance().init()");
		ScheduledLeaguerNoticeMgr.getInstance().init();
		
		MainLog.info(LogModule.StartInfo, "StartInfo[init]", "StoreEventSenderMgr.getInstance().init()");
		StoreEventSenderMgr.getInstance().init();
		
		MainLog.info(LogModule.StartInfo, "StartInfo[init]", "OrderTmpRcdMgr.getInstance().init()");
		OrderTmpRcdMgr.getInstance().init();
	}
}

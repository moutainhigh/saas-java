package com.hq.storeManagerMS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.hq.storeClient.StoreClientMgr;
import com.hq.storeFileClient.service.StoreFileClientMgr;
import com.hq.storeManagerMS.common.config.StoreMngMSCfg;
import com.hq.storeManagerMS.common.config.StoreMngMSCfgMgr;
import com.hq.storeManagerMS.common.constants.ServerConstants;
import com.hq.storeManagerMS.common.log.LogModule;
import com.hq.storeManagerMS.common.log.MainLog;
import com.hq.storeManagerMS.common.tableIndex.MsTableIndexMgr;
import com.hq.storeManagerMS.rabbitmq.LoggerSenderMgr;
import com.hq.storeManagerMS.service.sysDataInit.bs.SysDataInitMgr;
import com.hq.storeManagerMS.zenmind.dao.rest.restSTImpl.RestProxySTImpl;
import com.hq.storeManagerMS.zenmind.dao.rest.restSTImpl.RestTemplateMgr;
import com.hq.thirdPartyClient.service.ThirdPartyClientMgr;
import com.zenmind.dao.RestDaoMgr;
import com.zenmind.dao.mongodb.MongodbTemplateMgr;
import com.zenmind.dao.redis.RedisTemplateMgr;

@RefreshScope
@Service
public class HqAppStarter  implements ApplicationListener<ContextRefreshedEvent>{
	
	@Autowired  
	private StoreMngMSCfg storeMngMSCfg;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	 
	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		MainLog.info(LogModule.StartInfo, "StartInfo[init]", "config used:"+storeMngMSCfg.getEnvMark());
		StoreMngMSCfgMgr.setCfg(storeMngMSCfg);
		
		MainLog.info(LogModule.StartInfo, "StartInfo[init]", "MongodbTemplateMgr.getInstance().init(mongodbCfg)");
		MongodbTemplateMgr.getInstance().init(storeMngMSCfg.getMongodbCfg());
		
		MainLog.info(LogModule.StartInfo, "StartInfo[init]", "MongodbTemplateMgr.getInstance().ensureIndex(MsTableIndexMgr.getInstance().getClassInfos())");
		MongodbTemplateMgr.getInstance().ensureIndex(MsTableIndexMgr.getInstance().getClassInfos());
		
		MainLog.info(LogModule.StartInfo, "RestTemplateMgr[init]", "RestTemplateMgr.init()");
		RestTemplateMgr.getInstance().init();

		MainLog.info(LogModule.StartInfo, "RestDaoMgr[init]", "RestDaoMgr.init()");
		RestDaoMgr.init(RestProxySTImpl.getInstance());
		
		if(storeMngMSCfg.isRedisEnabled()){
			MainLog.info(LogModule.StartInfo, "RedisTemplateMgr[init]", "RedisTemplateMgr.init(stringRedisTemplateP, timeOutInSecondsP)");
			RedisTemplateMgr.getInstance().init(stringRedisTemplate, storeMngMSCfg.getRedisConfig());
		}
		
		//智美通组件初始化
		MainLog.info(LogModule.StartInfo, "StoreClientMgr[init]", "StoreClientMgr.init()");
		StoreClientMgr.init(storeMngMSCfg.getStoreMSHost(), ServerConstants.APP_NAME);
		//文件服务组件初始化
		MainLog.info(LogModule.StartInfo, "StoreFileClientMgr[init]", "StoreFileClientMgr.init()");
		StoreFileClientMgr.init(storeMngMSCfg.getFileMSHost());
		//第三方服务组件初始化
		MainLog.info(LogModule.StartInfo, "StoreFileClientMgr[init]", "StoreFileClientMgr.init()");
		ThirdPartyClientMgr.init(storeMngMSCfg.getThirdPartyHost());
		
		MainLog.info(LogModule.StartInfo, "LoggerSenderMgr[init]", "LoggerSenderMgr.init(rabbitProperties,storeMngMSCfg)");
		LoggerSenderMgr.getInstance().init(storeMngMSCfg);
		
		//系统数据初始化
		MainLog.info(LogModule.StartInfo, "SysDataInitMgr[init]", "SysDataInitMgr.init()");
		SysDataInitMgr.getInstance().init();
	}
}

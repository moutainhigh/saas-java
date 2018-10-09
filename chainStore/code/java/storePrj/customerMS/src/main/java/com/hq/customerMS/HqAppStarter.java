package com.hq.customerMS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.hq.customerMS.common.config.CustomerMSCfg;
import com.hq.customerMS.common.config.CustomerMSCfgMgr;
import com.hq.customerMS.common.constants.ServerConstants;
import com.hq.customerMS.common.log.LogModule;
import com.hq.customerMS.common.log.MainLog;
import com.hq.customerMS.common.tableIndex.MsTableIndexMgr;
import com.hq.customerMS.rabbitmq.LoggerSenderMgr;
import com.hq.customerMS.service.splitData.bs.SplitDataMgr;
import com.hq.customerMS.service.sysDataInit.bs.SysDataInitMgr;
import com.hq.customerMS.zenmind.dao.rest.restSTImpl.RestProxySTImpl;
import com.hq.customerMS.zenmind.dao.rest.restSTImpl.RestTemplateMgr;
import com.hq.storeClient.StoreClientMgr;
import com.hq.storeFileClient.service.StoreFileClientMgr;
import com.hq.thirdPartyClient.service.ThirdPartyClientMgr;
import com.zenmind.dao.RestDaoMgr;
import com.zenmind.dao.mongodb.MongodbTemplateMgr;
import com.zenmind.dao.redis.RedisTemplateMgr;

@RefreshScope
@Service
public class HqAppStarter implements ApplicationListener<ContextRefreshedEvent> {

	// 注入接口服务
	@Autowired
	private CustomerMSCfg customMSCfg;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		MainLog.info(LogModule.StartInfo, "StartInfo[init]", "config used:" + customMSCfg.getEnvMark());
		CustomerMSCfgMgr.setCfg(customMSCfg);
		
		MainLog.info(LogModule.StartInfo, "StartInfo[init]", "MongodbTemplateMgr.getInstance().init(mongodbCfg)");
		MongodbTemplateMgr.getInstance().init(customMSCfg.getMongodbCfg());
		
		MainLog.info(LogModule.StartInfo, "StartInfo[init]", "MongodbTemplateMgr.getInstance().ensureIndex(MsTableIndexMgr.getInstance().getClassInfos())");
		MongodbTemplateMgr.getInstance().ensureIndex(MsTableIndexMgr.getInstance().getClassInfos());

		MainLog.info(LogModule.StartInfo, "RestTemplateMgr[init]", "RestTemplateMgr.init()");
		RestTemplateMgr.getInstance().init();

		MainLog.info(LogModule.StartInfo, "RestDaoMgr[init]", "RestDaoMgr.init()");
		RestDaoMgr.init(RestProxySTImpl.getInstance());
		
		if(customMSCfg.isRedisEnabled()){
			MainLog.info(LogModule.StartInfo, "RedisTemplateMgr[init]", "RedisTemplateMgr.getInstance().init(stringRedisTemplateP,timeOutInSecondsP)");
			RedisTemplateMgr.getInstance().init(stringRedisTemplate, customMSCfg.getRedisConfig());
		}
		
		MainLog.info(LogModule.StartInfo, "LoggerSenderMgr[init]", "LoggerSenderMgr.getInstance().init(customMSCfg)");
		LoggerSenderMgr.getInstance().init(customMSCfg);
		
		// storeFileClient组件初始化
		MainLog.info(LogModule.StartInfo, "StoreFileClientMgr[init]", "StoreFileClientMgr.init(storeFileServer)");
		StoreFileClientMgr.init(customMSCfg.getFileHost());
		// ThirdPartyClient组件初始化
		MainLog.info(LogModule.StartInfo, "ThirdPartyClientMgr[init]", "ThirdPartyClientMgr.init(thirdPartyServerURL)");
		ThirdPartyClientMgr.init(customMSCfg.getThirdPartyHost());
		//StoreClient组件初始化
		MainLog.info(LogModule.StartInfo, "StoreClientMgr[init]", "StoreClientMgr.init()");
		StoreClientMgr.init(customMSCfg.getStoreMSHost(), ServerConstants.APP_NAME);
		
		//系统数据初始化
		MainLog.info(LogModule.StartInfo, "SysDataInitMgr[init]", "SysDataInitMgr.init()");
		SysDataInitMgr.getInstance().init();
		//系统数据拆分
		MainLog.info(LogModule.StartInfo, "SplitDataMgr[init]", "SplitDataMgr.init()");
		SplitDataMgr.getInstance().init();
	}

}

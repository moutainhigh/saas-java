package com.hq.chainMS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.hq.chainMS.common.config.ChainMSCfg;
import com.hq.chainMS.common.config.ChainMSCfgMgr;
import com.hq.chainMS.common.constants.ServerConstants;
import com.hq.chainMS.common.log.LogModule;
import com.hq.chainMS.common.log.MainLog;
import com.hq.chainMS.common.tableIndex.MsTableIndexMgr;
import com.hq.chainMS.rabbitmq.LoggerSenderMgr;
import com.hq.chainMS.service.monitor.bs.ChainMonitorMgr;
import com.hq.chainMS.service.sysDataInit.bs.SysDataInitMgr;
import com.hq.chainMS.zenmind.dao.interceptor.LogInterceptorMgr;
import com.hq.chainMS.zenmind.dao.rest.restSTImpl.RestProxySTImpl;
import com.hq.chainMS.zenmind.dao.rest.restSTImpl.RestTemplateMgr;
import com.hq.orderRestClient.OrderRestClientMgr;
import com.hq.storeClient.StoreClientMgr;
import com.hq.storeFileClient.service.StoreFileClientMgr;
import com.hq.thirdPartyClient.service.ThirdPartyClientMgr;
import com.zenmind.dao.RestDaoMgr;
import com.zenmind.dao.mongodb.MongodbTemplateMgr;
import com.zenmind.dao.redis.RedisTemplateMgr;

@RefreshScope
@Service
public class HqAppStarter implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private ChainMSCfg chainMSCfg;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		MainLog.info(LogModule.StartInfo, "StartInfo[init]", "config used:" + chainMSCfg.getEnvMark());
		ChainMSCfgMgr.setCfg(chainMSCfg);

		MainLog.info(LogModule.StartInfo, "StartInfo[init]", "MongodbTemplateMgr.getInstance().init(mongodbCfg)");
		MongodbTemplateMgr.getInstance().init(chainMSCfg.getMongodbCfg());

		MainLog.info(LogModule.StartInfo, "StartInfo[init]", "MongodbTemplateMgr.getInstance().ensureIndex()");
		MongodbTemplateMgr.getInstance().ensureIndex(MsTableIndexMgr.getInstance().getClassInfos());

		MainLog.info(LogModule.StartInfo, "StartInfo[init]", "RestTemplateMgr.init()");
		RestTemplateMgr.getInstance().init();

		MainLog.info(LogModule.StartInfo, "RestDaoMgr[init]", "RestDaoMgr.init()");
		RestDaoMgr.init(RestProxySTImpl.getInstance());

		if (chainMSCfg.isRedisEnabled()) {
			MainLog.info(LogModule.StartInfo, "RedisTemplateMgr[init]", "RedisTemplateMgr.getInstance().init()");
			RedisTemplateMgr.getInstance().init(stringRedisTemplate, chainMSCfg.getRedisConfig());
		}

		MainLog.info(LogModule.StartInfo, "LoggerSenderMgr[init]", "LoggerSenderMgr.getInstance().init(chainMSCfg)");
		LoggerSenderMgr.getInstance().init(chainMSCfg);

		// DAO日志拦截
		MainLog.info(LogModule.StartInfo, "LogInterceptorMgr[init]", "LogInterceptorMgr.getInstance().init()");
		LogInterceptorMgr.getInstance().init();

		// 系统默认的性能监控开关
		MainLog.info(LogModule.StartInfo, "StoreMsMonitorMgr[init]", "StoreMsMonitorMgr.getInstance().init(boolean)");
		ChainMonitorMgr.getInstance().init(chainMSCfg.isMonitorEnabled());

		// storeFileClient组件初始化
		MainLog.info(LogModule.StartInfo, "StoreFileClientMgr[init]", "StoreFileClientMgr.init(storeFileServer)");
		StoreFileClientMgr.init(chainMSCfg.getFileHost());
		// ThirdPartyClient组件初始化
		MainLog.info(LogModule.StartInfo, "ThirdPartyClientMgr[init]", "ThirdPartyClientMgr.init(thirdPartyServerURL)");
		ThirdPartyClientMgr.init(chainMSCfg.getThirdPartyHost());
		// OrderRestClient组件初始化
		MainLog.info(LogModule.StartInfo, "OrderClientMgr[init]", "OrderClientMgr.init(orderServerUrl)");
		OrderRestClientMgr.init(chainMSCfg.getOrderHost(), ServerConstants.appName);
		// StoreClient组件初始化
		MainLog.info(LogModule.StartInfo, "StoreClientMgr[init]", "StoreClientMgr.init()");
		StoreClientMgr.init(chainMSCfg.getStoreHost(), ServerConstants.appName);
		
		//初始化系统数据信息
		MainLog.info(LogModule.StartInfo, "SysDataInitMgr[init]", "SysDataInitMgr.init()");
		SysDataInitMgr.getInstance().init();
	}
}

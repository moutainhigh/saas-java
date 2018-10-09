package com.hq.payms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.hq.payms.common.config.PayMSCfg;
import com.hq.payms.common.config.PayMSCfgMgr;
import com.hq.payms.common.log.LogModule;
import com.hq.payms.common.log.MainLog;
import com.hq.payms.common.starter.AlipayStarter;
import com.hq.payms.common.starter.WxpayStarter;
import com.hq.payms.common.tableIndex.MsTableIndexMgr;
import com.hq.payms.zenmind.dao.interceptor.LogInterceptorMgr;
import com.hq.payms.zenmind.dao.restSTImpl.RestProxySTImpl;
import com.hq.payms.zenmind.dao.restSTImpl.RestTemplateMgr;
import com.hq.storeFileClient.service.StoreFileClientMgr;
import com.hq.thirdPartyClient.service.ThirdPartyClientMgr;
import com.zenmind.dao.RestDaoMgr;
import com.zenmind.dao.mongodb.MongodbTemplateMgr;
import com.zenmind.dao.redis.RedisTemplateMgr;

@RefreshScope
@Service
public class PayMSAppStarter implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired // 注入接口服务
	private PayMSCfg payMSCfg;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		MainLog.info(LogModule.StartInfo, "StartInfo[init]", "config used:" + payMSCfg.getEnvMark());
		PayMSCfgMgr.setCfg(payMSCfg);

		MainLog.info(LogModule.StartInfo, "StartInfo[init]", "MongodbTemplateMgr.getInstance().init(mongodbCfg)");
		MongodbTemplateMgr.getInstance().init(payMSCfg.getMongodbCfg());

		MainLog.info(LogModule.StartInfo, "StartInfo[init]", "MongodbTemplateMgr.getInstance().ensureIndex(MsTableIndexMgr.getInstance().getClassInfos())");
		MongodbTemplateMgr.getInstance().ensureIndex(MsTableIndexMgr.getInstance().getClassInfos());

		MainLog.info(LogModule.StartInfo, "StartInfo[init]", "RestTemplateMgr.init()");
		RestTemplateMgr.getInstance().init();

		MainLog.info(LogModule.StartInfo, "RestDaoMgr[init]", "RestDaoMgr.init()");
		RestDaoMgr.init(RestProxySTImpl.getInstance());

		if (payMSCfg.isRedisEnabled()) {
			MainLog.info(LogModule.StartInfo, "RedisTemplateMgr[init]", "RedisTemplateMgr.init(stringRedisTemplateP, timeOutInSecondsP)");
			RedisTemplateMgr.getInstance().init(stringRedisTemplate, payMSCfg.getRedisConfig());
		}

		// DAO日志拦截
		MainLog.info(LogModule.StartInfo, "LogInterceptorMgr[init]", "LogInterceptorMgr.getInstance().init()");
		LogInterceptorMgr.getInstance().init();

		// storeFileClient组件初始化
		MainLog.info(LogModule.StartInfo, "StoreFileClientMgr[init]", "StoreFileClientMgr.init(storeFileServer)");
		StoreFileClientMgr.init(payMSCfg.getFileHost());
		// ThirdPartyClient组件初始化
		MainLog.info(LogModule.StartInfo, "ThirdPartyClientMgr[init]", "ThirdPartyClientMgr.init(thirdPartyServerURL)");
		ThirdPartyClientMgr.init(payMSCfg.getThirdPartyHost());
		
		//支付组件初始化
		MainLog.info(LogModule.StartInfo, "WxpayStarter[init]", "WxpayStarter.init(zmWXPayCfg)");
		WxpayStarter.getInstance().init(payMSCfg.getZmWXPayCfg());
		
		MainLog.info(LogModule.StartInfo, "AlipayStarter[init]", "ZmSafeNormalWXPayMgr.init(zmAlipayCfg)");
		AlipayStarter.getInstance().init(payMSCfg.getZmAlipayCfg());
		
	}
}

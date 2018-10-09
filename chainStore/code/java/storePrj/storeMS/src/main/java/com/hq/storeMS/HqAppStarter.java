package com.hq.storeMS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.hq.chainClient.ChainRestClientMgr;
import com.hq.customerRestClient.CustomerClientMgr;
import com.hq.orderRestClient.OrderRestClientMgr;
import com.hq.payRestClient.service.common.PayClientCfg;
import com.hq.storeFileClient.service.StoreFileClientMgr;
import com.hq.storeMS.common.config.StoreMSCfg;
import com.hq.storeMS.common.config.StoreMSCfgMgr;
import com.hq.storeMS.common.constants.ServerConstants;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.common.message.trigger.MessageTriggerMgr;
import com.hq.storeMS.common.scheduleTask.StoreTaskMgr;
import com.hq.storeMS.common.tableIndex.MsTableIndexMgr;
import com.hq.storeMS.rabbitmq.EventReceiverMgr;
import com.hq.storeMS.rabbitmq.LoggerSenderMgr;
import com.hq.storeMS.rabbitmq.TaskSenderMgr;
import com.hq.storeMS.service.appointment.bs.wxNotice.WxNoticeSenderMgr;
import com.hq.storeMS.service.monitor.bs.StoreMsMonitorMgr;
import com.hq.storeMS.service.opLog.bs.asyn.OpLogTaskMgr;
import com.hq.storeMS.service.opuser.bs.OPUserMgr;
import com.hq.storeMS.service.saas.bs.SaasMgr;
import com.hq.storeMS.service.saas.data.SaasData;
import com.hq.storeMS.service.splitData.bs.SplitDataMgr;
import com.hq.storeMS.service.sysDataInit.bs.SysDataInitMgr;
import com.hq.storeMS.zenmind.dao.interceptor.LogInterceptorMgr;
import com.hq.storeMS.zenmind.dao.rest.restSTImpl.RestProxySTImpl;
import com.hq.storeMS.zenmind.dao.rest.restSTImpl.RestTemplateMgr;
import com.hq.storeManagerRestClient.StoreMngClientMgr;
import com.hq.thirdPartyClient.service.ThirdPartyClientMgr;
import com.zenmind.dao.RestDaoMgr;
import com.zenmind.dao.mongodb.MongodbTemplateMgr;
import com.zenmind.dao.push.PushTemplateMgr;
import com.zenmind.dao.redis.RedisTemplateMgr;
import com.zenmind.umeng.PushUMProxyImpl;
import com.zenmind.wx.WxTemplateMgr;

@RefreshScope
@Service
public class HqAppStarter implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired // 注入接口服务
	private StoreMSCfg storeMSCfg;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		MainLog.info(LogModule.StartInfo, "StartInfo[init]", "config used:" + storeMSCfg.getEnvMark());
		StoreMSCfgMgr.setCfg(storeMSCfg);

		MainLog.info(LogModule.StartInfo, "StartInfo[init]", "MongodbTemplateMgr.getInstance().init(mongodbCfg)");
		MongodbTemplateMgr.getInstance().init(storeMSCfg.getMongodbCfg());

		MainLog.info(LogModule.StartInfo, "StartInfo[init]", "MongodbTemplateMgr.getInstance().ensureIndex(MsTableIndexMgr.getInstance().getClassInfos())");
		MongodbTemplateMgr.getInstance().ensureIndex(MsTableIndexMgr.getInstance().getClassInfos());

		MainLog.info(LogModule.StartInfo, "StartInfo[init]", "SaasMgr.getInstance().init()");
		SaasData saasData = SaasMgr.getInstance().init();

		OPUserMgr.getInstance().init(saasData.getSuperOPAdminRole());

		MainLog.info(LogModule.StartInfo, "StartInfo[init]", "RestTemplateMgr.init()");
		RestTemplateMgr.getInstance().init();

		MainLog.info(LogModule.StartInfo, "RestDaoMgr[init]", "RestDaoMgr.init()");
		RestDaoMgr.init(RestProxySTImpl.getInstance());

		if (storeMSCfg.isRedisEnabled()) {
			MainLog.info(LogModule.StartInfo, "RedisTemplateMgr[init]", "RedisTemplateMgr.init(stringRedisTemplateP, timeOutInSecondsP)");
			RedisTemplateMgr.getInstance().init(stringRedisTemplate, storeMSCfg.getRedisConfig());
		}

		MainLog.info(LogModule.StartInfo, "TaskSenderMgr[init]", "TaskSenderMgr.init(storeMSCfg)");
		TaskSenderMgr.getInstance().init(storeMSCfg);

		MainLog.info(LogModule.StartInfo, "LoggerSenderMgr[init]", "LoggerSenderMgr.init(storeMSCfg)");
		LoggerSenderMgr.getInstance().init(storeMSCfg);

		MainLog.info(LogModule.StartInfo, "EventReceiverMgr[init]", "EventReceiverMgr.init(storeMSCfg)");
		EventReceiverMgr.getInstance().init(storeMSCfg);

		// DAO日志拦截
		MainLog.info(LogModule.StartInfo, "LogInterceptorMgr[init]", "LogInterceptorMgr.getInstance().init()");
		LogInterceptorMgr.getInstance().init();

		// 系统默认的性能监控开关
		MainLog.info(LogModule.StartInfo, "StoreMsMonitorMgr[init]", "StoreMsMonitorMgr.getInstance().init(boolean)");
		StoreMsMonitorMgr.getInstance().init(storeMSCfg.isMonitorEnabled());

		// 微信接口组件初始化
		MainLog.info(LogModule.StartInfo, "WxTemplateMgr[init]", "WxTemplateMgr.getInstance().init(wxConfig)");
		WxTemplateMgr.getInstance().init(storeMSCfg.getWxConfig());

		// 智美预约小程序发送通知组件初始化
		MainLog.info(LogModule.StartInfo, "WxNoticeSenderMgr[init]", "WxNoticeSenderMgr.getInstance().init(storeMSCfg)");
		WxNoticeSenderMgr.getInstance().init(storeMSCfg);
		
		MainLog.info(LogModule.StartInfo, "StoreTaskMgr[init]", "StoreTaskMgr.getInstance().init()");
		StoreTaskMgr.getInstance().init();
		MainLog.info(LogModule.StartInfo, "OpLogTaskMgr[init]", "OpLogTaskMgr.getInstance().init()");
		OpLogTaskMgr.getInstance().init();
		
		// storeFileClient组件初始化
		MainLog.info(LogModule.StartInfo, "StoreFileClientMgr[init]", "StoreFileClientMgr.init(storeFileServer)");
		StoreFileClientMgr.init(storeMSCfg.getFileHost());
		// ThirdPartyClient组件初始化
		MainLog.info(LogModule.StartInfo, "ThirdPartyClientMgr[init]", "ThirdPartyClientMgr.init(thirdPartyServerURL)");
		ThirdPartyClientMgr.init(storeMSCfg.getThirdPartyHost());
		// OrderRestClient组件初始化
		MainLog.info(LogModule.StartInfo, "OrderClientMgr[init]", "OrderClientMgr.init(orderServerUrl)");
		OrderRestClientMgr.init(storeMSCfg.getOrderHost(), ServerConstants.appName);
		// PayRestClient组件初始化
		MainLog.info(LogModule.StartInfo, "PayClientCfg[init]", "PayClientCfg.init(payServerUrl)");
		PayClientCfg.init(storeMSCfg.getPayHost());
		//连锁店组件初始化
		MainLog.info(LogModule.StartInfo, "ChainClientMgr[init]", "ChainClientMgr.init()");
		ChainRestClientMgr.init(storeMSCfg.getChainHost(), ServerConstants.appName);
		//管理后台组件初始化
		MainLog.info(LogModule.StartInfo, "StoreMngClientMgr[init]", "StoreMngClientMgr.init()");
		StoreMngClientMgr.init(storeMSCfg.getMngHost(), ServerConstants.appName);
		//C端组件初始化
		MainLog.info(LogModule.StartInfo, "StoreMngClientMgr[init]", "StoreMngClientMgr.init()");
		CustomerClientMgr.init(storeMSCfg.getCustomerHost(), ServerConstants.appName);
		
		//友盟推送组件初始化
		if(storeMSCfg.isUmengEnabled()) {
			PushUMProxyImpl.getInstance().init(storeMSCfg.getUmengConfig());
			PushTemplateMgr.init(PushUMProxyImpl.getInstance());
			MessageTriggerMgr.getInstance().init(); 
		}
		
		//初始化系统数据信息
		MainLog.info(LogModule.StartInfo, "SysDataInitMgr[init]", "SysDataInitMgr.init()");
		SysDataInitMgr.getInstance().init();
		
		// 店铺数据拆分
		MainLog.info(LogModule.StartInfo, "SplitDataMgr[init]", "SplitDataMgr.getInstance().init(xxxx)");
		SplitDataMgr.getInstance().init(); 
	}
}

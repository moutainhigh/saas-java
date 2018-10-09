package com.hq.orderMS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.hq.orderMS.common.config.OrderMSCfg;
import com.hq.orderMS.common.config.OrderMSCfgMgr;
import com.hq.orderMS.common.log.LogModule;
import com.hq.orderMS.common.log.MainLog;
import com.hq.orderMS.common.tableIndex.MsTableIndexMgr;
import com.hq.orderMS.rabbitmq.EventReceiverMgr;
import com.hq.orderMS.rabbitmq.LoggerSenderMgr;
import com.hq.orderMS.service.monitor.bs.StoreMsMonitorMgr;
import com.hq.orderMS.service.sysDataInit.bs.SysDataInitMgr;
import com.hq.orderMS.zenmind.dao.interceptor.LogInterceptorMgr;
import com.zenmind.dao.mongodb.MongodbTemplateMgr;
import com.zenmind.dao.redis.RedisTemplateMgr;

@RefreshScope
@Service
public class HqAppStarter implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired // 注入接口服务
	private OrderMSCfg orderMSCfg;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		MainLog.info(LogModule.StartInfo, "StartInfo[init]", "config used:" + orderMSCfg.getEnvMark());
		OrderMSCfgMgr.setCfg(orderMSCfg);

		MainLog.info(LogModule.StartInfo, "StartInfo[init]", "MongodbTemplateMgr.getInstance().init(mongodbCfg)");
		MongodbTemplateMgr.getInstance().init(orderMSCfg.getMongodbCfg());

		MainLog.info(LogModule.StartInfo, "StartInfo[init]", "MongodbTemplateMgr.getInstance().ensureIndex(MsTableIndexMgr.getInstance().getClassInfos())");
		MongodbTemplateMgr.getInstance().ensureIndex(MsTableIndexMgr.getInstance().getClassInfos());

		if (orderMSCfg.isRedisEnabled()) {
			MainLog.info(LogModule.StartInfo, "RedisTemplateMgr[init]", "RedisTemplateMgr.getInstance().init(stringRedisTemplateP, timeOutInSecondsP)");
			RedisTemplateMgr.getInstance().init(stringRedisTemplate, orderMSCfg.getRedisConfig());
		}

		MainLog.info(LogModule.StartInfo, "LoggerSenderMgr[init]", "LoggerSenderMgr.init(orderMSCfg)");
		LoggerSenderMgr.getInstance().init(orderMSCfg);

		MainLog.info(LogModule.StartInfo, "EventSenderMgr[init]", "EventReceiverMgr.init(orderMSCfg)");
		EventReceiverMgr.getInstance().init(orderMSCfg);

		// DAO日志拦截
		MainLog.info(LogModule.StartInfo, "LogInterceptorMgr[init]", "LogInterceptorMgr.getInstance().init()");
		LogInterceptorMgr.getInstance().init();

		// 系统默认的性能监控开关
		MainLog.info(LogModule.StartInfo, "StoreMsMonitorMgr[init]", "StoreMsMonitorMgr.getInstance().init(boolean)");
		StoreMsMonitorMgr.getInstance().init(orderMSCfg.isMonitorEnabled());

		// 初始化系统数据信息
		MainLog.info(LogModule.StartInfo, "SysDataInitMgr[init]", "SysDataInitMgr.init()");
		SysDataInitMgr.getInstance().init();
	}
}

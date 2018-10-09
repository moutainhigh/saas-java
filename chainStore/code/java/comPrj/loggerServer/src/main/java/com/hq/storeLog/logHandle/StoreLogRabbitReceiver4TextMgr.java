package com.hq.storeLog.logHandle;

import java.util.concurrent.atomic.AtomicBoolean;

import com.hq.common.config.StoreLogMSCfg;
import com.hq.common.config.StoreLogMSCfgMgr;
import com.hq.common.log.LogModule;
import com.hq.common.log.MainLog;
import com.hq.stream.log.StoreLog;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.common.json.JsonUtil;
import com.zenmind.dao.rabbit.RabbitCfg;
import com.zenmind.dao.rabbit.RabbitCfgBuilder;
import com.zenmind.dao.rabbit.RabbitMsgHandler;
import com.zenmind.dao.rabbit.bs.log.RabbitReceiver4Log;

public class StoreLogRabbitReceiver4TextMgr {

	public static StoreLogRabbitReceiver4TextMgr getInstance() {
		return HotSwap.getInstance().getSingleton(StoreLogRabbitReceiver4TextMgr.class);
	}
	
	private AtomicBoolean isInit = new AtomicBoolean(false);

	public void init() {
		if(isInit.compareAndSet(false, true)){
			initLogQueue();
			//处理前期遗留的问题   后期可以删除这几个方法
			initLogConsumer();
			initLogGroup();
			initLogProducer();
		}
	}
	
	private void initLogQueue(){
		StoreLogMSCfg prop = StoreLogMSCfgMgr.getProp();
		RabbitCfg rabbitCfg = RabbitCfgBuilder
				.newBuilder(prop.getLogHost(), prop.getLogPort())
				.withCredit(prop.getLogUsername(), prop.getLogPassword())
				.withExchange(prop.getLogExchange())
				.withQueue(prop.getLogQueue())
				.withRouteKey(prop.getLogRouteKey())
				.isPersistent(prop.isLogPesistent()).build();
		
		RabbitMsgHandler<String> handlerP = new RabbitMsgHandler<String>() {
			@Override
			public boolean handle(String msg) {
				try {
					StoreLog storeLog = JsonUtil.getInstance().fromJson(msg, StoreLog.class);
					LogAsynHandler.getInstance().addLog(storeLog);
				} catch (Exception e) {
					MainLog.error(LogModule.Tmp, "StoreLogRabbitReceiver4TextMgr[initLogConsumer]", "has Exception", e);
				}
				return true;
			}
		};
		RabbitReceiver4Log.newInstance(rabbitCfg, handlerP);
	}
	
	private void initLogConsumer(){
		StoreLogMSCfg prop = StoreLogMSCfgMgr.getProp();
		RabbitCfg rabbitCfg = RabbitCfgBuilder
				.newBuilder(prop.getLogHost(), prop.getLogPort())
				.withCredit(prop.getLogUsername(), prop.getLogPassword())
				.withExchange(prop.getLogExchange())
				.withQueue("storeLog.consumer")
				.withRouteKey(prop.getLogRouteKey())
				.isPersistent(prop.isLogPesistent()).build();

		RabbitMsgHandler<String> handlerP = new RabbitMsgHandler<String>() {
			@Override
			public boolean handle(String msg) {
				try {
					System.out.println("initLogGroup:"+msg);
				} catch (Exception e) {
					MainLog.error(LogModule.Tmp, "StoreLogRabbitReceiver4TextMgr[initLogConsumer]", "has Exception", e);
				}
				return true;
			}
		};
		RabbitReceiver4Log.newInstance(rabbitCfg, handlerP);
	}
	
	private void initLogGroup(){
		StoreLogMSCfg prop = StoreLogMSCfgMgr.getProp();
		RabbitCfg rabbitCfg = RabbitCfgBuilder
				.newBuilder(prop.getLogHost(), prop.getLogPort())
				.withCredit(prop.getLogUsername(), prop.getLogPassword())
				.withExchange(prop.getLogExchange())
				.withQueue("storeLog.storeLogGroup")
				.withRouteKey(prop.getLogRouteKey())
				.isPersistent(prop.isLogPesistent()).build();

		RabbitMsgHandler<String> handlerP = new RabbitMsgHandler<String>() {
			@Override
			public boolean handle(String msg) {
				try {
					System.out.println("initLogGroup:"+msg);
				} catch (Exception e) {
					MainLog.error(LogModule.Tmp, "StoreLogRabbitReceiver4TextMgr[initLogGroup]", "has Exception", e);
				}
				return true;
			}
		};
		RabbitReceiver4Log.newInstance(rabbitCfg, handlerP);
	}
	
	private void initLogProducer(){
		StoreLogMSCfg prop = StoreLogMSCfgMgr.getProp();
		RabbitCfg rabbitCfg = RabbitCfgBuilder
				.newBuilder(prop.getLogHost(), prop.getLogPort())
				.withCredit(prop.getLogUsername(), prop.getLogPassword())
				.withExchange(prop.getLogExchange())
				.withQueue("storeLog.producer")
				.withRouteKey(prop.getLogRouteKey())
				.isPersistent(prop.isLogPesistent()).build();

		RabbitMsgHandler<String> handlerP = new RabbitMsgHandler<String>() {
			@Override
			public boolean handle(String msg) {
				try {
					System.out.println("initLogProducer:"+msg);
				} catch (Exception e) {
					MainLog.error(LogModule.Tmp, "StoreLogRabbitReceiver4TextMgr[initLogProducer]", "has Exception", e);
				}
				return true;
			}
		};
		RabbitReceiver4Log.newInstance(rabbitCfg, handlerP);
	}

}

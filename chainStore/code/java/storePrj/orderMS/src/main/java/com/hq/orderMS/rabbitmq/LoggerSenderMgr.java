package com.hq.orderMS.rabbitmq;

import java.util.concurrent.atomic.AtomicBoolean;

import com.hq.orderMS.common.config.OrderMSCfg;
import com.hq.orderMS.common.log.LogModule;
import com.hq.orderMS.common.log.MainLog;
import com.hq.stream.log.StoreLog;
import com.zenmind.common.asynExecutor.IntfErrorHandler;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.common.json.JsonUtil;
import com.zenmind.dao.rabbit.RabbitCfg;
import com.zenmind.dao.rabbit.RabbitCfgBuilder;
import com.zenmind.dao.rabbit.bs.log.RabbitSender4Log;

public class LoggerSenderMgr {
	public static LoggerSenderMgr getInstance() {
		return HotSwap.getInstance().getSingleton(LoggerSenderMgr.class);
	}
	
	private RabbitSender4Log sender;
	private boolean open = false;
	private AtomicBoolean isInit = new AtomicBoolean(false);
	
	public void sendLogger(StoreLog log) {
		if(isOpen()){
			sender.addLog(JsonUtil.getInstance().toJson(log));
		}
	}

	public void init(OrderMSCfg cfg) {
		if(isInit.compareAndSet(false, true)){
			try {
				if(cfg.isLogEnabled()){
					RabbitCfg rabbitCfg = RabbitCfgBuilder.newBuilder(cfg.getLogHost(), cfg.getLogPort())
							.withCredit(cfg.getLogUsername(), cfg.getLogPassword())
							.withExchange(cfg.getLogExchange())
							.withQueue(cfg.getLogQueue())
							.withRouteKey(cfg.getLogRouteKey())
							.isPersistent(cfg.isLogPesistent())
							.build();
					sender = RabbitSender4Log.newInstance(rabbitCfg, new IntfErrorHandler<String>() {
						@Override
						public void handle(String target, String msg, Throwable e) {
							MainLog.error(LogModule.Tmp, "RabbitSender4Log[send]", msg, e);
						}
					});
					sender.init();
					open = true;
				}
			} catch (Exception e) {
				MainLog.error(LogModule.Tmp, "LoggerSenderMgr[init]", "初始化LoggerSenderMgr失败", e);
			}
		}
	}
	
	public boolean isOpen() {
		return open;
	}
}

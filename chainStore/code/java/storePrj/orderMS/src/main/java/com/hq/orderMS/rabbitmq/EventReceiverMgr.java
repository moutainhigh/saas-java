package com.hq.orderMS.rabbitmq;

import java.util.concurrent.atomic.AtomicBoolean;

import com.hq.orderMS.common.config.OrderMSCfg;
import com.hq.orderMS.common.log.LogModule;
import com.hq.orderMS.common.log.MainLog;
import com.hq.orderMS.eventHandle.EventHandleMgr;
import com.hq.stream.event.StoreEvent;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.common.json.JsonUtil;
import com.zenmind.dao.rabbit.RabbitCfg;
import com.zenmind.dao.rabbit.RabbitCfgBuilder;
import com.zenmind.dao.rabbit.RabbitMsgHandler;
import com.zenmind.dao.rabbit.bs.event.RabbitReceiver4Event;

public class EventReceiverMgr {
	public static EventReceiverMgr getInstance() {
		return HotSwap.getInstance().getSingleton(EventReceiverMgr.class);
	}
	
	//只初始化一次
	private AtomicBoolean isInit = new AtomicBoolean(false);
	
	public void init(OrderMSCfg cfg) {
		try {
			if(isInit.compareAndSet(false, true)){
				if(cfg.isEventEnabled()){
					RabbitCfg rabbitCfg = RabbitCfgBuilder.newBuilder(cfg.getEventHost(), cfg.getEventPort())
							.withCredit(cfg.getEventUsername(), cfg.getEventPassword())
							.withExchange(cfg.getEventExchange())
							.withQueue(cfg.getEventQueue())
							.withRouteKey(cfg.getEventRouteKey())
							.isPersistent(cfg.isEventPesistent()).build();
					RabbitMsgHandler<String> handlerP = new RabbitMsgHandler<String>(){
						@Override
						public boolean handle(String msg) {
							MainLog.warn(LogModule.Tmp, "EventReceiverMgr[init]", msg);
							StoreEvent storeEvent = JsonUtil.getInstance().fromJson(msg, StoreEvent.class);
							EventHandleMgr.getInstance().handle(storeEvent);
							return true;
						}
					};
					RabbitReceiver4Event.newInstance(rabbitCfg, handlerP);
				}
			}
		} catch (Exception e) {
			MainLog.error(LogModule.Tmp, "EventReceiverMgr[init]", "初始化EventReceiverMgr失败", e);
		}
	}
}

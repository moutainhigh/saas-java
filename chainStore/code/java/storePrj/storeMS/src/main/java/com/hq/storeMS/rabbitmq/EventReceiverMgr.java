package com.hq.storeMS.rabbitmq;

import java.util.concurrent.atomic.AtomicBoolean;

import com.hq.storeMS.common.config.StoreMSCfg;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.eventHandle.EventHandleMgr;
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
	
	public void init(StoreMSCfg storeMSCfg) {
		try {
			if(isInit.compareAndSet(false, true)){
				if(storeMSCfg.isEventEnabled()){
					RabbitCfg rabbitCfg = RabbitCfgBuilder.newBuilder(storeMSCfg.getEventHost(), storeMSCfg.getEventPort())
							.withCredit(storeMSCfg.getEventUsername(), storeMSCfg.getEventPassword())
							.withExchange(storeMSCfg.getEventExchange())
							.withQueue(storeMSCfg.getEventQueue())
							.withRouteKey(storeMSCfg.getEventRouteKey())
							.isPersistent(storeMSCfg.isEventPesistent()).build();
					RabbitMsgHandler<String> handlerP = new RabbitMsgHandler<String>(){
						@Override
						public boolean handle(String msg) {
							try {
								StoreEvent storeEvent = JsonUtil.getInstance().fromJson(msg, StoreEvent.class);
								EventHandleMgr.getInstance().handle(storeEvent);
							} catch (Exception e) {
								MainLog.error(LogModule.Tmp, "EventReceiverMgr[init]", msg, e);
							}
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

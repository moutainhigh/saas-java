package com.hq.taskMS.storeEvent;

import java.util.concurrent.atomic.AtomicBoolean;

import com.hq.stream.event.StoreEvent;
import com.hq.taskMS.common.config.StoreTaskMSCfg;
import com.hq.taskMS.common.config.StoreTaskMSCfgMgr;
import com.hq.taskMS.common.log.LogModule;
import com.hq.taskMS.common.log.MainLog;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.common.json.JsonUtil;
import com.zenmind.dao.rabbit.RabbitCfg;
import com.zenmind.dao.rabbit.RabbitCfgBuilder;
import com.zenmind.dao.rabbit.bs.event.RabbitSender4Event;

public class StoreEventSenderMgr {

	public static StoreEventSenderMgr getInstance() {
		return HotSwap.getInstance().getSingleton(StoreEventSenderMgr.class);
	}

	private RabbitSender4Event sender;
	private boolean open = false;
	// 只初始化一次
	private AtomicBoolean isInit = new AtomicBoolean(false);

	public void init() {
		if (isInit.compareAndSet(false, true)) {
			try {
				StoreTaskMSCfg prop = StoreTaskMSCfgMgr.getProp();
				
				if(!prop.isEventEnabled()) {
					return;
				}
				
				RabbitCfg rabbitCfg = RabbitCfgBuilder.newBuilder(prop.getEventHost(), prop.getEventPort())
						.withCredit(prop.getEventUsername(), prop.getEventPassword())
						.withExchange(prop.getEventExchange()).withQueue(prop.getEventQueue())
						.withRouteKey(prop.getEventRouteKey()).isPersistent(prop.isEventPesistent()).build();
				sender = RabbitSender4Event.newInstance(rabbitCfg);
				open = true;
			} catch (Exception e) {
				MainLog.error(LogModule.Tmp, "StoreEventSenderMgr[init]", "初始化StoreEventSenderMgr失败", e);
			}
		}
	}

	public void send(StoreEvent event) {
		if (isOpen()) {
			String json = JsonUtil.getInstance().toJson(event);
			MainLog.warn(LogModule.Tmp, "StoreEventSenderMgr[send]", json);
			sender.send(json);
		}
	}

	public boolean isOpen() {
		return open;
	}

}

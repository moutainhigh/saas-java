package com.hq.taskMS.storeEvent;

import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import com.hq.stream.event.StoreEvent;
import com.hq.stream.event.StoreEventType;
import com.hq.stream.event.data.EventContent;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.common.json.JsonUtil;

public class OrderTmpRcdMgr {
	public static OrderTmpRcdMgr getInstance() {
		return HotSwap.getInstance().getSingleton(OrderTmpRcdMgr.class);
	}

	private ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
	// 只初始化一次
	private AtomicBoolean isInit = new AtomicBoolean(false);

	// 5分钟的毫秒数
	private final long fiveMinutes = 5L * 60 * 1000;

	public void init() {
		if (isInit.compareAndSet(false, true)) {
			// 每5分钟 触发一次
			executor.scheduleAtFixedRate(new Runnable() {
				@Override
				public void run() {
					EventContent content = EventContent.newInstance();
					content.setRemark("test");
					StoreEvent event = new StoreEvent();
					event.setTid(UUID.randomUUID().toString());
					event.setEventType(StoreEventType.OrderCancelHook.ordinal());
					event.setStoreId(0);
					event.setJsonData(JsonUtil.getInstance().toJson(content));
					StoreEventSenderMgr.getInstance().send(event);
				}
			}, 10L * 1000, fiveMinutes, TimeUnit.MILLISECONDS);
		}
	}
}

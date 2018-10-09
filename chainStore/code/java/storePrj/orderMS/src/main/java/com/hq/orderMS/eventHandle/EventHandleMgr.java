package com.hq.orderMS.eventHandle;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;

import com.hq.orderMS.eventHandle.handler.OrderTmpRcdEventHandler;
import com.hq.stream.event.StoreEvent;
import com.hq.stream.event.StoreEventType;
import com.zenmind.common.hotSwap.HotSwap;

public class EventHandleMgr {

	public static EventHandleMgr getInstance() {
		return HotSwap.getInstance().getSingleton(EventHandleMgr.class);
	}

	private Map<StoreEventType, Set<IStoreEventHandler>> handleMapper = new HashMap<StoreEventType, Set<IStoreEventHandler>>();

	public EventHandleMgr() {
		Set<IStoreEventHandler> evnetHandlers = new HashSet<IStoreEventHandler>();
		evnetHandlers.add(new OrderTmpRcdEventHandler());
		handleMapper.put(StoreEventType.OrderCancelHook, evnetHandlers);
	}

	public void handle(StoreEvent storeEvent) {
		StoreEventType eventTypeEnum = storeEvent.getEventTypeEnum();
		Set<IStoreEventHandler> handleSet = handleMapper.get(eventTypeEnum);
		if (CollectionUtils.isNotEmpty(handleSet)) {
			for (IStoreEventHandler storeEventHandlerTmp : handleSet) {
				storeEventHandlerTmp.handle(storeEvent);
			}
		}
	}
}

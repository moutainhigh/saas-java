package com.hq.storeMS.eventHandle;

import com.hq.stream.event.StoreEvent;

public interface IStoreEventHandler {
	public void handle(StoreEvent event);
}

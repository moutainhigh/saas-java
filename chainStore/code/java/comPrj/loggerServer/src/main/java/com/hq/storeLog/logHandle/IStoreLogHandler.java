package com.hq.storeLog.logHandle;

import com.hq.stream.log.StoreLog;

public interface IStoreLogHandler {
	public void handle(StoreLog log);
}

package com.hq.orderMS.service.monitor.bs;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.common.json.JsonUtil;
import com.zenmind.monitor.MonitorMgr;
import com.zenmind.monitor.tracer.ThreadTraceMgr;

public class StoreMsMonitorMgr {

	public static StoreMsMonitorMgr getInstance() {
		return HotSwap.getInstance().getSingleton(StoreMsMonitorMgr.class);
	}

	public void init(boolean enabled) {
		JsonUtil.getInstance().initMonitor();
		MonitorMgr.getInstance().enableAll(enabled);
		ThreadTraceMgr.getInstance().enabled(enabled);
	}
}

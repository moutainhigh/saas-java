package com.hq.chainMS.service.monitor.bs;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.common.json.JsonUtil;
import com.zenmind.monitor.MonitorMgr;
import com.zenmind.monitor.tracer.ThreadTraceMgr;

public class ChainMonitorMgr {

	public static ChainMonitorMgr getInstance() {
		return HotSwap.getInstance().getSingleton(ChainMonitorMgr.class);
	}

	public void init(boolean enabled) {
		JsonUtil.getInstance().initMonitor();
		MonitorMgr.getInstance().enableAll(enabled);
		ThreadTraceMgr.getInstance().enabled(enabled);
	}
}

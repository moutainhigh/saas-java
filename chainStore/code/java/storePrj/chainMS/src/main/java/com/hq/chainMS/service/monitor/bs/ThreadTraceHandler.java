package com.hq.chainMS.service.monitor.bs;

import com.hq.chainMS.service.common.ReqResult;
import com.hq.chainMS.service.common.RespStatus;
import com.hq.chainMS.service.monitor.apiData.TracerCfg;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.monitor.tracer.ThreadTraceMgr;

public class ThreadTraceHandler {
	
	public static ThreadTraceHandler getInstance() {
		return HotSwap.getInstance().getSingleton(ThreadTraceHandler.class);
	}

	public ReqResult<String> enableTrace(boolean enabled) {
		ReqResult<String> result = ReqResult.newInstance(false, String.class);
		ThreadTraceMgr.getInstance().enabled(enabled);
		result.setStatus(RespStatus.OK);
		result.setSuccess(true);
		return result;
	}
	public ReqResult<String> clearTrace() {
		ReqResult<String> result = ReqResult.newInstance(false, String.class);
		ThreadTraceMgr.getInstance().clear();
		result.setStatus(RespStatus.OK);
		result.setSuccess(true);
		return result;
	}

	public ReqResult<String> reCfg(TracerCfg cfg) {
		ReqResult<String> result = ReqResult.newInstance(false, String.class);
		ThreadTraceMgr.getInstance().reConfig(cfg.getQueueSize(), cfg.getMethodMinTime(), cfg.getThreadMinTime());
		result.setStatus(RespStatus.OK);
		result.setSuccess(true);
		return result;
	}

	public ReqResult<String> getTraceInfo() {
		ReqResult<String> result = ReqResult.newInstance(false, String.class);
		String traceInfo = ThreadTraceMgr.getInstance().getTraceInfo();
		result.setStatus(RespStatus.OK);
		result.setSuccess(true);
		result.setTarget(traceInfo);
		return result;
	}
	
	
}

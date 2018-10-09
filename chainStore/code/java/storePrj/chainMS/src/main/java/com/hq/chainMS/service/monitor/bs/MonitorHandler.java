package com.hq.chainMS.service.monitor.bs;

import org.apache.commons.lang3.StringUtils;

import com.hq.chainMS.service.common.ReqResult;
import com.hq.chainMS.service.common.RespStatus;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.monitor.MonitorMgr;

public class MonitorHandler {
	
	public static MonitorHandler getInstance() {
		return HotSwap.getInstance().getSingleton(MonitorHandler.class);
	}


	public ReqResult<String> enable(String key, boolean enabled) {
		ReqResult<String> result = ReqResult.newInstance(false, String.class);
		
		if(StringUtils.equals(key, "all")){
			MonitorMgr.getInstance().enableAll(enabled);
			result.setStatus(RespStatus.OK);
			result.setSuccess(true);
		}else{
			MonitorMgr.getInstance().enable(key, enabled);
			result.setStatus(RespStatus.OK);
			result.setSuccess(true);			
		}
		return result;
	}


	public ReqResult<String> reset(String key) {
		ReqResult<String> result = ReqResult.newInstance(false, String.class);
		
		if(StringUtils.equals(key, "all")){
			MonitorMgr.getInstance().resetAll();
			result.setStatus(RespStatus.OK);
			result.setSuccess(true);
		}else{
			MonitorMgr.getInstance().reset(key);
			result.setStatus(RespStatus.OK);
			result.setSuccess(true);			
		}
		return result;
	}


	public ReqResult<String> getInfo() {
		ReqResult<String> result = ReqResult.newInstance(false, String.class);
		
		String info = MonitorMgr.getInstance().getInfo();
		result.setStatus(RespStatus.OK);
		result.setSuccess(true);	
		result.setTarget(info);
		
		return result;
	}
	
	
	
}

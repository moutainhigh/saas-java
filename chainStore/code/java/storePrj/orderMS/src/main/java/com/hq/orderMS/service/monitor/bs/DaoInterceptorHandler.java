package com.hq.orderMS.service.monitor.bs;

import com.hq.orderMS.common.log.LogModule;
import com.hq.orderMS.common.log.MainLog;
import com.hq.orderMS.service.common.ReqResult;
import com.hq.orderMS.service.common.RespStatus;
import com.hq.orderMS.zenmind.dao.interceptor.LogInterceptorConfig;
import com.hq.orderMS.zenmind.dao.interceptor.LogInterceptorMgr;
import com.zenmind.common.hotSwap.HotSwap;

public class DaoInterceptorHandler {

	public static DaoInterceptorHandler getInstance() {
		return HotSwap.getInstance().getSingleton(DaoInterceptorHandler.class);
	}

	public ReqResult<String> reset(LogInterceptorConfig config) {
		ReqResult<String> result = ReqResult.newInstance(false, String.class);
		try {
			LogInterceptorMgr.getInstance().reset(config);
			result.setStatus(RespStatus.OK);
			result.setSuccess(true);
		} catch (Exception e) {
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(LogModule.Tmp, "DaoInterceptorHandler[reset]", "", e);
		}
		return result;
	}

}

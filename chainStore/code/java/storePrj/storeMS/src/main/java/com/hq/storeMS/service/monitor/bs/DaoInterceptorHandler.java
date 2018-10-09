package com.hq.storeMS.service.monitor.bs;

import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RespStatus;
import com.hq.storeMS.zenmind.dao.interceptor.LogInterceptorConfig;
import com.hq.storeMS.zenmind.dao.interceptor.LogInterceptorMgr;
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
			final String reason = LogHelper.getInstance().exceptionReason(config);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(LogModule.Tmp, "DaoInterceptorHandler[reset]", reason, e);
		}
		return result;
	}

}

package com.hq.chainMS.service.monitor.bs;

import com.hq.chainMS.common.log.LogHelper;
import com.hq.chainMS.common.log.LogModule;
import com.hq.chainMS.common.log.MainLog;
import com.hq.chainMS.service.common.ReqResult;
import com.hq.chainMS.service.common.RespStatus;
import com.hq.chainMS.zenmind.dao.interceptor.LogInterceptorConfig;
import com.hq.chainMS.zenmind.dao.interceptor.LogInterceptorMgr;
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

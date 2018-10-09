package com.hq.storeMS.service.cuserChainData.bs;

import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.service.common.ExceptionInfo;
import com.hq.storeMS.service.common.HandlerHelper;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.cuserChainData.data.CuserChainData;
import com.zenmind.common.hotSwap.HotSwap;

public class CuserChainDataHandler {

	public static CuserChainDataHandler getInstance() {
		return HotSwap.getInstance().getSingleton(CuserChainDataHandler.class);
	}

	private final LogModule logModule = LogModule.CuserChainData;

	public ReqResult<CuserChainData> getCuserChainData(long cuserId) {
		ReqResult<CuserChainData> result = ReqResult.newInstance(false, CuserChainData.class);
		try {
			CuserChainData cuserChainData = CuserChainDataMgr.getInstance().get(cuserId);
			result.setTarget(cuserChainData);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "CuserChainDataHandler[getCuserChainData]";
			final String reason = LogHelper.getInstance().exceptionReason(cuserId);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule).withLogId(logId)
					.withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
}

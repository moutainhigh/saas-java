package com.hq.customerMS.service.storeClerkInfo.bs;

import com.hq.customerMS.common.log.LogHelper;
import com.hq.customerMS.common.log.LogModule;
import com.hq.customerMS.common.log.MainLog;
import com.hq.customerMS.service.common.ReqResult;
import com.hq.customerMS.service.common.RespStatus;
import com.hq.storeClient.service.storeClerkInfo.data.StoreClerkInfo;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreClerkInfoHandler {

	public static StoreClerkInfoHandler getInstance() {
		return HotSwap.getInstance().getSingleton(StoreClerkInfoHandler.class);
	}

	private final LogModule logModule = LogModule.StoreClerkInfo;
	
	public ReqResult<StoreClerkInfo> get(String id) {
		ReqResult<StoreClerkInfo> result = ReqResult.newInstance(false, StoreClerkInfo.class);
		try {
			StoreClerkInfo storeClerkInfo = StoreClerkInfoMgr.getInstance().get(id);
			result.setTarget(storeClerkInfo);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(id);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(logModule, "StoreClerkInfoHandler[get]", reason, e);
		}
		return result;
	}
}

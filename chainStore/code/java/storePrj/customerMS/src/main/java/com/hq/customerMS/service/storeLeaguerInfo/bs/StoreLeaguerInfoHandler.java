package com.hq.customerMS.service.storeLeaguerInfo.bs;

import com.hq.customerMS.common.log.LogHelper;
import com.hq.customerMS.common.log.LogModule;
import com.hq.customerMS.common.log.MainLog;
import com.hq.customerMS.service.common.ReqResult;
import com.hq.customerMS.service.common.RespStatus;
import com.hq.storeClient.service.storeLeaguerInfo.data.StoreLeaguerInfo;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreLeaguerInfoHandler {

	public static StoreLeaguerInfoHandler getInstance() {
		return HotSwap.getInstance().getSingleton(StoreLeaguerInfoHandler.class);
	}

	private final LogModule logModule = LogModule.StoreLeaguerInfo;

	public ReqResult<StoreLeaguerInfo> get(long storeId) {
		ReqResult<StoreLeaguerInfo> result = ReqResult.newInstance(false, StoreLeaguerInfo.class);
		try {
			StoreLeaguerInfo storeLeaguerInfo = StoreLeaguerInfoMgr.getInstance().getByStoreId(storeId);
			result.setTarget(storeLeaguerInfo);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(storeId);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(logModule, "StoreLeaguerInfoHandler[get]", reason, e);
		}
		return result;
	}
}

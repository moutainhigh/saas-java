package com.hq.customerMS.service.storeCardInfo.bs;

import com.hq.customerMS.common.log.LogHelper;
import com.hq.customerMS.common.log.LogModule;
import com.hq.customerMS.common.log.MainLog;
import com.hq.customerMS.service.common.ReqResult;
import com.hq.customerMS.service.common.RespStatus;
import com.hq.storeClient.service.storeCardInfo.data.StoreCardInfo;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreCardInfoHandler {

	public static StoreCardInfoHandler getInstance() {
		return HotSwap.getInstance().getSingleton(StoreCardInfoHandler.class);
	}

	private final LogModule logModule = LogModule.StoreCardInfo;

	public ReqResult<StoreCardInfo> getStoreCardInfo(long storeId) {
		ReqResult<StoreCardInfo> result = ReqResult.newInstance(false, StoreCardInfo.class);
		try {
			StoreCardInfo info = StoreCardInfoMgr.getInstance().getStoreCardInfo(storeId);
			result.setTarget(info);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(storeId);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(logModule, "StoreCardInfoHandler[get]", reason, e);
		}
		return result;
	}

}

package com.hq.customerMS.service.storeProductInfo.bs;

import com.hq.customerMS.common.log.LogHelper;
import com.hq.customerMS.common.log.LogModule;
import com.hq.customerMS.common.log.MainLog;
import com.hq.customerMS.service.common.ReqResult;
import com.hq.customerMS.service.common.RespStatus;
import com.hq.storeClient.service.storeProductInfo.data.StoreProductInfo;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreProductInfoHandler {

	public static StoreProductInfoHandler getInstance() {
		return HotSwap.getInstance().getSingleton(StoreProductInfoHandler.class);
	}

	public ReqResult<StoreProductInfo> getStoreProductInfo(long storeId){
		ReqResult<StoreProductInfo> result = ReqResult.newInstance(false, StoreProductInfo.class);
		try {
			StoreProductInfo info = StoreProductInfoMgr.getInstance().get(storeId);
			result.setSuccess(true);
			result.setTarget(info);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(storeId);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(LogModule.StoreProductInfo, "StoreProductInfoHandler[getStoreProductInfo]", reason, e);
		}
		return result;
	}
}

package com.hq.customerMS.service.storeBeauticianInfo.bs;

import com.hq.customerMS.common.log.LogHelper;
import com.hq.customerMS.common.log.LogModule;
import com.hq.customerMS.common.log.MainLog;
import com.hq.customerMS.service.common.ReqResult;
import com.hq.customerMS.service.common.RespStatus;
import com.hq.storeClient.service.storeBeauticianInfo.data.StoreBeauticianInfo;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreBeauticianInfoHandler {

	public static StoreBeauticianInfoHandler getInstance() {
		return HotSwap.getInstance().getSingleton(StoreBeauticianInfoHandler.class);
	}
	
	public ReqResult<StoreBeauticianInfo> getStoreBeauticianInfo(long storeId){
		ReqResult<StoreBeauticianInfo> result = ReqResult.newInstance(false, StoreBeauticianInfo.class);
		try {
			StoreBeauticianInfo info = StoreBeauticianInfoMgr.getInstance().get(storeId);
			result.setSuccess(true);
			result.setTarget(info);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(storeId);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(LogModule.StoreBeauticianInfo, "StoreBeauticianInfoHandler[getStoreBeauticianInfo]", reason, e);
		}
		return result;
	}
	
}

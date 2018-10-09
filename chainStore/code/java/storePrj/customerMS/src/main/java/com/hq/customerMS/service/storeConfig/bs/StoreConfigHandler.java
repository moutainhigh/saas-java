package com.hq.customerMS.service.storeConfig.bs;

import com.hq.customerMS.common.log.LogHelper;
import com.hq.customerMS.common.log.LogModule;
import com.hq.customerMS.service.common.ExceptionInfo;
import com.hq.customerMS.service.common.HandlerHelper;
import com.hq.customerMS.service.common.ReqResult;
import com.hq.storeClient.service.storeConfig.data.StoreConfig;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreConfigHandler {

	public static StoreConfigHandler getInstance() {
		return HotSwap.getInstance().getSingleton(StoreConfigHandler.class);
	}
	
	private final LogModule logModule = LogModule.StoreConfig;
	
	public ReqResult<StoreConfig> getByStoreId(long storeId) {
		ReqResult<StoreConfig> result = ReqResult.newInstance(false, StoreConfig.class);
		try {
			StoreConfig storeConfig = StoreConfigMgr.getInstance().getByStoreId(storeId);
			result.setTarget(storeConfig);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "StoreConfigHandler[getByStoreId]";
			final String reason = LogHelper.getInstance().exceptionReason(storeId);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}

}

package com.hq.storeManagerMS.service.storeMenu.bs;

import com.hq.storeClient.service.storeMenu.data.StoreMenu;
import com.hq.storeManagerMS.common.log.LogModule;
import com.hq.storeManagerMS.service.common.ExceptionInfo;
import com.hq.storeManagerMS.service.common.HandlerHelper;
import com.hq.storeManagerMS.service.common.ReqResult;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreMenuHandler {

	public static StoreMenuHandler getInstance() {
		return HotSwap.getInstance().getSingleton(StoreMenuHandler.class);
	}

	private final LogModule logModule = LogModule.StoreMenu;

	public ReqResult<StoreMenu> getStoreMenu() {
		ReqResult<StoreMenu> result = ReqResult.newInstance(false, StoreMenu.class);
		try {
			StoreMenu storeMenu = StoreMenuMgr.getInstance().get();
			result.setTarget(storeMenu);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "StoreMenuHandler[getStoreMenu]";
			final String reason = "";
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule).withLogId(logId)
					.withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
}

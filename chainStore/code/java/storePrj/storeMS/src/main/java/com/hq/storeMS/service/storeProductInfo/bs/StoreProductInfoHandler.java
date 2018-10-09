package com.hq.storeMS.service.storeProductInfo.bs;

import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.service.common.ExceptionInfo;
import com.hq.storeMS.service.common.HandlerHelper;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RespStatus;
import com.hq.storeMS.service.storeProductInfo.apiData.StoreProductInfoUpdateForm;
import com.hq.storeMS.service.storeProductInfo.bs.update.ProductHandlerHelper;
import com.hq.storeMS.service.storeProductInfo.data.StoreProductInfo;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreProductInfoHandler {

	public static StoreProductInfoHandler getInstance() {
		return HotSwap.getInstance().getSingleton(StoreProductInfoHandler.class);
	}

	public ReqResult<StoreProductInfo> findSimpleStoreInfo(long storeId) {
		ReqResult<StoreProductInfo> result = ReqResult.newInstance(false, StoreProductInfo.class);
		try {
			StoreProductInfo storeProductInfo = StoreProductInfoMgr.getInstance().getByStoreId(storeId);
			result.setTarget(storeProductInfo);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(storeId);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(LogModule.StoreProductInfo, "StoreProductInfoHandler[findSimpleStoreInfo]", reason, e);
		}
		return result;
	}

	public ReqResult<StoreProductInfo> update(StoreProductInfoUpdateForm formInfo) {
		ReqResult<StoreProductInfo> result = ReqResult.newInstance(false, StoreProductInfo.class);
		try {
			OperateTips operateTips = ProductHandlerHelper.getInstance().update(formInfo);
			if (operateTips.isSuccess()) {
				result.setSuccess(true);
				result.setTips(operateTips.getTips());
			} else {
				HandlerHelper.getInstance().handleReqResult(result, operateTips);
			}
		} catch (Exception e) {
			final LogModule logModule = LogModule.StoreProductInfo;
			final String logId = "StoreProductInfoHandler[update]";
			final String reason = LogHelper.getInstance().exceptionReason(formInfo);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule).withLogId(logId)
					.withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
}

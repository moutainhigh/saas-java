package com.hq.storeMS.service.storeLeaguerInfo.bs;

import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.service.common.ExceptionInfo;
import com.hq.storeMS.service.common.HandlerHelper;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.storeLeaguerInfo.apiData.StoreLeaguerInfoUpdateApiForm;
import com.hq.storeMS.service.storeLeaguerInfo.bs.update.LeaguerHandlerHelper;
import com.hq.storeMS.service.storeLeaguerInfo.data.StoreLeaguerInfo;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreLeaguerInfoHandler {

	public static StoreLeaguerInfoHandler getInstance() {
		return HotSwap.getInstance().getSingleton(StoreLeaguerInfoHandler.class);
	}

	private final LogModule logModule = LogModule.Leaguer;

	public ReqResult<StoreLeaguerInfo> findSimpleStoreInfo(long storeId) {
		ReqResult<StoreLeaguerInfo> result = ReqResult.newInstance(false, StoreLeaguerInfo.class);
		try {
//			BUserAuthUtils.getInstance().checkStoreClerk(storeId);
			StoreLeaguerInfo info = StoreLeaguerInfoMgr.getInstance().get(storeId);
			result.setTarget(info);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "StoreLeaguerInfoHandler[getSimpleStoreInfo]";
			final String reason = LogHelper.getInstance().exceptionReason(storeId);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance()
					.withLogModule(logModule).withLogId(logId)
					.withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<StoreLeaguerInfo> updateStoreLeaguerInfo(StoreLeaguerInfoUpdateApiForm inputForm){
		ReqResult<StoreLeaguerInfo> result = ReqResult.newInstance(false, StoreLeaguerInfo.class);
		try {
			OperateTips operateTips = LeaguerHandlerHelper.getInstance().update(inputForm);
			if (operateTips.isSuccess()) {
				result.setSuccess(true);
				result.setTips(operateTips.getTips());
			} else {
				HandlerHelper.getInstance().handleReqResult(result, operateTips);
			}
		} catch (Exception e) {
			final String logId = "StoreLeaguerInfoHandler[updateStoreLeaguerInfo]";
			final String reason = LogHelper.getInstance().exceptionReason(inputForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance()
					.withLogModule(logModule).withLogId(logId)
					.withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
}

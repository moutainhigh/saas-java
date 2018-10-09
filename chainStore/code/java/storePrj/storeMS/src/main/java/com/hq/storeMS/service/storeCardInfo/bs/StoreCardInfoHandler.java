package com.hq.storeMS.service.storeCardInfo.bs;

import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.service.auth.buser.BUserAuthUtils;
import com.hq.storeMS.service.common.ExceptionInfo;
import com.hq.storeMS.service.common.HandlerHelper;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RespStatus;
import com.hq.storeMS.service.storeCardInfo.apiData.StoreCardInfoUpdateApiForm;
import com.hq.storeMS.service.storeCardInfo.bs.update.CardUpdateHandlerHelper;
import com.hq.storeMS.service.storeCardInfo.data.StoreCardInfo;
import com.hq.storeMS.service.storeClerkInfo.data.adminRole.StoreAdminPermEnum;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreCardInfoHandler {

	public static StoreCardInfoHandler getInstance() {
		return HotSwap.getInstance().getSingleton(StoreCardInfoHandler.class);
	}

	private final LogModule logModule = LogModule.StoreCardInfo;

	public ReqResult<StoreCardInfo> getStoreCardInfoSimple(long storeId) {
		ReqResult<StoreCardInfo> result = ReqResult.newInstance(false, StoreCardInfo.class);
		try {
			StoreCardInfo info = StoreCardInfoMgr.getInstance().getByStoreId(storeId);
			result.setTarget(info);
			result.setSuccess(true);
		} catch (Exception e) {
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(logModule, "StoreCardInfoHandler[getStoreCardInfoSimple]", "", e);
		}
		
		return result;
	}

	public ReqResult<StoreCardInfo> updateStoreCardInfo(long storeId, StoreCardInfoUpdateApiForm updateForm) {
		BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.CARD_ADMIN);
		ReqResult<StoreCardInfo> result = ReqResult.newInstance(false, StoreCardInfo.class);
		try {
			OperateTips operateTips = CardUpdateHandlerHelper.getInstance().update(storeId, updateForm);
			if (operateTips.isSuccess()) {
				result.setSuccess(true);
			} else {
				HandlerHelper.getInstance().handleReqResult(result, operateTips);
			}
		} catch (Exception e) {
			final String logId = "StoreCardInfoHandler[update]";
			final String reason = LogHelper.getInstance().exceptionReason(storeId, updateForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance()
					.withLogModule(logModule).withLogId(logId)
					.withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	
}

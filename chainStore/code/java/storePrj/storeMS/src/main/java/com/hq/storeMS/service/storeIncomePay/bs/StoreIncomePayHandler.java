package com.hq.storeMS.service.storeIncomePay.bs;

import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.service.common.ExceptionInfo;
import com.hq.storeMS.service.common.HandlerHelper;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RespStatus;
import com.hq.storeMS.service.storeIncomePay.apiData.StoreIncomePayUpdateForm;
import com.hq.storeMS.service.storeIncomePay.bs.updateHandle.IncomePayHandlerHelper;
import com.hq.storeMS.service.storeIncomePay.data.StoreIncomePay;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreIncomePayHandler {

	public static StoreIncomePayHandler getInstance() {
		return HotSwap.getInstance().getSingleton(StoreIncomePayHandler.class);
	}
	
	private final LogModule logModule = LogModule.StoreIncomePay;

	public ReqResult<StoreIncomePay> get(long storeId) {
		ReqResult<StoreIncomePay> result = ReqResult.newInstance(false, StoreIncomePay.class);
		try {
			StoreIncomePay storeIncomePay = StoreIncomePayMgr.getInstance().getByStoreId(storeId);
			result.setTarget(storeIncomePay);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(storeId);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(LogModule.StoreIncomePay, "StoreIncomePayHandler[get]", reason, e);
		}
		return result;
	}
	
	public ReqResult<StoreIncomePay> update(long storeId, StoreIncomePayUpdateForm formInfo) {
		ReqResult<StoreIncomePay> result = ReqResult.newInstance(false, StoreIncomePay.class);
		try {
			OperateTips operateTips = IncomePayHandlerHelper.getInstance().update(storeId, formInfo);
			if (operateTips.isSuccess()) {
				result.setSuccess(true);
				result.setTips(operateTips.getTips());
			} else {
				HandlerHelper.getInstance().handleReqResult(result, operateTips);
			}
		} catch (Exception e) {
			final String logId = "StoreIncomePayHandler[update]";
			final String reason = LogHelper.getInstance().exceptionReason(storeId);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance()
					.withLogModule(logModule).withLogId(logId)
					.withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}



}

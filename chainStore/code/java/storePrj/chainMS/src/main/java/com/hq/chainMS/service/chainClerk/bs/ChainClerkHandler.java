package com.hq.chainMS.service.chainClerk.bs;

import com.hq.chainMS.common.log.LogHelper;
import com.hq.chainMS.common.log.LogModule;
import com.hq.chainMS.service.chainClerk.apiData.ChainClerkUpdateForm;
import com.hq.chainMS.service.chainClerk.bs.updateHandle.ClerkInfoHandleHelper;
import com.hq.chainMS.service.chainClerk.data.ChainClerk;
import com.hq.chainMS.service.common.ExceptionInfo;
import com.hq.chainMS.service.common.HandlerHelper;
import com.hq.chainMS.service.common.OperateTips;
import com.hq.chainMS.service.common.ReqResult;
import com.zenmind.common.hotSwap.HotSwap;

public class ChainClerkHandler {

	public static ChainClerkHandler getInstance() {
		return HotSwap.getInstance().getSingleton(ChainClerkHandler.class);
	}

	public ReqResult<ChainClerk> get(long chainId) {
		ReqResult<ChainClerk> result = ReqResult.newInstance(false, ChainClerk.class);
		try {
			ChainClerk chainClerk = ChainClerkMgr.getInstance().get(chainId);
			result.setTarget(chainClerk);
			result.setSuccess(true);
		} catch (Exception e) {
			final LogModule logModule = LogModule.ChainClerk;
			final String logId = "ChainClerkHandler[get]";
			final String reason = LogHelper.getInstance().exceptionReason(chainId);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}

	public ReqResult<ChainClerk> update(long chainId, ChainClerkUpdateForm formInfo) {
		ReqResult<ChainClerk> result = ReqResult.newInstance(false, ChainClerk.class);
		try {
			OperateTips operateTips = ClerkInfoHandleHelper.getInstance().update(chainId, formInfo);
			if (operateTips.isSuccess()) {
				result.setSuccess(true);
			} else {
				HandlerHelper.getInstance().handleReqResult(result, operateTips);
			}
		} catch (Exception e) {
			final LogModule logModule = LogModule.ChainClerk;
			final String logId = "ChainClerkHandler[update]";
			final String reason = LogHelper.getInstance().exceptionReason(formInfo);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
}

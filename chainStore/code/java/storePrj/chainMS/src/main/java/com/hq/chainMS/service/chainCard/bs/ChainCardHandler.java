package com.hq.chainMS.service.chainCard.bs;

import com.hq.chainMS.common.log.LogHelper;
import com.hq.chainMS.common.log.LogModule;
import com.hq.chainMS.common.log.MainLog;
import com.hq.chainMS.service.chainCard.apiData.ChainCardUpdateApiForm;
import com.hq.chainMS.service.chainCard.bs.updateHandle.CardInfoHandleHelper;
import com.hq.chainMS.service.chainCard.data.ChainCard;
import com.hq.chainMS.service.common.ExceptionInfo;
import com.hq.chainMS.service.common.HandlerHelper;
import com.hq.chainMS.service.common.OperateTips;
import com.hq.chainMS.service.common.ReqResult;
import com.hq.chainMS.service.common.RespStatus;
import com.zenmind.common.hotSwap.HotSwap;

public class ChainCardHandler {

	public static ChainCardHandler getInstance() {
		return HotSwap.getInstance().getSingleton(ChainCardHandler.class);
	}

	private final LogModule logModule = LogModule.ChainCard;

	public ReqResult<ChainCard> getChainCard(long chainId) {
		ReqResult<ChainCard> result = ReqResult.newInstance(false, ChainCard.class);
		try {
			ChainCard info = ChainCardMgr.getInstance().get(chainId);
			result.setTarget(info);
			result.setSuccess(true);
		} catch (Exception e) {
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(logModule, "ChainCardHandler[getChainCardSimple]", "", e);
		}
		
		return result;
	}

	public ReqResult<ChainCard> updateChainCard(long chainId, ChainCardUpdateApiForm updateForm) {
		ReqResult<ChainCard> result = ReqResult.newInstance(false, ChainCard.class);
		try {
			OperateTips operateTips = CardInfoHandleHelper.getInstance().update(chainId, updateForm);
			if (operateTips.isSuccess()) {
				result.setSuccess(true);
			} else {
				HandlerHelper.getInstance().handleReqResult(result, operateTips);
			}
		} catch (Exception e) {
			final String logId = "ChainCardHandler[update]";
			final String reason = LogHelper.getInstance().exceptionReason(chainId, updateForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance()
					.withLogModule(logModule).withLogId(logId)
					.withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
}

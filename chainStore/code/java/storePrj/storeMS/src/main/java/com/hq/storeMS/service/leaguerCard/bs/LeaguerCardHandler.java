package com.hq.storeMS.service.leaguerCard.bs;

import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.service.common.ExceptionInfo;
import com.hq.storeMS.service.common.HandlerHelper;
import com.hq.storeMS.service.common.PageResp;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.leaguerCard.apiData.LeaguerCardQueryForm;
import com.hq.storeMS.service.leaguerCard.data.LeaguerCard;
import com.zenmind.common.hotSwap.HotSwap;

public class LeaguerCardHandler {

	public static LeaguerCardHandler getInstance() {
		return HotSwap.getInstance().getSingleton(LeaguerCardHandler.class);
	}

	private final LogModule logModule = LogModule.LeaguerCard;

	@SuppressWarnings("rawtypes")
	public ReqResult<PageResp> getExpiredCardPageInfo(LeaguerCardQueryForm queryForm) {
		ReqResult<PageResp> result = ReqResult.newInstance(false, PageResp.class);
		try {
			PageResp<LeaguerCard> pageResp = LeaguerCardMgr.getInstance().getExpiredCardPageInfo(queryForm);
			result.setTarget(pageResp);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "LeaguerCardHandler[getExpiredCardPageInfo]";
			final String reason = LogHelper.getInstance().exceptionReason(queryForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule).withLogId(logId)
					.withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}

}

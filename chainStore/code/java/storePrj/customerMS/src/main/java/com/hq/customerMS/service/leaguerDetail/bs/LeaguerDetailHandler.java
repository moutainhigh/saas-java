package com.hq.customerMS.service.leaguerDetail.bs;

import com.hq.customerMS.common.log.LogHelper;
import com.hq.customerMS.common.log.LogModule;
import com.hq.customerMS.common.log.MainLog;
import com.hq.customerMS.service.common.ReqResult;
import com.hq.customerMS.service.common.RespStatus;
import com.hq.storeClient.service.leaguerDetail.data.LeaguerDetail;
import com.zenmind.common.hotSwap.HotSwap;

public class LeaguerDetailHandler {

	public static LeaguerDetailHandler getInstance() {
		return HotSwap.getInstance().getSingleton(LeaguerDetailHandler.class);
	}

	private final LogModule logModule = LogModule.LeaguerDetail;

	public ReqResult<LeaguerDetail> getLeaguerDetail(long storeId, String leaguerDetailId) {
		ReqResult<LeaguerDetail> result = ReqResult.newInstance(false, LeaguerDetail.class);
		try {
			LeaguerDetail leaguerDetail = LeaguerDetailMgr.getInstance().get(storeId, leaguerDetailId);
			result.setTarget(leaguerDetail);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(storeId, leaguerDetailId);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(logModule, "LeaguerDetailHandler[getLeaguerDetail]", reason, e);
		}
		return result;
	}
}

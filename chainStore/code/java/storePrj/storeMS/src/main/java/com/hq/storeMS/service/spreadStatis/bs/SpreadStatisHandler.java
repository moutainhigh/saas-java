package com.hq.storeMS.service.spreadStatis.bs;

import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.service.common.ExceptionInfo;
import com.hq.storeMS.service.common.HandlerHelper;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.spreadStatis.apiData.SpreadStatisQueryForm;
import com.hq.storeMS.service.spreadStatis.data.vo.BuserSpreadStatis;
import com.hq.storeMS.service.spreadStatis.data.vo.StoreSpreadStatis;
import com.zenmind.common.hotSwap.HotSwap;

public class SpreadStatisHandler {

	public static SpreadStatisHandler getInstance() {
		return HotSwap.getInstance().getSingleton(SpreadStatisHandler.class);
	}

	private final LogModule logModule = LogModule.SpreadStatis;

	public ReqResult<BuserSpreadStatis> findBuserSpreadStatis(SpreadStatisQueryForm queryForm) {
		ReqResult<BuserSpreadStatis> result = ReqResult.newInstance(false, BuserSpreadStatis.class);
		try {
			BuserSpreadStatis target = SpreadStatisQueryMgr.getInstance().findBuserSpreadStatis(queryForm);
			result.setTarget(target);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "SpreadStatisHandler[findBuserSpreadStatis]";
			final String reason = LogHelper.getInstance().exceptionReason(queryForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule).withLogId(logId)
					.withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}

	public ReqResult<StoreSpreadStatis> findStoreSpreadStatis(SpreadStatisQueryForm queryForm) {
		ReqResult<StoreSpreadStatis> result = ReqResult.newInstance(false, StoreSpreadStatis.class);
		try {
			StoreSpreadStatis target = SpreadStatisQueryMgr.getInstance().findStoreSpreadStatis(queryForm.getStoreId(),
					queryForm.getMaxTime(), queryForm.getMinTime());
			result.setTarget(target);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "SpreadStatisHandler[findStoreSpreadStatis]";
			final String reason = LogHelper.getInstance().exceptionReason(queryForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule).withLogId(logId)
					.withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
}

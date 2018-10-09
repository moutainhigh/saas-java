package com.hq.storeMS.service.footprint.bs;

import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.service.common.ExceptionInfo;
import com.hq.storeMS.service.common.HandlerHelper;
import com.hq.storeMS.service.common.PageResp;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.footprint.apiData.FootprintAddForm;
import com.hq.storeMS.service.footprint.apiData.FootprintQueryForm;
import com.hq.storeMS.service.footprint.data.Footprint;
import com.zenmind.common.hotSwap.HotSwap;

public class FootprintHandler {

	public static FootprintHandler getInstance() {
		return HotSwap.getInstance().getSingleton(FootprintHandler.class);
	}
	
	private final LogModule logModule = LogModule.Footprint;
	
	@SuppressWarnings("rawtypes")
	public ReqResult<PageResp> findPage(FootprintQueryForm queryForm) {
		ReqResult<PageResp> result = ReqResult.newInstance(false, PageResp.class);
		try {
			PageResp<Footprint> page = FootprintQueryMgr.getInstance().findPage(queryForm);
			result.setTarget(page);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "FootprintHandler[findPage]";
			final String reason = LogHelper.getInstance().exceptionReason(queryForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<Footprint> get(long footprintId) {
		ReqResult<Footprint> result = ReqResult.newInstance(false, Footprint.class);
		try {
			Footprint footprint = FootprintQueryMgr.getInstance().get(footprintId);
			result.setTarget(footprint);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "FootprintHandler[get]";
			final String reason = LogHelper.getInstance().exceptionReason(footprintId);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<Footprint> add(FootprintAddForm inputForm) {
		ReqResult<Footprint> result = ReqResult.newInstance(false, Footprint.class);
		try {
			Footprint target = FootprintModifyMgr.getInstance().addByForm(inputForm);
			result.setTarget(target);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "FootprintHandler[add]";
			final String reason = LogHelper.getInstance().exceptionReason(inputForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
}

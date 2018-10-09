package com.hq.storeMS.service.dynamic.bs;

import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.service.common.ExceptionInfo;
import com.hq.storeMS.service.common.HandlerHelper;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.common.PageResp;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.dynamic.apiData.DynamicAddForm;
import com.hq.storeMS.service.dynamic.apiData.DynamicQueryForm;
import com.hq.storeMS.service.dynamic.apiData.DynamicQueryFormForCuser;
import com.hq.storeMS.service.dynamic.apiData.DynamicUpdateApiForm;
import com.hq.storeMS.service.dynamic.apiData.DynamicUpdateType;
import com.hq.storeMS.service.dynamic.data.Dynamic;
import com.zenmind.common.hotSwap.HotSwap;

public class DynamicHandler {

	public static DynamicHandler getInstance() {
		return HotSwap.getInstance().getSingleton(DynamicHandler.class);
	}
	
	private final LogModule logModule = LogModule.Dynamic;
	
	@SuppressWarnings("rawtypes")
	public ReqResult<PageResp> findPage(DynamicQueryForm queryForm) {
		ReqResult<PageResp> result = ReqResult.newInstance(false, PageResp.class);
		try {
			PageResp<Dynamic> page = DynamicQueryMgr.getInstance().findPage(queryForm);
			result.setTarget(page);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "DynamicHandler[findPage]";
			final String reason = LogHelper.getInstance().exceptionReason(queryForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	@SuppressWarnings("rawtypes")
	public ReqResult<PageResp> findPageForCuser(DynamicQueryFormForCuser queryForm) {
		ReqResult<PageResp> result = ReqResult.newInstance(false, PageResp.class);
		try {
			PageResp<Dynamic> page = DynamicQueryMgr.getInstance().findPageForCuser(queryForm);
			result.setTarget(page);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "DynamicHandler[findPageForCuser]";
			final String reason = LogHelper.getInstance().exceptionReason(queryForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<Dynamic> get(long dynamicId) {
		ReqResult<Dynamic> result = ReqResult.newInstance(false, Dynamic.class);
		try {
			Dynamic dynamic = DynamicQueryMgr.getInstance().get(dynamicId);
			result.setTarget(dynamic);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "DynamicHandler[get]";
			final String reason = LogHelper.getInstance().exceptionReason(dynamicId);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<Dynamic> update(long dynamicId, DynamicUpdateApiForm inputForm) {
		ReqResult<Dynamic> result = ReqResult.newInstance(false, Dynamic.class);
		try {
			DynamicUpdateType updateType = inputForm.getDynamicUpdateType();
			OperateTips operateTips = OperateTips.newInstance(false, "修改动态失败");
			switch (updateType) {
			case UpdateDynamicInfo:
				operateTips = DynamicModifyMgr.getInstance().updateDynamicInfo(dynamicId, inputForm.getUpdateInfoForm());
				break;
			case UpdateDynamicStatus:
				operateTips = DynamicModifyMgr.getInstance().updateDynamicStatus(dynamicId, inputForm.getUpdateStatusForm());
				break;
			default:
				break;
			}
			if (operateTips.isSuccess()) {
				Dynamic target = DynamicQueryMgr.getInstance().get(dynamicId);
				result.setSuccess(true);
				result.setTarget(target);
				result.setTips("操作成功");
			} else {
				HandlerHelper.getInstance().handleReqResult(result, operateTips);
			}
		} catch (Exception e) {
			final String logId = "DynamicHandler[update]";
			final String reason = LogHelper.getInstance().exceptionReason(dynamicId, inputForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<Dynamic> add(DynamicAddForm inputForm) {
		ReqResult<Dynamic> result = ReqResult.newInstance(false, Dynamic.class);
		try {
			Dynamic target = DynamicModifyMgr.getInstance().addByForm(inputForm);
			result.setTarget(target);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "DynamicHandler[add]";
			final String reason = LogHelper.getInstance().exceptionReason(inputForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
}

package com.hq.storeMS.service.workFlowData.bs;

import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.service.common.ExceptionInfo;
import com.hq.storeMS.service.common.HandlerHelper;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.workFlowData.apiData.MemCardInfoForm;
import com.hq.storeMS.service.workFlowData.data.WorkFlowData;
import com.hq.storeMS.service.workFlowData.data.WorkFlowDataBeanHelper;
import com.zenmind.common.hotSwap.HotSwap;

public class WorkFlowDataMemCardInfoHandler {

	public static WorkFlowDataMemCardInfoHandler getInstance() {
		return HotSwap.getInstance().getSingleton(WorkFlowDataMemCardInfoHandler.class);
	}
	
	private final LogModule logModule = LogModule.WorkFlowData;
	
	public ReqResult<WorkFlowData> update(long workFlowDataId, String memTypeId, MemCardInfoForm inputForm) {
		ReqResult<WorkFlowData> result = ReqResult.newInstance(false, WorkFlowData.class);
		try {
			WorkFlowData workFlowData = WorkFlowDataMgr.getInstance().get(workFlowDataId);
			WorkFlowDataBeanHelper.getInstance().updateMemCardInfo(workFlowData, inputForm);
			WorkFlowDataMgr.getInstance().updateWorkFlowData(workFlowData);
			result.setTarget(workFlowData);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "WorkFlowDataMemCardInfoHandler[update]";
			final String reason = LogHelper.getInstance().exceptionReason(workFlowDataId, memTypeId, inputForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<WorkFlowData> add(long workFlowDataId, MemCardInfoForm inputForm) {
		ReqResult<WorkFlowData> result = ReqResult.newInstance(false, WorkFlowData.class);
		try {
			WorkFlowData workFlowData = WorkFlowDataMgr.getInstance().get(workFlowDataId);
			WorkFlowDataBeanHelper.getInstance().updateMemCardInfo(workFlowData, inputForm);
			WorkFlowDataMgr.getInstance().updateWorkFlowData(workFlowData);
			result.setTarget(workFlowData);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "WorkFlowDataMemCardInfoHandler[add]";
			final String reason = LogHelper.getInstance().exceptionReason(workFlowDataId, inputForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
}

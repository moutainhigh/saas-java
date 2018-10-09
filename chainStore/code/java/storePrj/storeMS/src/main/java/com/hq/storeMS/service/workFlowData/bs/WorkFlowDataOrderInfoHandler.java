package com.hq.storeMS.service.workFlowData.bs;

import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.service.common.ExceptionInfo;
import com.hq.storeMS.service.common.HandlerHelper;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RespStatus;
import com.hq.storeMS.service.workFlowData.apiData.OrderInfoForm;
import com.hq.storeMS.service.workFlowData.data.WorkFlowData;
import com.hq.storeMS.service.workFlowData.data.WorkFlowDataBeanHelper;
import com.zenmind.common.hotSwap.HotSwap;

public class WorkFlowDataOrderInfoHandler {

	public static WorkFlowDataOrderInfoHandler getInstance() {
		return HotSwap.getInstance().getSingleton(WorkFlowDataOrderInfoHandler.class);
	}
	
	private final LogModule logModule = LogModule.WorkFlowData;
	
	public ReqResult<WorkFlowData> update(long workFlowDataId, long orderId, OrderInfoForm inputForm) {
		ReqResult<WorkFlowData> result = ReqResult.newInstance(false, WorkFlowData.class);
		try {
			WorkFlowData workFlowData = WorkFlowDataMgr.getInstance().get(workFlowDataId);
			if(WorkFlowDataBeanHelper.getInstance().updateOrderInfo(workFlowData, inputForm)) {
				WorkFlowDataMgr.getInstance().updateWorkFlowData(workFlowData);
				result.setTarget(workFlowData);
				result.setSuccess(true);
			}else {
				result.setTips("记录不存在");
				result.setStatus(RespStatus.INVALID_REQUEST);
			}
		} catch (Exception e) {
			final String logId = "WorkFlowDataOrderInfoHandler[update]";
			final String reason = LogHelper.getInstance().exceptionReason(workFlowDataId, orderId, inputForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<WorkFlowData> add(long workFlowDataId, OrderInfoForm inputForm) {
		ReqResult<WorkFlowData> result = ReqResult.newInstance(false, WorkFlowData.class);
		try {
			WorkFlowData workFlowData = WorkFlowDataMgr.getInstance().get(workFlowDataId);
			if(WorkFlowDataBeanHelper.getInstance().updateOrderInfo(workFlowData, inputForm)) {
				WorkFlowDataMgr.getInstance().updateWorkFlowData(workFlowData);
				result.setTarget(workFlowData);
				result.setSuccess(true);
			}else {
				result.setTips("记录不存在");
				result.setStatus(RespStatus.INVALID_REQUEST);
			}
		} catch (Exception e) {
			final String logId = "WorkFlowDataOrderInfoHandler[add]";
			final String reason = LogHelper.getInstance().exceptionReason(workFlowDataId, inputForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
}

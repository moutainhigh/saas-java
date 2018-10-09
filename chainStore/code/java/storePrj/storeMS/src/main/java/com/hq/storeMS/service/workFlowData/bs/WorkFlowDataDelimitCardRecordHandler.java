package com.hq.storeMS.service.workFlowData.bs;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.service.common.ExceptionInfo;
import com.hq.storeMS.service.common.HandlerHelper;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RespStatus;
import com.hq.storeMS.service.workFlowData.apiData.DelimitCardRecordAddForm;
import com.hq.storeMS.service.workFlowData.apiData.DelimitCardRecordAddListForm;
import com.hq.storeMS.service.workFlowData.apiData.DelimitCardRecordUpdListForm;
import com.hq.storeMS.service.workFlowData.apiData.DelimitCardRecordUpdateForm;
import com.hq.storeMS.service.workFlowData.data.WorkFlowData;
import com.hq.storeMS.service.workFlowData.data.WorkFlowDataBeanHelper;
import com.zenmind.common.hotSwap.HotSwap;

public class WorkFlowDataDelimitCardRecordHandler {

	public static WorkFlowDataDelimitCardRecordHandler getInstance() {
		return HotSwap.getInstance().getSingleton(WorkFlowDataDelimitCardRecordHandler.class);
	}
	
	private final LogModule logModule = LogModule.WorkFlowData;
	
	public ReqResult<WorkFlowData> update(long workFlowDataId, String delimitCardId, DelimitCardRecordUpdateForm inputForm) {
		ReqResult<WorkFlowData> result = ReqResult.newInstance(false, WorkFlowData.class);
		try {
			WorkFlowData workFlowData = WorkFlowDataMgr.getInstance().get(workFlowDataId);
			if(WorkFlowDataBeanHelper.getInstance().updateDelimitCardRecord(workFlowData, inputForm)){
				WorkFlowDataMgr.getInstance().updateWorkFlowData(workFlowData);
				result.setTarget(workFlowData);
				result.setSuccess(true);
			}else{
				result.setStatus(RespStatus.INVALID_REQUEST);
				result.setTips("更新划卡信息失败");
			}
		} catch (Exception e) {
			final String logId = "WorkFlowDataDelimitCardRecordHandler[update]";
			final String reason = LogHelper.getInstance().exceptionReason(workFlowDataId, delimitCardId, inputForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<WorkFlowData> delete(long workFlowDataId, String delimitCardId) {
		ReqResult<WorkFlowData> result = ReqResult.newInstance(false, WorkFlowData.class);
		try {
			WorkFlowData workFlowData = WorkFlowDataMgr.getInstance().get(workFlowDataId);
			if(WorkFlowDataBeanHelper.getInstance().deleteDelimitCardRecord(workFlowData, delimitCardId)){
				WorkFlowDataMgr.getInstance().updateWorkFlowData(workFlowData);
				result.setTarget(workFlowData);
				result.setSuccess(true);
			}else{
				result.setStatus(RespStatus.INVALID_REQUEST);
				result.setTips("删除划卡信息失败");
			}
		} catch (Exception e) {
			final String logId = "WorkFlowDataDelimitCardRecordHandler[delete]";
			final String reason = LogHelper.getInstance().exceptionReason(workFlowDataId, delimitCardId);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<WorkFlowData> add(long workFlowDataId, DelimitCardRecordAddForm inputForm) {
		ReqResult<WorkFlowData> result = ReqResult.newInstance(false, WorkFlowData.class);
		try {
			WorkFlowData workFlowData = WorkFlowDataMgr.getInstance().get(workFlowDataId);
			if(WorkFlowDataBeanHelper.getInstance().addDelimitCardRecord(workFlowData, inputForm)){
				WorkFlowDataMgr.getInstance().updateWorkFlowData(workFlowData);
				result.setTarget(workFlowData);
				result.setSuccess(true);
			}else{
				result.setStatus(RespStatus.INVALID_REQUEST);
				result.setTips("划卡不能重复选择");
			}
		} catch (Exception e) {
			final String logId = "WorkFlowDataDelimitCardRecordHandler[add]";
			final String reason = LogHelper.getInstance().exceptionReason(workFlowDataId, inputForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<WorkFlowData> addList(long workFlowDataId, DelimitCardRecordAddListForm addListForm) {
		ReqResult<WorkFlowData> result = ReqResult.newInstance(false, WorkFlowData.class);
		try {
			List<DelimitCardRecordAddForm> inputForms = addListForm.getDelimitCardAddForms();
			if(CollectionUtils.isNotEmpty(inputForms)){
				WorkFlowData workFlowData = WorkFlowDataMgr.getInstance().get(workFlowDataId);
				for (DelimitCardRecordAddForm inputForm : inputForms) {
					WorkFlowDataBeanHelper.getInstance().addDelimitCardRecord(workFlowData, inputForm);
				}
				WorkFlowDataMgr.getInstance().updateWorkFlowData(workFlowData);
				result.setTarget(workFlowData);
			}
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "WorkFlowDataDelimitCardRecordHandler[addList]";
			final String reason = LogHelper.getInstance().exceptionReason(workFlowDataId, addListForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<WorkFlowData> updList(long workFlowDataId, DelimitCardRecordUpdListForm updListForm) {
		ReqResult<WorkFlowData> result = ReqResult.newInstance(false, WorkFlowData.class);
		try {
			WorkFlowData workFlowData = WorkFlowDataMgr.getInstance().get(workFlowDataId);
			workFlowData.getDelimitCardRecordMap().clear();
			List<DelimitCardRecordAddForm> inputForms = updListForm.getDelimitCardUpdateForms();
			if(CollectionUtils.isNotEmpty(inputForms)){
				for (DelimitCardRecordAddForm inputForm : inputForms) {
					WorkFlowDataBeanHelper.getInstance().addDelimitCardRecord(workFlowData, inputForm);
				}
			}
			WorkFlowDataMgr.getInstance().updateWorkFlowData(workFlowData);
			result.setTarget(workFlowData);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "WorkFlowDataDelimitCardRecordHandler[updList]";
			final String reason = LogHelper.getInstance().exceptionReason(workFlowDataId, updListForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
}

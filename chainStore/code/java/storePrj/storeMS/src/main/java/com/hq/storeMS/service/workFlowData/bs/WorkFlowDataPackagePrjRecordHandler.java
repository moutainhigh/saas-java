package com.hq.storeMS.service.workFlowData.bs;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.service.common.ExceptionInfo;
import com.hq.storeMS.service.common.HandlerHelper;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RespStatus;
import com.hq.storeMS.service.workFlowData.apiData.PackagePrjRecordAddForm;
import com.hq.storeMS.service.workFlowData.apiData.PackagePrjRecordAddListForm;
import com.hq.storeMS.service.workFlowData.apiData.PackagePrjRecordUpdListForm;
import com.hq.storeMS.service.workFlowData.apiData.PackagePrjRecordUpdateForm;
import com.hq.storeMS.service.workFlowData.data.WorkFlowData;
import com.hq.storeMS.service.workFlowData.data.WorkFlowDataBeanHelper;
import com.zenmind.common.hotSwap.HotSwap;

public class WorkFlowDataPackagePrjRecordHandler {

	public static WorkFlowDataPackagePrjRecordHandler getInstance() {
		return HotSwap.getInstance().getSingleton(WorkFlowDataPackagePrjRecordHandler.class);
	}
	
	private final LogModule logModule = LogModule.WorkFlowData;
	
	public ReqResult<WorkFlowData> update(long workFlowDataId, String packagePrjId, PackagePrjRecordUpdateForm inputForm) {
		ReqResult<WorkFlowData> result = ReqResult.newInstance(false, WorkFlowData.class);
		try {
			WorkFlowData workFlowData = WorkFlowDataMgr.getInstance().get(workFlowDataId);
			if(WorkFlowDataBeanHelper.getInstance().updatePackagePrjRecord(workFlowData, inputForm)){
				WorkFlowDataMgr.getInstance().updateWorkFlowData(workFlowData);
				result.setTarget(workFlowData);
				result.setSuccess(true);
			}else{
				result.setStatus(RespStatus.INVALID_REQUEST);
				result.setTips("更新套餐信息失败");
			}
		} catch (Exception e) {
			final String logId = "WorkFlowDataPackagePrjRecordHandler[update]";
			final String reason = LogHelper.getInstance().exceptionReason(workFlowDataId, packagePrjId, inputForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<WorkFlowData> delete(long workFlowDataId, String packagePrjId) {
		ReqResult<WorkFlowData> result = ReqResult.newInstance(false, WorkFlowData.class);
		try {
			WorkFlowData workFlowData = WorkFlowDataMgr.getInstance().get(workFlowDataId);
			if(WorkFlowDataBeanHelper.getInstance().deletePackagePrjRecord(workFlowData, packagePrjId)){
				WorkFlowDataMgr.getInstance().updateWorkFlowData(workFlowData);
				result.setTarget(workFlowData);
				result.setSuccess(true);
			}else{
				result.setStatus(RespStatus.INVALID_REQUEST);
				result.setTips("删除套餐失败");
			}
		} catch (Exception e) {
			final String logId = "WorkFlowDataPackagePrjRecordHandler[delete]";
			final String reason = LogHelper.getInstance().exceptionReason(workFlowDataId, packagePrjId);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<WorkFlowData> add(long workFlowDataId, PackagePrjRecordAddForm inputForm) {
		ReqResult<WorkFlowData> result = ReqResult.newInstance(false, WorkFlowData.class);
		try {
			WorkFlowData workFlowData = WorkFlowDataMgr.getInstance().get(workFlowDataId);
			if(WorkFlowDataBeanHelper.getInstance().addPackagePrjRecord(workFlowData, inputForm)){
				WorkFlowDataMgr.getInstance().updateWorkFlowData(workFlowData);
				result.setTarget(workFlowData);
				result.setSuccess(true);
			}else{
				result.setStatus(RespStatus.INVALID_REQUEST);
				result.setTips("套餐不能重复选择");
			}
		} catch (Exception e) {
			final String logId = "WorkFlowDataPackagePrjRecordHandler[add]";
			final String reason = LogHelper.getInstance().exceptionReason(workFlowDataId, inputForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<WorkFlowData> addList(long workFlowDataId, PackagePrjRecordAddListForm addListForm) {
		ReqResult<WorkFlowData> result = ReqResult.newInstance(false, WorkFlowData.class);
		try {
			List<PackagePrjRecordAddForm> inputForms = addListForm.getPackagePrjRecordAddForms();
			if(CollectionUtils.isNotEmpty(inputForms)){
				WorkFlowData workFlowData = WorkFlowDataMgr.getInstance().get(workFlowDataId);
				for (PackagePrjRecordAddForm inputForm : inputForms) {
					WorkFlowDataBeanHelper.getInstance().addPackagePrjRecord(workFlowData, inputForm);
				}
				WorkFlowDataMgr.getInstance().updateWorkFlowData(workFlowData);
				result.setTarget(workFlowData);
			}
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "WorkFlowDataPackagePrjRecordHandler[addList]";
			final String reason = LogHelper.getInstance().exceptionReason(workFlowDataId, addListForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<WorkFlowData> updList(long workFlowDataId, PackagePrjRecordUpdListForm updListForm) {
		ReqResult<WorkFlowData> result = ReqResult.newInstance(false, WorkFlowData.class);
		try {
			WorkFlowData workFlowData = WorkFlowDataMgr.getInstance().get(workFlowDataId);
			workFlowData.getPackagePrjRecordMap().clear();
			List<PackagePrjRecordAddForm> inputForms = updListForm.getPackagePrjRecordUpdForms();
			if(CollectionUtils.isNotEmpty(inputForms)){
				for (PackagePrjRecordAddForm inputForm : inputForms) {
					WorkFlowDataBeanHelper.getInstance().addPackagePrjRecord(workFlowData, inputForm);
				}
			}
			WorkFlowDataMgr.getInstance().updateWorkFlowData(workFlowData);
			result.setTarget(workFlowData);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "WorkFlowDataPackagePrjRecordHandler[updList]";
			final String reason = LogHelper.getInstance().exceptionReason(workFlowDataId, updListForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
}

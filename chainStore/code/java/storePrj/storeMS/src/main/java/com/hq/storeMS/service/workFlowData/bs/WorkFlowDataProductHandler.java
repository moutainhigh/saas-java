package com.hq.storeMS.service.workFlowData.bs;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.service.common.ExceptionInfo;
import com.hq.storeMS.service.common.HandlerHelper;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RespStatus;
import com.hq.storeMS.service.workFlowData.apiData.ProdRecordAddForm;
import com.hq.storeMS.service.workFlowData.apiData.ProdRecordAddListForm;
import com.hq.storeMS.service.workFlowData.apiData.ProdRecordUpdInfoForm;
import com.hq.storeMS.service.workFlowData.apiData.ProdRecordUpdListForm;
import com.hq.storeMS.service.workFlowData.apiData.ProdRecordUpdStatusForm;
import com.hq.storeMS.service.workFlowData.apiData.ProdRecordUpdateEnum;
import com.hq.storeMS.service.workFlowData.apiData.ProdRecordUpdateForm;
import com.hq.storeMS.service.workFlowData.data.WorkFlowData;
import com.hq.storeMS.service.workFlowData.data.WorkFlowDataBeanHelper;
import com.zenmind.common.hotSwap.HotSwap;

public class WorkFlowDataProductHandler {

	public static WorkFlowDataProductHandler getInstance() {
		return HotSwap.getInstance().getSingleton(WorkFlowDataProductHandler.class);
	}
	
	private final LogModule logModule = LogModule.WorkFlowData;
	
	public ReqResult<WorkFlowData> update(long workFlowDataId, String productId, ProdRecordUpdateForm inputForm) {
		ReqResult<WorkFlowData> result = ReqResult.newInstance(false, WorkFlowData.class);
		try {
			ProdRecordUpdateEnum updateType = ProdRecordUpdateEnum.valueOf(inputForm.getUpdateType());
			OperateTips operateTips = OperateTips.newInstance(false, "更新失败");
			switch (updateType) {
			case updateProductInfo:
				operateTips = updateProductInfo(workFlowDataId, productId, inputForm.getProdRecordUpdInfoForm());
				break;
			case updateProductStatus:
				operateTips = updateProductStatus(workFlowDataId, productId, inputForm.getProdRecordUpdStatusForm());
				break;
			default:
				break;
			}
			if(operateTips.isSuccess()){
				WorkFlowData target = WorkFlowDataMgr.getInstance().get(workFlowDataId);
				result.setTarget(target);
				result.setSuccess(true);
			}else{
				HandlerHelper.getInstance().handleReqResult(result, operateTips);
			}
		} catch (Exception e) {
			final String logId = "WorkFlowDataHandler[update]";
			final String reason = LogHelper.getInstance().exceptionReason(workFlowDataId, productId, inputForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}

	private OperateTips updateProductStatus(long workFlowDataId, String productId, ProdRecordUpdStatusForm updStatusForm) {
		OperateTips operateTips = OperateTips.newInstance(false);
		WorkFlowData workFlowData = WorkFlowDataMgr.getInstance().get(workFlowDataId);
		if(WorkFlowDataBeanHelper.getInstance().updateProdRecordStatus(workFlowData, updStatusForm)){
			WorkFlowDataMgr.getInstance().updateWorkFlowData(workFlowData);
			operateTips.setSuccess(true);
		}else{
			operateTips.setTips("更新项目状态失败");
		}
		return operateTips;
	}

	private OperateTips updateProductInfo(long workFlowDataId, String productId, ProdRecordUpdInfoForm updateInfoForm) {
		OperateTips operateTips = OperateTips.newInstance(false);
		WorkFlowData workFlowData = WorkFlowDataMgr.getInstance().get(workFlowDataId);
		if(WorkFlowDataBeanHelper.getInstance().updateProdRecordInfo(workFlowData, updateInfoForm)){
			WorkFlowDataMgr.getInstance().updateWorkFlowData(workFlowData);
			operateTips.setSuccess(true);
		}else{
			operateTips.setTips("更新项目信息失败");
		}
		return operateTips;
	}
	
	public ReqResult<WorkFlowData> delete(long workFlowDataId, String productId) {
		ReqResult<WorkFlowData> result = ReqResult.newInstance(false, WorkFlowData.class);
		try {
			WorkFlowData target = WorkFlowDataMgr.getInstance().get(workFlowDataId);
			if(WorkFlowDataBeanHelper.getInstance().deleteProdRecord(target, productId)){
				WorkFlowDataMgr.getInstance().updateWorkFlowData(target);
				result.setTarget(target);
				result.setSuccess(true);
			}else{
				result.setStatus(RespStatus.INVALID_REQUEST);
				result.setTips("删除项目失败");
			}
		} catch (Exception e) {
			final String logId = "WorkFlowDataProductHandler[delete]";
			final String reason = LogHelper.getInstance().exceptionReason(workFlowDataId, productId);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<WorkFlowData> add(long workFlowDataId, ProdRecordAddForm inputForm) {
		ReqResult<WorkFlowData> result = ReqResult.newInstance(false, WorkFlowData.class);
		try {
			WorkFlowData target = WorkFlowDataMgr.getInstance().get(workFlowDataId);
			if(WorkFlowDataBeanHelper.getInstance().addProdRecord(target, inputForm)){
				WorkFlowDataMgr.getInstance().updateWorkFlowData(target);
				result.setTarget(target);
				result.setSuccess(true);
			}else{
				result.setStatus(RespStatus.INVALID_REQUEST);
				result.setTips("重复添加项目");
			}
		} catch (Exception e) {
			final String logId = "WorkFlowDataProductHandler[add]";
			final String reason = LogHelper.getInstance().exceptionReason(workFlowDataId, inputForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<WorkFlowData> addList(long workFlowDataId, ProdRecordAddListForm addListForm) {
		ReqResult<WorkFlowData> result = ReqResult.newInstance(false, WorkFlowData.class);
		try {
			List<ProdRecordAddForm> inputForms = addListForm.getProdRecordAddForms();
			if(CollectionUtils.isNotEmpty(inputForms)){
				WorkFlowData workFlowData = WorkFlowDataMgr.getInstance().get(workFlowDataId);
				for (ProdRecordAddForm inputForm : inputForms) {
					WorkFlowDataBeanHelper.getInstance().addProdRecord(workFlowData, inputForm);
				}
				WorkFlowDataMgr.getInstance().updateWorkFlowData(workFlowData);
				result.setTarget(workFlowData);
			}
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "WorkFlowDataProductHandler[addList]";
			final String reason = LogHelper.getInstance().exceptionReason(workFlowDataId, addListForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<WorkFlowData> updList(long workFlowDataId, ProdRecordUpdListForm updListForm) {
		ReqResult<WorkFlowData> result = ReqResult.newInstance(false, WorkFlowData.class);
		try {
			WorkFlowData workFlowData = WorkFlowDataMgr.getInstance().get(workFlowDataId);
			workFlowData.getProdRecordMap().clear();
			List<ProdRecordAddForm> inputForms = updListForm.getProdRecordAddForms();
			if(CollectionUtils.isNotEmpty(inputForms)){
				for (ProdRecordAddForm inputForm : inputForms) {
					WorkFlowDataBeanHelper.getInstance().addProdRecord(workFlowData, inputForm);
				}
			}
			WorkFlowDataMgr.getInstance().updateWorkFlowData(workFlowData);
			result.setTarget(workFlowData);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "WorkFlowDataProductHandler[updList]";
			final String reason = LogHelper.getInstance().exceptionReason(workFlowDataId, updListForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
}

package com.hq.storeMS.service.workFlowData.bs;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.service.common.ExceptionInfo;
import com.hq.storeMS.service.common.HandlerHelper;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RespStatus;
import com.hq.storeMS.service.workFlowData.apiData.BonusInfoAddForm;
import com.hq.storeMS.service.workFlowData.apiData.BonusInfoAddListForm;
import com.hq.storeMS.service.workFlowData.apiData.BonusInfoUpdateForm;
import com.hq.storeMS.service.workFlowData.data.WorkFlowData;
import com.hq.storeMS.service.workFlowData.data.WorkFlowDataBeanHelper;
import com.zenmind.common.hotSwap.HotSwap;

public class WorkFlowDataBonusInfoHandler {

	public static WorkFlowDataBonusInfoHandler getInstance() {
		return HotSwap.getInstance().getSingleton(WorkFlowDataBonusInfoHandler.class);
	}
	
	private final LogModule logModule = LogModule.WorkFlowData;
	
	public ReqResult<WorkFlowData> update(long workFlowDataId, String bonusId, BonusInfoUpdateForm inputForm) {
		ReqResult<WorkFlowData> result = ReqResult.newInstance(false, WorkFlowData.class);
		try {
			WorkFlowData workFlowData = WorkFlowDataMgr.getInstance().get(workFlowDataId);
			if(WorkFlowDataBeanHelper.getInstance().updateBonusInfo(workFlowData, inputForm)){
				WorkFlowDataMgr.getInstance().updateWorkFlowData(workFlowData);
				result.setTarget(workFlowData);
				result.setSuccess(true);
			}else{
				result.setStatus(RespStatus.INVALID_REQUEST);
				result.setTips("更新提成信息失败");
			}
		} catch (Exception e) {
			final String logId = "WorkFlowDataBonusInfoHandler[update]";
			final String reason = LogHelper.getInstance().exceptionReason(workFlowDataId, bonusId, inputForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<WorkFlowData> delete(long workFlowDataId, String bonusId) {
		ReqResult<WorkFlowData> result = ReqResult.newInstance(false, WorkFlowData.class);
		try {
			WorkFlowData workFlowData = WorkFlowDataMgr.getInstance().get(workFlowDataId);
			String[] ids = bonusId.split(",");
			boolean b = true;
			for (String id : ids) {
				if(!WorkFlowDataBeanHelper.getInstance().deleteBonusInfo(workFlowData, id)) {
					b = false;
					break;
				}
			}
			if(b){
				WorkFlowDataMgr.getInstance().updateWorkFlowData(workFlowData);
				result.setTarget(workFlowData);
				result.setSuccess(true);
			}else{
				result.setStatus(RespStatus.INVALID_REQUEST);
				result.setTips("删除提成记录失败");
			}
		} catch (Exception e) {
			final String logId = "WorkFlowDataBonusInfoHandler[delete]";
			final String reason = LogHelper.getInstance().exceptionReason(workFlowDataId, bonusId);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<WorkFlowData> add(long workFlowDataId, BonusInfoAddForm inputForm) {
		ReqResult<WorkFlowData> result = ReqResult.newInstance(false, WorkFlowData.class);
		try {
			WorkFlowData workFlowData = WorkFlowDataMgr.getInstance().get(workFlowDataId);
			if(WorkFlowDataBeanHelper.getInstance().addBonusInfo(workFlowData, inputForm)){
				WorkFlowDataMgr.getInstance().updateWorkFlowData(workFlowData);
				result.setTarget(workFlowData);
				result.setSuccess(true);
			}else{
				result.setStatus(RespStatus.INVALID_REQUEST);
				result.setTips("提成记录重复");
			}
		} catch (Exception e) {
			final String logId = "WorkFlowDataBonusInfoHandler[add]";
			final String reason = LogHelper.getInstance().exceptionReason(workFlowDataId, inputForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<WorkFlowData> addList(long workFlowDataId, BonusInfoAddListForm addListForm) {
		ReqResult<WorkFlowData> result = ReqResult.newInstance(false, WorkFlowData.class);
		try {
			List<BonusInfoAddForm> inputForms = addListForm.getBonusInfoAddForms();
			if(CollectionUtils.isNotEmpty(inputForms)){
				WorkFlowData workFlowData = WorkFlowDataMgr.getInstance().get(workFlowDataId);
				for (BonusInfoAddForm inputForm : inputForms) {
					WorkFlowDataBeanHelper.getInstance().addBonusInfo(workFlowData, inputForm);
				}
				WorkFlowDataMgr.getInstance().updateWorkFlowData(workFlowData);
				result.setTarget(workFlowData);
			}
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "WorkFlowDataBonusInfoHandler[add]";
			final String reason = LogHelper.getInstance().exceptionReason(workFlowDataId, addListForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
}

package com.hq.storeMS.service.workFlowType.bs;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.service.common.ExceptionInfo;
import com.hq.storeMS.service.common.HandlerHelper;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.workFlowType.apiData.AddWorkFlowTypeForm;
import com.hq.storeMS.service.workFlowType.apiData.QueryWorkFlowTypeForm;
import com.hq.storeMS.service.workFlowType.apiData.UpdWorkFlowTypeForm;
import com.hq.storeMS.service.workFlowType.apiData.UpdateWorkFlowTypeEnum;
import com.hq.storeMS.service.workFlowType.data.WorkFlowType;
import com.zenmind.common.beanCopy.FastBeanCopyer;
import com.zenmind.common.hotSwap.HotSwap;

public class WorkFlowTypeHandler {

	public static WorkFlowTypeHandler getInstance() {
		return HotSwap.getInstance().getSingleton(WorkFlowTypeHandler.class);
	}
	
	private final LogModule logModule = LogModule.WorkFlowType;
	
	public ReqResult<WorkFlowType> findByCond(QueryWorkFlowTypeForm queryForm) {
		ReqResult<WorkFlowType> result = ReqResult.newInstance(false, WorkFlowType.class);
		try {
			List<WorkFlowType> workFlowTypes = WorkFlowTypeMgr.getInstance().findByCond(queryForm);
			if(CollectionUtils.isEmpty(workFlowTypes)){
				workFlowTypes = new ArrayList<WorkFlowType>();
			}
			result.setTargetList(workFlowTypes);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "WorkFlowTypeHandler[findByCond]";
			final String reason = LogHelper.getInstance().exceptionReason(queryForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<WorkFlowType> findByName(String typeName) {
		ReqResult<WorkFlowType> result = ReqResult.newInstance(false, WorkFlowType.class);
		try {
			WorkFlowType workFlowType = WorkFlowTypeMgr.getInstance().findOne(typeName);
			result.setTarget(workFlowType);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "WorkFlowTypeHandler[findByName]";
			final String reason = LogHelper.getInstance().exceptionReason(typeName);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<WorkFlowType> getWorkFlowType(long workFlowTypeId) {
		ReqResult<WorkFlowType> result = ReqResult.newInstance(false, WorkFlowType.class);
		try {
			WorkFlowType workFlowType = WorkFlowTypeMgr.getInstance().get(workFlowTypeId);
			result.setTarget(workFlowType);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "WorkFlowTypeHandler[getWorkFlowType]";
			final String reason = LogHelper.getInstance().exceptionReason(workFlowTypeId);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<WorkFlowType> updateWorkFlowType(long workFlowTypeId, UpdWorkFlowTypeForm inputForm) {
		ReqResult<WorkFlowType> result = ReqResult.newInstance(false, WorkFlowType.class);
		try {
			UpdateWorkFlowTypeEnum updateType = UpdateWorkFlowTypeEnum.valueOf(inputForm.getUpdateType());
			WorkFlowType workFlowType = WorkFlowTypeMgr.getInstance().get(workFlowTypeId);
			boolean success = false;
			switch (updateType) {
			case UpdateWorkFlowTypeInfo:
				FastBeanCopyer.getInstance().copy(inputForm.getUpdWorkFlowTypeInfoForm(), workFlowType);
				WorkFlowTypeMgr.getInstance().updateWorkFlowType(workFlowType);
				success = true;
				break;
			default:
				break;
			}
			result.setTarget(workFlowType);
			result.setSuccess(success);
		} catch (Exception e) {
			final String logId = "WorkFlowTypeHandler[updateWorkFlowType]";
			final String reason = LogHelper.getInstance().exceptionReason(workFlowTypeId, inputForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<WorkFlowType> addWorkFlowType(AddWorkFlowTypeForm inputForm) {
		ReqResult<WorkFlowType> result = ReqResult.newInstance(false, WorkFlowType.class);
		try {
			WorkFlowType target = WorkFlowType.newInstance();
			FastBeanCopyer.getInstance().copy(inputForm, target);
			WorkFlowTypeMgr.getInstance().addAndReturnId(target);
			result.setTarget(target);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "WorkFlowTypeHandler[addWorkFlowType]";
			final String reason = LogHelper.getInstance().exceptionReason(inputForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
}

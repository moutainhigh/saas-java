package com.hq.storeMS.service.workFlowData.bs;

import org.apache.commons.lang3.StringUtils;

import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.service.common.ExceptionInfo;
import com.hq.storeMS.service.common.HandlerHelper;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.storeLeaguerInfo.bs.StoreLeaguerInfoMgr;
import com.hq.storeMS.service.storeLeaguerInfo.data.Leaguer;
import com.hq.storeMS.service.storeLeaguerInfo.data.StoreLeaguerInfo;
import com.hq.storeMS.service.workFlowData.apiData.LeaguerInfoForm;
import com.hq.storeMS.service.workFlowData.data.WorkFlowData;
import com.hq.storeMS.service.workFlowData.data.WorkFlowDataBeanHelper;
import com.zenmind.common.hotSwap.HotSwap;

public class WorkFlowDataLeaguerInfoHandler {

	public static WorkFlowDataLeaguerInfoHandler getInstance() {
		return HotSwap.getInstance().getSingleton(WorkFlowDataLeaguerInfoHandler.class);
	}
	
	private final LogModule logModule = LogModule.WorkFlowData;
	
	public ReqResult<WorkFlowData> update(long workFlowDataId, String leaguerId, LeaguerInfoForm inputForm) {
		ReqResult<WorkFlowData> result = ReqResult.newInstance(false, WorkFlowData.class);
		try {
			WorkFlowData workFlowData = WorkFlowDataMgr.getInstance().get(workFlowDataId);
			if(StringUtils.isBlank(inputForm.getLeaguerName())){
				StoreLeaguerInfo storeLeaguerInfo = StoreLeaguerInfoMgr.getInstance().get(workFlowData.getStoreId());
				Leaguer leaguer = storeLeaguerInfo.getLeaguerInfoMap().get(inputForm.getLeaguerId());
				if(leaguer != null){
					inputForm.setLeaguerName(leaguer.getName());
				}
			}
			WorkFlowDataBeanHelper.getInstance().updateLeaguerInfo(workFlowData, inputForm);
			WorkFlowDataMgr.getInstance().updateWorkFlowData(workFlowData);
			result.setTarget(workFlowData);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "WorkFlowDataLeaguerInfoHandler[update]";
			final String reason = LogHelper.getInstance().exceptionReason(workFlowDataId, leaguerId, inputForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<WorkFlowData> add(long workFlowDataId, LeaguerInfoForm inputForm) {
		ReqResult<WorkFlowData> result = ReqResult.newInstance(false, WorkFlowData.class);
		try {
			WorkFlowData workFlowData = WorkFlowDataMgr.getInstance().get(workFlowDataId);
			if(StringUtils.isBlank(inputForm.getLeaguerName())){
				StoreLeaguerInfo storeLeaguerInfo = StoreLeaguerInfoMgr.getInstance().get(workFlowData.getStoreId());
				Leaguer leaguer = storeLeaguerInfo.getLeaguerInfoMap().get(inputForm.getLeaguerId());
				if(leaguer != null){
					inputForm.setLeaguerName(leaguer.getName());
				}
			}
			WorkFlowDataBeanHelper.getInstance().updateLeaguerInfo(workFlowData, inputForm);
			WorkFlowDataMgr.getInstance().updateWorkFlowData(workFlowData);
			result.setTarget(workFlowData);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "WorkFlowDataLeaguerInfoHandler[add]";
			final String reason = LogHelper.getInstance().exceptionReason(workFlowDataId, inputForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
}

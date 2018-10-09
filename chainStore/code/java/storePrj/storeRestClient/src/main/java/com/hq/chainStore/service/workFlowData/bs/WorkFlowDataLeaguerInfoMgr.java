package com.hq.chainStore.service.workFlowData.bs;

import com.hq.chainStore.service.workFlowData.apiData.LeaguerInfoForm;
import com.hq.chainStore.service.workFlowData.data.WorkFlowData;
import com.hq.chainStore.service.workFlowData.data.WorkFlowDataDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class WorkFlowDataLeaguerInfoMgr {
	
	public static WorkFlowDataLeaguerInfoMgr getInstance(){
		return HotSwap.getInstance().getSingleton(WorkFlowDataLeaguerInfoMgr.class);
	}
	
	private final String module = "leaguerInfo";
	
	public WorkFlowData updateLeaguerInfo(long workFlowDataId, String leaguerId, LeaguerInfoForm inputForm) {
		return WorkFlowDataDAO.getInstance().updateSubDomains(workFlowDataId, leaguerId, inputForm, module);
	}
	
	public WorkFlowData addLeaguerInfo(long workFlowDataId, LeaguerInfoForm inputForm) {
		return WorkFlowDataDAO.getInstance().addSubDomains(workFlowDataId, inputForm, module);
	}
	
}

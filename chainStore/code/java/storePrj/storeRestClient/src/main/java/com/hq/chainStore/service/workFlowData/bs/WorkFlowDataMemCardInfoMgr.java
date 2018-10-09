package com.hq.chainStore.service.workFlowData.bs;

import com.hq.chainStore.service.workFlowData.apiData.MemCardInfoForm;
import com.hq.chainStore.service.workFlowData.data.WorkFlowData;
import com.hq.chainStore.service.workFlowData.data.WorkFlowDataDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class WorkFlowDataMemCardInfoMgr {
	
	public static WorkFlowDataMemCardInfoMgr getInstance(){
		return HotSwap.getInstance().getSingleton(WorkFlowDataMemCardInfoMgr.class);
	}
	
	private final String module = "memCardInfo";
	
	public WorkFlowData updateMemCardInfo(long workFlowDataId, String memTypeId, MemCardInfoForm inputForm) {
		return WorkFlowDataDAO.getInstance().updateSubDomains(workFlowDataId, memTypeId, inputForm, module);
	}
	
	public WorkFlowData addMemCardInfo(long workFlowDataId, MemCardInfoForm inputForm) {
		return WorkFlowDataDAO.getInstance().addSubDomains(workFlowDataId, inputForm, module);
	}
	
}

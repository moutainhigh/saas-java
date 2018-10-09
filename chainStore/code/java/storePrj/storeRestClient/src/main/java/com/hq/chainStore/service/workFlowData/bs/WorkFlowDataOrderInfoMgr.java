package com.hq.chainStore.service.workFlowData.bs;

import com.hq.chainStore.service.workFlowData.apiData.OrderInfoForm;
import com.hq.chainStore.service.workFlowData.data.WorkFlowData;
import com.hq.chainStore.service.workFlowData.data.WorkFlowDataDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class WorkFlowDataOrderInfoMgr {
	
	public static WorkFlowDataOrderInfoMgr getInstance(){
		return HotSwap.getInstance().getSingleton(WorkFlowDataOrderInfoMgr.class);
	}
	
	private final String module = "orderInfo";
	
	public WorkFlowData addOrderInfo(long workFlowDataId, OrderInfoForm inputForm) {
		return WorkFlowDataDAO.getInstance().addSubDomains(workFlowDataId, inputForm, module);
	}
}

package com.hq.chainStore.service.workFlowType.data;

import com.zenmind.common.hotSwap.HotSwap;

public class WorkFlowTypeSynDataHolder{

	public static WorkFlowTypeSynDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(WorkFlowTypeSynDataHolder.class);
	}

	public WorkFlowType getData(String ownerId, String targetId) {
		return WorkFlowTypeDAO.getInstance().get(targetId);
	}

}

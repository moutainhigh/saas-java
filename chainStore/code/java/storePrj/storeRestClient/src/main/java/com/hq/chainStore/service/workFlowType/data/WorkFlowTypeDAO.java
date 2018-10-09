package com.hq.chainStore.service.workFlowType.data;

import com.hq.chainStore.service.common.RestClientCfg;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class WorkFlowTypeDAO extends RestDao<WorkFlowType> {

	public static WorkFlowTypeDAO getInstance(){
		return HotSwap.getInstance().getSingleton(WorkFlowTypeDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getStoreService();
	}
	
}

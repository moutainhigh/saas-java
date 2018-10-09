package com.hq.chainStore.service.clerkSalary.data;

import com.hq.chainStore.service.common.RestClientCfg;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class ClerkSalaryDAO extends RestDao<ClerkSalary>{

	public static ClerkSalaryDAO getInstance(){
		return HotSwap.getInstance().getSingleton(ClerkSalaryDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getStoreService();
	}
	
}

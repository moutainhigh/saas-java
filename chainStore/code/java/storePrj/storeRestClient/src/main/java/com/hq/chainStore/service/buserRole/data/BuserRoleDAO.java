package com.hq.chainStore.service.buserRole.data;

import com.hq.chainStore.service.common.RestClientCfg;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class BuserRoleDAO extends RestDao<BuserRole> {
	public static BuserRoleDAO getInstance() {
		return HotSwap.getInstance().getSingleton(BuserRoleDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getStoreService();
	}
}

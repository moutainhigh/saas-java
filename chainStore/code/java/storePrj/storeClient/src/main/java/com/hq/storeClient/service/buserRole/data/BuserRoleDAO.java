package com.hq.storeClient.service.buserRole.data;

import com.hq.storeClient.common.restClientResp.RestClientCfg;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class BuserRoleDAO extends RestDao<BuserRole> {
	public static BuserRoleDAO getInstance() {
		return HotSwap.getInstance().getSingleton(BuserRoleDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getService();
	}
}

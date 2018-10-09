package com.hq.chainStore.service.buser.data;

import com.hq.chainStore.service.buser.apiData.BUserResetPasswordData;
import com.hq.chainStore.service.common.RestClientCfg;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class BUserResetPasswordDAO extends RestDao<BUserResetPasswordData> {

	public static BUserResetPasswordDAO getInstance(){
		return HotSwap.getInstance().getSingleton(BUserResetPasswordDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getStoreService();
	}
	
	
}

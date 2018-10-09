package com.hq.chainClient.service.chainPackageProject.data;

import com.hq.chainClient.common.restClientResp.RestClientCfg;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class ChainPackageProjectDAO extends RestDao<ChainPackageProject> {

	public static ChainPackageProjectDAO getInstance(){
		return HotSwap.getInstance().getSingleton(ChainPackageProjectDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getService();
	}
}

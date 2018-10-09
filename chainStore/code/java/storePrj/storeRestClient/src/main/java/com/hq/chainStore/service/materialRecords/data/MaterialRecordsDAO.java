package com.hq.chainStore.service.materialRecords.data;

import com.hq.chainStore.service.common.RestClientCfg;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class MaterialRecordsDAO extends RestDao<MaterialRecords> {

	public static MaterialRecordsDAO getInstance(){
		return HotSwap.getInstance().getSingleton(MaterialRecordsDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getStoreService();
	}
	
}

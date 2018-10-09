package com.hq.storeClient.service.storeBeauticianInfo.data;

import com.hq.storeClient.common.restClientResp.RestClientCfg;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class StoreBeauticianInfoDAO extends RestDao<StoreBeauticianInfo> {

	public static StoreBeauticianInfoDAO getInstance() {
		return HotSwap.getInstance().getSingleton(StoreBeauticianInfoDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getService();
	}
	
	@Override
	public StoreBeauticianInfo get(Object storeId) {
		String uriPath = "findSimpleStoreInfo/"+storeId;
		return super.findOne(uriPath);
	}
	

}

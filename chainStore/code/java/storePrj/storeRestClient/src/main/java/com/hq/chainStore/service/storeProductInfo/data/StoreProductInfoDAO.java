package com.hq.chainStore.service.storeProductInfo.data;

import com.hq.chainStore.service.common.RestClientCfg;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class StoreProductInfoDAO extends RestDao<StoreProductInfo> {

	public static StoreProductInfoDAO getInstance(){
		return HotSwap.getInstance().getSingleton(StoreProductInfoDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getStoreService();
	}
	
	@Override
	public StoreProductInfo get(Object storeId) {
		return getSimple(storeId);
	}
	
	public StoreProductInfo getDetail(Object storeId) {
		return super.get(storeId);
	}
	
	public StoreProductInfo getSimple(Object storeId){
		String uriPath = "findSimpleStoreInfo/"+storeId;
		return super.findOne(uriPath);
	}
	
}

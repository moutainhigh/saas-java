package com.hq.chainStore.service.storeCardInfo.data;

import com.hq.chainStore.service.common.RestClientCfg;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class StoreCardInfoDAO extends RestDao<StoreCardInfo> {

	public static StoreCardInfoDAO getInstance() {
		return HotSwap.getInstance().getSingleton(StoreCardInfoDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getStoreService();
	}
	
	@Override
	public StoreCardInfo get(Object storeId) {
		return getSimple(storeId);
	}
	
	public StoreCardInfo getDetail(Object storeId) {
		return super.get(storeId);
	}
	
	public StoreCardInfo getSimple(Object storeId){
		String uriPath = "findSimpleStoreInfo/"+storeId;
		return super.findOne(uriPath);
	}

}

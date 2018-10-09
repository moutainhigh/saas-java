package com.hq.chainStore.service.storeGoods.data;

import com.hq.chainStore.service.common.RestClientCfg;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class StoreGoodsDAO extends RestDao<StoreGoods> {

	public static StoreGoodsDAO getInstance(){
		return HotSwap.getInstance().getSingleton(StoreGoodsDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getStoreService();
	}
	
	@Override
	public StoreGoods get(Object storeId) {
		return getSimple(storeId);
	}
	
	public StoreGoods getDetail(Object storeId) {
		return super.get(storeId);
	}
	
	public StoreGoods getSimple(Object storeId){
		String uriPath = "findSimpleStoreInfo/"+storeId;
		return super.findOne(uriPath);
	}
	
}

package com.hq.chainStore.service.storeLeaguerInfo.data;

import com.hq.chainStore.service.common.RestClientCfg;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.classinfo.ClassInfo;
import com.zenmind.dao.rest.RestDao;

public class StoreLeaguerInfoDAO extends RestDao<StoreLeaguerInfo> {

	public static StoreLeaguerInfoDAO getInstance(){
		return HotSwap.getInstance().getSingleton(StoreLeaguerInfoDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getStoreService();
	}
	
	@Override
	public StoreLeaguerInfo get(Object storeId) {
		return getSimple(storeId);
	}
	
	public StoreLeaguerInfo getDetail(Object storeId) {
		return super.get(storeId);
	}
	
	public StoreLeaguerInfo getSimple(Object storeId){
		String uriPath = "findSimpleStoreInfo/"+storeId;
		return super.findOne(uriPath);
	}
	
	public static void main(String[] args) {
		new ClassInfo(Leaguer.class);
	}
	
}

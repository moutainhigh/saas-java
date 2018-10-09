package com.hq.chainStore.service.storeProductInfo.data;

import java.util.Map;

import com.hq.common.dataSyn.bs.AbsDataSynDataHolder;
import com.hq.common.dataSyn.info.DataSynType;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class StoreProductInfoSynDataHolder extends AbsDataSynDataHolder<StoreProductInfo> {

	public static StoreProductInfoSynDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(StoreProductInfoSynDataHolder.class);
	}

	final private DataSynType synType = DataSynType.StoreProductInfo;

	protected Class<StoreProductInfo> getClazz() {
		return StoreProductInfo.class;
	}

	protected RestDao<StoreProductInfo> getDao() {
		return StoreProductInfoDAO.getInstance();
	}

	public DataSynType getSynType() {
		return synType;
	}

	
	public Map<String, ProductInfo> getProductInfoMap(String ownerId, Long storeId){
		StoreProductInfo storeProductInfo = super.getData(ownerId, String.valueOf(storeId));
		return storeProductInfo.getProductInfoMap();
	}
	
	public Map<String, ProductType> getProductTypeMap(String ownerId, Long storeId){
		StoreProductInfo storeProductInfo = super.getData(ownerId, String.valueOf(storeId));
		return storeProductInfo.getProductTypeMap();
	}

}

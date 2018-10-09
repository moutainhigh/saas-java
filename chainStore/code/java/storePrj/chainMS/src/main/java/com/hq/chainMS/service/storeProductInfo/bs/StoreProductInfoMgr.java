package com.hq.chainMS.service.storeProductInfo.bs;

import java.util.Set;

import com.hq.storeClient.service.storeProductInfo.apiData.BatchUpdateProductStateData;
import com.hq.storeClient.service.storeProductInfo.apiData.UpdateProductStateData;
import com.hq.storeClient.service.storeProductInfo.data.StoreProductInfo;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreProductInfoMgr {

	public static StoreProductInfoMgr getInstance() {
		return HotSwap.getInstance().getSingleton(StoreProductInfoMgr.class);
	}
	
	public StoreProductInfo getStoreProductInfo(long storeId) {
		return StoreProductInfoDataHolder.getInstance().getStoreProductInfo(storeId);
	}
	
	public void updateProductState(Set<Long> applyStoreIds, String id, int state) {
		for (Long storeId : applyStoreIds) {
			UpdateProductStateData data = UpdateProductStateData.newInstance();
			data.setStoreId(storeId);
			data.setId(id);
			data.setState(state);
			updateProductState(storeId, data);
		}
	}
	
	public void batchUpdateProductState(long storeId, BatchUpdateProductStateData dataForm) {
		StoreProductInfoDataHolder.getInstance().batchUpdateProductState(storeId, dataForm);
	}
	
	public void updateProductState(long storeId, UpdateProductStateData dataForm) {
		StoreProductInfoDataHolder.getInstance().updateProductState(storeId, dataForm);
	}
}

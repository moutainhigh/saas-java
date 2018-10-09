package com.hq.chainMS.service.storeProductInfo.bs;

import com.hq.chainMS.common.constants.ServerConstants;
import com.hq.chainMS.common.validate.AppIdThreadLocal;
import com.hq.chainMS.service.storeProductInfo.data.StoreProductInfoCacheDAO;
import com.hq.storeClient.service.storeProductInfo.apiData.BatchUpdateProductStateData;
import com.hq.storeClient.service.storeProductInfo.apiData.UpdateProductStateData;
import com.hq.storeClient.service.storeProductInfo.bs.StoreProductInfoClientMgr;
import com.hq.storeClient.service.storeProductInfo.data.StoreProductInfo;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreProductInfoDataHolder {

	public static StoreProductInfoDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(StoreProductInfoDataHolder.class);
	}

	public StoreProductInfo getStoreProductInfo(long storeId) {
		StoreProductInfo data = StoreProductInfoCacheDAO.getInstance().get(storeId);
		if(data!=null) {
			return data;
		}
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		data = StoreProductInfoClientMgr.getInstance().get(storeId);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}
	
	public void batchUpdateProductState(long storeId, BatchUpdateProductStateData dataForm) {
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		StoreProductInfoClientMgr.getInstance().batchUpdateProductState(storeId, dataForm);
		AppIdThreadLocal.getInstance().set(null);
	}
	
	public void updateProductState(long storeId, UpdateProductStateData dataForm) {
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		StoreProductInfoClientMgr.getInstance().updateProductState(storeId, dataForm);
		AppIdThreadLocal.getInstance().set(null);
	}
	
}

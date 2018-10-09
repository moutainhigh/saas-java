package com.hq.storeClient.service.storeProductInfo.bs;

import com.hq.storeClient.service.storeProductInfo.apiData.BatchUpdateProductStateData;
import com.hq.storeClient.service.storeProductInfo.apiData.StoreProductInfoUpdateForm;
import com.hq.storeClient.service.storeProductInfo.apiData.StoreProductInfoUpdateType;
import com.hq.storeClient.service.storeProductInfo.apiData.UpdateProductStateData;
import com.hq.storeClient.service.storeProductInfo.data.StoreProductInfo;
import com.hq.storeClient.service.storeProductInfo.data.StoreProductInfoDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreProductInfoClientMgr {

	public static StoreProductInfoClientMgr getInstance() {
		return HotSwap.getInstance().getSingleton(StoreProductInfoClientMgr.class);
	}

	public StoreProductInfo get(long storeId) {
		return StoreProductInfoDAO.getInstance().get(storeId);
	}

	public void update(long id, StoreProductInfoUpdateForm updateForm) {
		StoreProductInfoDAO.getInstance().update(id, updateForm);
	}
	
	public void updateProductState(long storeId, UpdateProductStateData updateProductStateData){
		StoreProductInfoUpdateForm updateForm = StoreProductInfoUpdateForm.newInstance();
		updateForm.setUpdateProductStateData(updateProductStateData);
		updateForm.setUpdateTypeEnum(StoreProductInfoUpdateType.UpdateProductState);
		update(storeId, updateForm);
	}
	
	public void batchUpdateProductState(long storeId, BatchUpdateProductStateData batchUpdateProductStateData){
		StoreProductInfoUpdateForm updateForm = StoreProductInfoUpdateForm.newInstance();
		updateForm.setBatchUpdateProductStateData(batchUpdateProductStateData);
		updateForm.setUpdateTypeEnum(StoreProductInfoUpdateType.BatchUpdateProductState);
		update(storeId, updateForm);
	}

}

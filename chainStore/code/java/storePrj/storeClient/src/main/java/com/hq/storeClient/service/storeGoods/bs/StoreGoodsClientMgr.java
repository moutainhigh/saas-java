package com.hq.storeClient.service.storeGoods.bs;

import com.hq.storeClient.service.storeGoods.apiData.GoodsBatchUpdateStateForm;
import com.hq.storeClient.service.storeGoods.apiData.GoodsUpdateStateForm;
import com.hq.storeClient.service.storeGoods.apiData.StoreGoodsUpdateForm;
import com.hq.storeClient.service.storeGoods.apiData.StoreGoodsUpdateType;
import com.hq.storeClient.service.storeGoods.data.StoreGoods;
import com.hq.storeClient.service.storeGoods.data.StoreGoodsDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreGoodsClientMgr {

	public static StoreGoodsClientMgr getInstance() {
		return HotSwap.getInstance().getSingleton(StoreGoodsClientMgr.class);
	}

	public StoreGoods get(long storeId) {
		return StoreGoodsDAO.getInstance().get(storeId);
	}

	public void update(long storeId, StoreGoodsUpdateForm updateForm) {
		StoreGoodsDAO.getInstance().update(storeId, updateForm);
	}
	
	public void updateGoodsState(long storeId, GoodsUpdateStateForm dataForm){
		StoreGoodsUpdateForm updateForm = StoreGoodsUpdateForm.newInstance();
		updateForm.setUpdateType(StoreGoodsUpdateType.UpdateGoodsState.ordinal());
		updateForm.setGoodsUpdateStateForm(dataForm);
		update(storeId, updateForm);
	}
	
	public void batchUpdateGoodsState(long storeId, GoodsBatchUpdateStateForm dataForm){
		StoreGoodsUpdateForm updateForm = StoreGoodsUpdateForm.newInstance();
		updateForm.setUpdateType(StoreGoodsUpdateType.BatchUpdateGoodsState.ordinal());
		updateForm.setGoodsBatchUpdateStateForm(dataForm);
		update(storeId, updateForm);
	}

}

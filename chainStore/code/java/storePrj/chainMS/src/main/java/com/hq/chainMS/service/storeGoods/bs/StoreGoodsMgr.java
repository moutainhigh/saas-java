package com.hq.chainMS.service.storeGoods.bs;

import java.util.Set;

import com.hq.storeClient.service.storeGoods.apiData.GoodsBatchUpdateStateForm;
import com.hq.storeClient.service.storeGoods.apiData.GoodsUpdateStateForm;
import com.hq.storeClient.service.storeGoods.data.StoreGoods;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreGoodsMgr {

	public static StoreGoodsMgr getInstance() {
		return HotSwap.getInstance().getSingleton(StoreGoodsMgr.class);
	}
	
	public StoreGoods getStoreGoods(long storeId) {
		return StoreGoodsDataHolder.getInstance().getStoreGoods(storeId);
	}
	
	public void updateGoodsState(Set<Long> applyStoreIds, String id, int state) {
		for (Long storeId : applyStoreIds) {
			GoodsUpdateStateForm data = GoodsUpdateStateForm.newInstance();
			data.setGoodsId(id);
			data.setState(state);
			updateGoodsState(storeId, data);
		}
	}
	
	public void batchUpdateGoodsState(long storeId, GoodsBatchUpdateStateForm dataForm) {
		StoreGoodsDataHolder.getInstance().batchUpdateGoodsState(storeId, dataForm);
	}
	
	public void updateGoodsState(long storeId, GoodsUpdateStateForm dataForm) {
		StoreGoodsDataHolder.getInstance().updateGoodsState(storeId, dataForm);
	}
}

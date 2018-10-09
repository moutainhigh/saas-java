package com.hq.storeMS.service.storeGoods.bs;

import com.hq.storeMS.service.storeGoods.data.StoreGoods;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreGoodsMgr {

	public static StoreGoodsMgr getInstance() {
		return HotSwap.getInstance().getSingleton(StoreGoodsMgr.class);
	}

	public void addWithId(StoreGoods target) {
		StoreGoodsDataHolder.getInstance().addWithId(target);
	}

	public void delete(StoreGoods target) {
		StoreGoodsDataHolder.getInstance().delete(target);
	}

	public void update(StoreGoods target) {
		StoreGoodsDataHolder.getInstance().update(target);
	}
	
	public StoreGoods getByStoreId(long storeId) {
		StoreGoods storeGoodsInfo = StoreGoodsDataHolder.getInstance().get(storeId);
		if(storeGoodsInfo == null){//不存在，则新增
			storeGoodsInfo = StoreGoods.newInstance(storeId);
			StoreGoodsDataHolder.getInstance().addWithId(storeGoodsInfo);
		}
		return storeGoodsInfo;
	}
}

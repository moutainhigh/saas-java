package com.hq.customerMS.service.storeGoods.bs;

import com.hq.storeClient.service.storeGoods.data.StoreGoods;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreGoodsMgr {

	public static StoreGoodsMgr getInstance() {
		return HotSwap.getInstance().getSingleton(StoreGoodsMgr.class);
	}

	public StoreGoods getByStoreId(long storeId) {
		return StoreGoodsDataHolder.getInstance().get(storeId);
	}

}

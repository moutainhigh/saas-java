package com.hq.customerRestClient.service.storeGoods.bs;

import com.hq.customerRestClient.service.storeGoods.data.StoreGoods;
import com.hq.customerRestClient.service.storeGoods.data.StoreGoodsDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreGoodsClientMgr {

	public static StoreGoodsClientMgr getInstance() {
		return HotSwap.getInstance().getSingleton(StoreGoodsClientMgr.class);
	}

	public StoreGoods get(long storeId) {
		return StoreGoodsDAO.getInstance().get(storeId);
	}

}

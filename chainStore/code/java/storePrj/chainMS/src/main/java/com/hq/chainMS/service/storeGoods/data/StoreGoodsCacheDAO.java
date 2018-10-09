package com.hq.chainMS.service.storeGoods.data;

import com.hq.storeClient.service.storeGoods.data.StoreGoods;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreGoodsCacheDAO {

	public static StoreGoodsCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(StoreGoodsCacheDAO.class);
	}

	public StoreGoods get(long id) {
		return StoreGoodsRedisDAO.getInstance().get(id);
	}

}

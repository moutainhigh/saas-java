package com.hq.storeMS.service.chainGoods.data;

import com.hq.chainClient.service.chainGoods.data.ChainGoods;
import com.zenmind.common.hotSwap.HotSwap;

public class ChainGoodsCacheDAO {

	public static ChainGoodsCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(ChainGoodsCacheDAO.class);
	}

	public ChainGoods get(long id) {
		return ChainGoodsRedisDAO.getInstance().get(id);
	}
	
}

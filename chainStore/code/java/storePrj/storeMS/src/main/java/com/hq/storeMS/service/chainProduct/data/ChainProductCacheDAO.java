package com.hq.storeMS.service.chainProduct.data;

import com.hq.chainClient.service.chainProduct.data.ChainProduct;
import com.zenmind.common.hotSwap.HotSwap;

public class ChainProductCacheDAO {

	public static ChainProductCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(ChainProductCacheDAO.class);
	}

	public ChainProduct get(long id) {
		return ChainProductRedisDAO.getInstance().get(id);
	}
}

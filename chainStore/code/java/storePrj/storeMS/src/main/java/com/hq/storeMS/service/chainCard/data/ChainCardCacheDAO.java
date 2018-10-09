package com.hq.storeMS.service.chainCard.data;

import com.hq.chainClient.service.chainCard.data.ChainCard;
import com.zenmind.common.hotSwap.HotSwap;

public class ChainCardCacheDAO {

	public static ChainCardCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(ChainCardCacheDAO.class);
	}
	
	public ChainCard get(long id) {
		return ChainCardRedisDAO.getInstance().get(id);
	}
	
}

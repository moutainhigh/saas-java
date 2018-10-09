package com.hq.storeMS.service.chain.data;

import com.hq.chainClient.service.chain.data.Chain;
import com.zenmind.common.hotSwap.HotSwap;

public class ChainCacheDAO {

	public static ChainCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(ChainCacheDAO.class);
	}
	
	final private String groupName = "chain";
	
	public Chain get(Object id) {
		return ChainRedisDAO.getInstance().get(id);
	}
	
	public Chain getOne(String key) {
		return ChainRedisDAO.getInstance().findByOne(groupName, key);
	}
}

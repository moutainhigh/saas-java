package com.hq.storeManagerMS.service.storeMenu.data;

import com.hq.storeClient.service.storeMenu.data.StoreMenu;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class StoreMenuRedisDAO extends RedisDao<StoreMenu> {

	public static StoreMenuRedisDAO getInstance(){
		return HotSwap.getInstance().getSingleton(StoreMenuRedisDAO.class);
	}
	
}

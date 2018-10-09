package com.hq.storeMS.service.storeMaterialInfo.data;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class StoreMaterialInfoRedisDAO extends RedisDao<StoreMaterialInfo> {

	public static StoreMaterialInfoRedisDAO getInstance(){
		return HotSwap.getInstance().getSingleton(StoreMaterialInfoRedisDAO.class);
	}
	
}

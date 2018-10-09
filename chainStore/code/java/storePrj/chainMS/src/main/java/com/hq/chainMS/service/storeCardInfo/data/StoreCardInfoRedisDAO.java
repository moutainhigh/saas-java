package com.hq.chainMS.service.storeCardInfo.data;

import com.hq.storeClient.service.storeCardInfo.data.StoreCardInfo;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class StoreCardInfoRedisDAO extends RedisDao<StoreCardInfo> {

	public static StoreCardInfoRedisDAO getInstance(){
		return HotSwap.getInstance().getSingleton(StoreCardInfoRedisDAO.class);
	}
	
}

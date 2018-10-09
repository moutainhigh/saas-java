package com.hq.storeMS.service.beauticianProduct.data;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class BeauticianProductRedisDAO extends RedisDao<BeauticianProduct> {

	public static BeauticianProductRedisDAO getInstance(){
		return HotSwap.getInstance().getSingleton(BeauticianProductRedisDAO.class);
	}
}

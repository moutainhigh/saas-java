package com.hq.storeMS.service.wxJscode.data;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class WxJscodeRedisDAO extends RedisDao<WxJscode>{
	public static WxJscodeRedisDAO getInstance() {
		return HotSwap.getInstance().getSingleton(WxJscodeRedisDAO.class);
	}
}

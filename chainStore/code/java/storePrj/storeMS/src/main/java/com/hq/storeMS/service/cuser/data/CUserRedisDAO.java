package com.hq.storeMS.service.cuser.data;

import com.hq.customerRestClient.service.cuser.data.CUser;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class CUserRedisDAO extends RedisDao<CUser> {

	public static CUserRedisDAO getInstance(){
		return HotSwap.getInstance().getSingleton(CUserRedisDAO.class);
	}
	
}

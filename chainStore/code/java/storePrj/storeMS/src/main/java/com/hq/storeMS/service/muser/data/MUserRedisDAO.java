package com.hq.storeMS.service.muser.data;

import com.hq.storeManagerRestClient.service.muser.data.MUser;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class MUserRedisDAO extends RedisDao<MUser> {

	public static MUserRedisDAO getInstance(){
		return HotSwap.getInstance().getSingleton(MUserRedisDAO.class);
	}
	
}

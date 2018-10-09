package com.hq.storeMS.service.clerkSalary.data;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class ClerkSalaryRedisDAO extends RedisDao<ClerkSalary> {

	public static ClerkSalaryRedisDAO getInstance(){
		return HotSwap.getInstance().getSingleton(ClerkSalaryRedisDAO.class);
	}
	
}

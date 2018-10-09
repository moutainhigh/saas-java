package com.hq.storeManagerMS.service.muserAdminRole.data;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class MUserAdminRoleRedisDAO extends RedisDao<MUserAdminRole> {

	public static MUserAdminRoleRedisDAO getInstance(){
		return HotSwap.getInstance().getSingleton(MUserAdminRoleRedisDAO.class);
	}
	
}

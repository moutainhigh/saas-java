package com.hq.storeMS.service.materialRecords.data;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class MaterialRecordsRedisDAO extends RedisDao<MaterialRecords> {

	public static MaterialRecordsRedisDAO getInstance(){
		return HotSwap.getInstance().getSingleton(MaterialRecordsRedisDAO.class);
	}
	
}

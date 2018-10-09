package com.hq.storeMS.service.billRecord.data;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class BillRecordRedisDAO extends RedisDao<BillRecord>{
	public static BillRecordRedisDAO getInstance(){
		return HotSwap.getInstance().getSingleton(BillRecordRedisDAO.class);
	}	
}

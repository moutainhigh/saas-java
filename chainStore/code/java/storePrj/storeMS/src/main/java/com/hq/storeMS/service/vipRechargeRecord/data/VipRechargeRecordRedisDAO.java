package com.hq.storeMS.service.vipRechargeRecord.data;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class VipRechargeRecordRedisDAO extends RedisDao<VipRechargeRecord> {

	public static VipRechargeRecordRedisDAO getInstance() {
		return HotSwap.getInstance().getSingleton(VipRechargeRecordRedisDAO.class);
	}

}

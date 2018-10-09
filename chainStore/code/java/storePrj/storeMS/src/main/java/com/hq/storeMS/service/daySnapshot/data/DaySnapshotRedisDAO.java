package com.hq.storeMS.service.daySnapshot.data;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class DaySnapshotRedisDAO extends RedisDao<DaySnapshot> {

	public static DaySnapshotRedisDAO getInstance() {
		return HotSwap.getInstance().getSingleton(DaySnapshotRedisDAO.class);
	}
}

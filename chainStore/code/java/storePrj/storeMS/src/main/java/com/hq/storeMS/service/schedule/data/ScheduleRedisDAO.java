package com.hq.storeMS.service.schedule.data;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class ScheduleRedisDAO extends RedisDao<Schedule> {

	public static ScheduleRedisDAO getInstance() {
		return HotSwap.getInstance().getSingleton(ScheduleRedisDAO.class);
	}

}

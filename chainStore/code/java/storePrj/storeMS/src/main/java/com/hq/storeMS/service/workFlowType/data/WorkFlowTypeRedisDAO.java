package com.hq.storeMS.service.workFlowType.data;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class WorkFlowTypeRedisDAO extends RedisDao<WorkFlowType> {
	public static WorkFlowTypeRedisDAO getInstance(){
		return HotSwap.getInstance().getSingleton(WorkFlowTypeRedisDAO.class);
	}
}

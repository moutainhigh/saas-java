package com.hq.storeMS.service.chainCard.data;

import com.hq.chainClient.service.chainCard.data.MembershipCardDetail;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class MembershipCardDetailRedisDAO extends RedisDao<MembershipCardDetail> {

	public static MembershipCardDetailRedisDAO getInstance() {
		return HotSwap.getInstance().getSingleton(MembershipCardDetailRedisDAO.class);
	}

}

package com.hq.customerMS.service.membershipCardDetail.data;

import com.hq.storeClient.service.membershipCardDetail.data.MembershipCardDetail;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class MembershipCardDetailRedisDAO extends RedisDao<MembershipCardDetail> {

	public static MembershipCardDetailRedisDAO getInstance() {
		return HotSwap.getInstance().getSingleton(MembershipCardDetailRedisDAO.class);
	}
}

package com.hq.storeMS.service.chainCard.data;

import com.hq.chainClient.service.chainCard.data.MembershipCardDetail;
import com.hq.storeMS.common.util.AppUtils;
import com.zenmind.common.hotSwap.HotSwap;

public class MembershipCardDetailCacheDAO {

	public static MembershipCardDetailCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(MembershipCardDetailCacheDAO.class);
	}

	final private String suffix = "chainMembershipCardDetail";

	public MembershipCardDetail get(long chainId, String id) {
		return MembershipCardDetailRedisDAO.getInstance().findByOne(getGroupName(chainId), id);
	}

	private String getGroupName(long chainId) {
		return AppUtils.joinByUnderline(suffix, chainId);
	}
}

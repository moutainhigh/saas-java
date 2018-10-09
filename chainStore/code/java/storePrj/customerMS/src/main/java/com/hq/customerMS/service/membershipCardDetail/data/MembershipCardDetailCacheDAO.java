package com.hq.customerMS.service.membershipCardDetail.data;

import com.hq.customerMS.common.util.AppUtils;
import com.hq.storeClient.service.membershipCardDetail.data.MembershipCardDetail;
import com.zenmind.common.hotSwap.HotSwap;

public class MembershipCardDetailCacheDAO {

	public static MembershipCardDetailCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(MembershipCardDetailCacheDAO.class);
	}

	final private String suffix = "storeMembershipCardDetail";

	public MembershipCardDetail get(long storeId, String id) {
		return MembershipCardDetailRedisDAO.getInstance().findByOne(getGroupName(storeId), id);
	}

	private String getGroupName(Object storeId) {
		return AppUtils.joinByUnderline(suffix, storeId);
	}
}

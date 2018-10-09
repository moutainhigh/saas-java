package com.hq.storeMS.service.membershipCardDetail.data;

import com.zenmind.common.hotSwap.HotSwap;

public class MembershipCardDetailBeanHelper {
	public static MembershipCardDetailBeanHelper getInstance() {
		return HotSwap.getInstance().getSingleton(MembershipCardDetailBeanHelper.class);
	}
}

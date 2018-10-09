package com.hq.chainMS.service.chainCard.data;

import com.zenmind.common.hotSwap.HotSwap;

public class MembershipCardDetailBeanHelper {
	public static MembershipCardDetailBeanHelper getInstance() {
		return HotSwap.getInstance().getSingleton(MembershipCardDetailBeanHelper.class);
	}
}

package com.hq.customerMS.service.membershipCardDetail.bs;

import com.hq.storeClient.service.membershipCardDetail.data.MembershipCardDetail;
import com.zenmind.common.hotSwap.HotSwap;

public class MembershipCardDetailMgr {

	public static MembershipCardDetailMgr getInstance() {
		return HotSwap.getInstance().getSingleton(MembershipCardDetailMgr.class);
	}

	public MembershipCardDetail get(long storeId,String membershipCardDetailId) {
		return MembershipCardDetailDataHolder.getInstance().get(storeId,membershipCardDetailId);
	}
	
}




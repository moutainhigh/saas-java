package com.hq.customerMS.service.membershipCardDetail.bs;

import com.hq.customerMS.common.constants.ServerConstants;
import com.hq.customerMS.common.validate.AppIdThreadLocal;
import com.hq.customerMS.service.membershipCardDetail.data.MembershipCardDetailCacheDAO;
import com.hq.storeClient.service.membershipCardDetail.bs.MembershipCardDetailClientMgr;
import com.hq.storeClient.service.membershipCardDetail.data.MembershipCardDetail;
import com.zenmind.common.hotSwap.HotSwap;

public class MembershipCardDetailDataHolder {
	
	public static MembershipCardDetailDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(MembershipCardDetailDataHolder.class);
	}
	
	public MembershipCardDetail get(long storeId,String membershipCardDetailId) {
		MembershipCardDetail data = MembershipCardDetailCacheDAO.getInstance().get(storeId, membershipCardDetailId);
		if(data != null) {
			return data;
		}
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		data = MembershipCardDetailClientMgr.getInstance().getMembershipCardDetail(storeId,membershipCardDetailId);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}
	
}

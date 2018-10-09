package com.hq.storeClient.service.membershipCardDetail.bs;

import com.hq.storeClient.common.restClientResp.PageResp;
import com.hq.storeClient.common.utils.StringUtils4Client;
import com.hq.storeClient.service.membershipCardDetail.apiData.MembershipCardDetailQueryForm;
import com.hq.storeClient.service.membershipCardDetail.data.MembershipCardDetail;
import com.hq.storeClient.service.membershipCardDetail.data.MembershipCardDetailDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class MembershipCardDetailClientMgr {

	public static MembershipCardDetailClientMgr getInstance() {
		return HotSwap.getInstance().getSingleton(MembershipCardDetailClientMgr.class);
	}
	
	public PageResp<MembershipCardDetail> getMembershipCardDetailPageInfo(MembershipCardDetailQueryForm queryForm) {
		final String findPath = "getMembershipCardDetailPageInfo";
		return MembershipCardDetailDAO.getInstance().getMembershipCardDetailPageInfo(findPath, queryForm);
	}

	public MembershipCardDetail getMembershipCardDetail(long storeId, String membershipCardDetailId) {
		String uriPath = StringUtils4Client.format("{}/{}", storeId, membershipCardDetailId);
		return MembershipCardDetailDAO.getInstance().findOne(uriPath);
	}

}

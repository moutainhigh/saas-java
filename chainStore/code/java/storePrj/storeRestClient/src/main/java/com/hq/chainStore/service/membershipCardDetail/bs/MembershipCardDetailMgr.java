package com.hq.chainStore.service.membershipCardDetail.bs;

import com.hq.chainStore.service.common.PageResp;
import com.hq.chainStore.service.membershipCardDetail.apiData.MembershipCardDetailQueryForm;
import com.hq.chainStore.service.membershipCardDetail.data.MembershipCardDetail;
import com.hq.chainStore.service.membershipCardDetail.data.MembershipCardDetailDAO;
import com.hq.common.StringUtils4Client;
import com.zenmind.common.hotSwap.HotSwap;

public class MembershipCardDetailMgr {

	public static MembershipCardDetailMgr getInstance() {
		return HotSwap.getInstance().getSingleton(MembershipCardDetailMgr.class);
	}
	
	public PageResp<MembershipCardDetail> getMembershipCardDetailPageInfo(MembershipCardDetailQueryForm queryForm) {
		final String findPath = "getMembershipCardDetailPageInfo";
		return MembershipCardDetailDAO.getInstance().getMembershipCardDetailPageInfo(findPath, queryForm);
	}
	
	@Deprecated
	public MembershipCardDetail getMembershipCardDetail(String membershipCardDetailId) {
		return MembershipCardDetailDAO.getInstance().get(membershipCardDetailId);
	}
	
	public MembershipCardDetail getMembershipCardDetail(long storeId, String membershipCardDetailId) {
		String uriPath = StringUtils4Client.format("{}/{}", storeId, membershipCardDetailId);
		return MembershipCardDetailDAO.getInstance().findOne(uriPath);
	}
}

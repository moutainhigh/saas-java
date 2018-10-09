package com.hq.chainClient.service.chainCard.bs;

import com.hq.chainClient.common.restClientResp.PageResp;
import com.hq.chainClient.service.chainCard.apiData.MembershipCardDetailQueryForm;
import com.hq.chainClient.service.chainCard.data.MembershipCardDetail;
import com.hq.chainClient.service.chainCard.data.MembershipCardDetailDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class MembershipCardDetailClientMgr {

	public static MembershipCardDetailClientMgr getInstance() {
		return HotSwap.getInstance().getSingleton(MembershipCardDetailClientMgr.class);
	}
	
	public PageResp<MembershipCardDetail> getMembershipCardDetailPageInfo(MembershipCardDetailQueryForm queryForm) {
		final String findPath = "getMembershipCardDetailPageInfo";
		return MembershipCardDetailDAO.getInstance().getMembershipCardDetailPageInfo(findPath, queryForm);
	}
	
	public MembershipCardDetail getMembershipCardDetail(long chainId, String detailId) {
		return MembershipCardDetailDAO.getInstance().get(chainId+"/"+detailId);
	}
}

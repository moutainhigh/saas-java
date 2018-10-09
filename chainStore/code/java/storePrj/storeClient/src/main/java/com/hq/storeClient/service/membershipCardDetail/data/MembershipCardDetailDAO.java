package com.hq.storeClient.service.membershipCardDetail.data;

import com.hq.storeClient.common.restClientResp.PageResp;
import com.hq.storeClient.common.restClientResp.RestClientCfg;
import com.hq.storeClient.common.utils.JsonUtil4Client;
import com.hq.storeClient.service.membershipCardDetail.apiData.MembershipCardDetailQueryForm;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;
import com.zenmind.dao.rest.RestResp;

public class MembershipCardDetailDAO extends RestDao<MembershipCardDetail> {

	public static MembershipCardDetailDAO getInstance() {
		return HotSwap.getInstance().getSingleton(MembershipCardDetailDAO.class);
	}
	
	@Override
	public String getService() {
		return RestClientCfg.getService();
	}
	
	public PageResp<MembershipCardDetail> getMembershipCardDetailPageInfo(String findPath, MembershipCardDetailQueryForm queryForm) {
		RestResp restResp = super.rawGetReq(findPath, queryForm.toReqMap(), queryForm.getPageItemCount(), queryForm.getPageNo());
		return JsonUtil4Client.getInstance().parseTPage(restResp.gettJson(), MembershipCardDetail.class);
	}

}

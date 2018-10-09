package com.hq.chainStore.service.membershipCardDetail.data;

import com.hq.chainStore.service.common.PageResp;
import com.hq.chainStore.service.common.RestClientCfg;
import com.hq.chainStore.service.membershipCardDetail.apiData.MembershipCardDetailQueryForm;
import com.hq.common.JsonUtil4Client;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;
import com.zenmind.dao.rest.RestDaoException;
import com.zenmind.dao.rest.RestProxyException;
import com.zenmind.dao.rest.RestResp;

public class MembershipCardDetailDAO extends RestDao<MembershipCardDetail> {

	public static MembershipCardDetailDAO getInstance(){
		return HotSwap.getInstance().getSingleton(MembershipCardDetailDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getStoreService();
	}

	public PageResp<MembershipCardDetail> getMembershipCardDetailPageInfo(String findPath, MembershipCardDetailQueryForm queryForm) {
		try {
			RestResp restResp = super.rawGetReq(findPath, queryForm.toReqMap(), queryForm.getPageItemCount(), queryForm.getPageNo());
			return JsonUtil4Client.getInstance().parseTPage(restResp.gettJson(), MembershipCardDetail.class);
		} catch (Exception e) {
			if(e instanceof RestProxyException){
				throw((RestProxyException)e);
			}
			throw(RestDaoException.newInstance("MembershipCardDetailDAO.getMembershipCardDetailPageInfo()", "membershipCardDetail", "", e));
		}
	}
	
}

package com.hq.chainStore.service.leaguerDetail.bs;

import com.hq.chainStore.service.common.PageResp;
import com.hq.chainStore.service.leaguerDetail.apiData.LeaguerDetailQueryForm;
import com.hq.chainStore.service.leaguerDetail.data.LeaguerDetail;
import com.hq.chainStore.service.leaguerDetail.data.LeaguerDetailDAO;
import com.hq.chainStore.service.leaguerDetail.data.MemberCardExist;
import com.hq.common.StringUtils4Client;
import com.zenmind.common.hotSwap.HotSwap;

public class LeaguerDetailMgr {

	public static LeaguerDetailMgr getInstance() {
		return HotSwap.getInstance().getSingleton(LeaguerDetailMgr.class);
	}
	
	public PageResp<LeaguerDetail> getLeaguerDetailPageInfo(LeaguerDetailQueryForm queryForm) {
		final String findPath = "getLeaguerDetailPageInfo";
		return LeaguerDetailDAO.getInstance().getLeaguerDetailPageInfo(findPath, queryForm);
	}
	
	public MemberCardExist checkMemberCardExist(String number, long storeId) {
		final String findPath = "checkMemberCardExist";
		return LeaguerDetailDAO.getInstance().checkMemberCardExist(findPath, number, storeId);
	}
	
	@Deprecated
	public LeaguerDetail getLeaguerDetail(String leaguerDetailId) {
		return LeaguerDetailDAO.getInstance().get(leaguerDetailId);
	}
	
	public LeaguerDetail getLeaguerDetail(long storeId, String leaguerDetailId) {
		String uriPath = StringUtils4Client.format("{}/{}", storeId, leaguerDetailId);
		return LeaguerDetailDAO.getInstance().findOne(uriPath);
	}
}

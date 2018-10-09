package com.hq.storeClient.service.leaguerDetail.bs;

import com.hq.storeClient.common.restClientResp.PageResp;
import com.hq.storeClient.common.utils.StringUtils4Client;
import com.hq.storeClient.service.leaguerDetail.apiData.LeaguerDetailQueryForm;
import com.hq.storeClient.service.leaguerDetail.data.LeaguerDetail;
import com.hq.storeClient.service.leaguerDetail.data.LeaguerDetailDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class LeaguerDetailClientMgr {

	public static LeaguerDetailClientMgr getInstance() {
		return HotSwap.getInstance().getSingleton(LeaguerDetailClientMgr.class);
	}
	
	public PageResp<LeaguerDetail> getLeaguerDetailPageInfo(LeaguerDetailQueryForm queryForm) {
		final String findPath = "getLeaguerDetailPageInfo";
		return LeaguerDetailDAO.getInstance().getLeaguerDetailPageInfo(findPath, queryForm);
	}
	
	public LeaguerDetail get(long storeId, String leaguerDetailId) {
		String uriPath = StringUtils4Client.format("{}/{}", storeId, leaguerDetailId);
		return LeaguerDetailDAO.getInstance().findOne(uriPath);
	}
	
	//测试专用
	@Deprecated
	public void triggerNotice(long storeId) {
		String uriPath = StringUtils4Client.format("triggerNotice/{}", storeId);
		LeaguerDetailDAO.getInstance().findOne(uriPath);
	}
}

package com.hq.storeClient.service.leaguerDetail.data;

import com.hq.storeClient.common.restClientResp.PageResp;
import com.hq.storeClient.common.restClientResp.RestClientCfg;
import com.hq.storeClient.common.utils.JsonUtil4Client;
import com.hq.storeClient.service.leaguerDetail.apiData.LeaguerDetailQueryForm;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;
import com.zenmind.dao.rest.RestResp;

public class LeaguerDetailDAO extends RestDao<LeaguerDetail> {

	public static LeaguerDetailDAO getInstance() {
		return HotSwap.getInstance().getSingleton(LeaguerDetailDAO.class);
	}
	
	@Override
	public String getService() {
		return RestClientCfg.getService();
	}
	
	public PageResp<LeaguerDetail> getLeaguerDetailPageInfo(String findPath, LeaguerDetailQueryForm queryForm) {
		RestResp restResp = super.rawGetReq(findPath, queryForm.toReqMap(), queryForm.getPageItemCount(), queryForm.getPageNo());
		return JsonUtil4Client.getInstance().parseTPage(restResp.gettJson(), LeaguerDetail.class);
	}

}

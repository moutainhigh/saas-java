package com.hq.customerRestClient.service.leaguerDetail.bs;

import com.hq.customerRestClient.common.util.StringUtils4Client;
import com.hq.customerRestClient.service.leaguerDetail.data.LeaguerDetail;
import com.hq.customerRestClient.service.leaguerDetail.data.LeaguerDetailDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class LeaguerDetailClientMgr {

	public static LeaguerDetailClientMgr getInstance() {
		return HotSwap.getInstance().getSingleton(LeaguerDetailClientMgr.class);
	}
	
	public LeaguerDetail get(long storeId, String leaguerDetailId) {
		String uriPath = StringUtils4Client.format("{}/{}", storeId, leaguerDetailId);
		return LeaguerDetailDAO.getInstance().findOne(uriPath);
	}
	
}

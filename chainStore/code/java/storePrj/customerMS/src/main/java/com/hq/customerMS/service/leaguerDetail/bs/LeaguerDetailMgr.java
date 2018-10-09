package com.hq.customerMS.service.leaguerDetail.bs;

import com.hq.storeClient.service.leaguerDetail.data.LeaguerDetail;
import com.zenmind.common.StringFormatUtil;
import com.zenmind.common.hotSwap.HotSwap;

public class LeaguerDetailMgr {

	public static LeaguerDetailMgr getInstance() {
		return HotSwap.getInstance().getSingleton(LeaguerDetailMgr.class);
	}

	public LeaguerDetail get(long storeId, String leaguerDetailId) {
		return LeaguerDetailDataHolder.getInstance().get(storeId, leaguerDetailId);
	}
	
	public LeaguerDetail get(long storeId, long cuserId) {
		String leaguerId = StringFormatUtil.format("{}_{}", storeId, cuserId);
		return get(storeId, leaguerId);
	}

}

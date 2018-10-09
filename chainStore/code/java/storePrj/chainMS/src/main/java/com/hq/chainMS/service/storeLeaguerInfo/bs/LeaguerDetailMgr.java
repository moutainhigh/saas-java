package com.hq.chainMS.service.storeLeaguerInfo.bs;

import com.hq.storeClient.service.leaguerDetail.data.LeaguerDetail;
import com.zenmind.common.hotSwap.HotSwap;

public class LeaguerDetailMgr {

	public static LeaguerDetailMgr getInstance() {
		return HotSwap.getInstance().getSingleton(LeaguerDetailMgr.class);
	}

	public LeaguerDetail get(long storeId, String leaguerDetailId) {
		return LeaguerDetailDataHolder.getInstance().get(storeId, leaguerDetailId);
	}

}

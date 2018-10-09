package com.hq.customerMS.service.leaguerDetail.data;

import com.hq.customerMS.common.util.AppUtils;
import com.hq.storeClient.service.leaguerDetail.data.LeaguerDetail;
import com.zenmind.common.hotSwap.HotSwap;

public class LeaguerDetailCacheDAO {

	public static LeaguerDetailCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(LeaguerDetailCacheDAO.class);
	}

	final private String suffix = "storeLeaguerDetail";

	public LeaguerDetail get(long storeId, String id) {
		return LeaguerDetailRedisDAO.getInstance().findByOne(getGroupName(storeId), id);
	}

	private String getGroupName(Object storeId) {
		return AppUtils.joinByUnderline(suffix, storeId);
	}
}

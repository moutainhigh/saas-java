package com.hq.customerMS.service.buser.data;

import com.hq.storeClient.service.buser.data.BUser;
import com.zenmind.common.hotSwap.HotSwap;

public class BUserCacheDAO {

	public static BUserCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(BUserCacheDAO.class);
	}

	public BUser get(long id) {
		return BUserRedisDAO.getInstance().get(id);
	}
}

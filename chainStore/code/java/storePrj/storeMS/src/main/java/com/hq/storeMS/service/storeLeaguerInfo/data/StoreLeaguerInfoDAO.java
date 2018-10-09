package com.hq.storeMS.service.storeLeaguerInfo.data;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.mongodb.MongodbDao;

public class StoreLeaguerInfoDAO extends MongodbDao<StoreLeaguerInfo> {
	public static StoreLeaguerInfoDAO getInstance() {
		return HotSwap.getInstance().getSingleton(StoreLeaguerInfoDAO.class);
	}
}

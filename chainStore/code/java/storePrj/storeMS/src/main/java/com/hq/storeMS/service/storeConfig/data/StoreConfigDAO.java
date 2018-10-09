package com.hq.storeMS.service.storeConfig.data;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.mongodb.MongodbDao;

public class StoreConfigDAO extends MongodbDao<StoreConfig> {
	public static StoreConfigDAO getInstance() {
		return HotSwap.getInstance().getSingleton(StoreConfigDAO.class);
	}
}

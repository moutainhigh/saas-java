package com.hq.storeMS.service.storeProductInfo.data;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.mongodb.MongodbDao;

public class StoreProductInfoDAO extends MongodbDao<StoreProductInfo> {

	public static StoreProductInfoDAO getInstance() {
		return HotSwap.getInstance().getSingleton(StoreProductInfoDAO.class);
	}
}

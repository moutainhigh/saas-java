package com.hq.storeMS.service.storeClerkInfo.data;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.mongodb.MongodbDao;

public class StoreClerkInfoDAO extends MongodbDao<StoreClerkInfo> {

	public static StoreClerkInfoDAO getInstance(){
		return HotSwap.getInstance().getSingleton(StoreClerkInfoDAO.class);
	}

	
}

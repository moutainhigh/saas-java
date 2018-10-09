package com.hq.storeMS.service.storeBeauticianInfo.data;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.mongodb.MongodbDao;

public class StoreBeauticianInfoDAO extends MongodbDao<StoreBeauticianInfo> {

	public static StoreBeauticianInfoDAO getInstance(){
		return HotSwap.getInstance().getSingleton(StoreBeauticianInfoDAO.class);
	}

	
}

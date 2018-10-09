package com.hq.orderMS.service.store.data;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.mongodb.MongodbDao;

public class StoreDAO extends MongodbDao<Store> {

	public static StoreDAO getInstance(){
		return HotSwap.getInstance().getSingleton(StoreDAO.class);
	}
	
}

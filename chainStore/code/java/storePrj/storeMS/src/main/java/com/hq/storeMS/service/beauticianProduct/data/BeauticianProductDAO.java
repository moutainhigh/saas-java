package com.hq.storeMS.service.beauticianProduct.data;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.mongodb.MongodbDao;

public class BeauticianProductDAO extends MongodbDao<BeauticianProduct> {

	public static BeauticianProductDAO getInstance(){
		return HotSwap.getInstance().getSingleton(BeauticianProductDAO.class);
	}
	
}

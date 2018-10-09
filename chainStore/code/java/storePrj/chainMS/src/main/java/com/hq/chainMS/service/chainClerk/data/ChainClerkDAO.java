package com.hq.chainMS.service.chainClerk.data;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.mongodb.MongodbDao;

public class ChainClerkDAO extends MongodbDao<ChainClerk> {

	public static ChainClerkDAO getInstance() {
		return HotSwap.getInstance().getSingleton(ChainClerkDAO.class);
	}

}

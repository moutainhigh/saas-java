package com.hq.chainMS.service.chainCard.data;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.mongodb.MongodbDao;

public class ChainCardDAO extends MongodbDao<ChainCard> {

	public static ChainCardDAO getInstance() {
		return HotSwap.getInstance().getSingleton(ChainCardDAO.class);
	}
}

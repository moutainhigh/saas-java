package com.hq.chainMS.service.chainProduct.data;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.mongodb.MongodbDao;

public class ChainProductDAO extends MongodbDao<ChainProduct> {

	public static ChainProductDAO getInstance() {
		return HotSwap.getInstance().getSingleton(ChainProductDAO.class);
	}

}

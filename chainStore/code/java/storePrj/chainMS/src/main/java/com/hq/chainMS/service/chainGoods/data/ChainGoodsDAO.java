package com.hq.chainMS.service.chainGoods.data;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.mongodb.MongodbDao;

public class ChainGoodsDAO extends MongodbDao<ChainGoods> {

	public static ChainGoodsDAO getInstance() {
		return HotSwap.getInstance().getSingleton(ChainGoodsDAO.class);
	}

}

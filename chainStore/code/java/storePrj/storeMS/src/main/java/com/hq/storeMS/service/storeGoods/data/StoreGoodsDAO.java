package com.hq.storeMS.service.storeGoods.data;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.mongodb.MongodbDao;

public class StoreGoodsDAO extends MongodbDao<StoreGoods> {
	public static StoreGoodsDAO getInstance() {
		return HotSwap.getInstance().getSingleton(StoreGoodsDAO.class);
	}
}

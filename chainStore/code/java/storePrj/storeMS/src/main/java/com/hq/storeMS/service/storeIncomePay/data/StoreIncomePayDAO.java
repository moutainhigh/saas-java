package com.hq.storeMS.service.storeIncomePay.data;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.mongodb.MongodbDao;

public class StoreIncomePayDAO extends MongodbDao<StoreIncomePay> {
	public static StoreIncomePayDAO getInstance() {
		return HotSwap.getInstance().getSingleton(StoreIncomePayDAO.class);
	}
}

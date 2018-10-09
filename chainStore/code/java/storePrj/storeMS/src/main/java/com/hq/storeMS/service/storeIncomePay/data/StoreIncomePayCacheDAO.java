package com.hq.storeMS.service.storeIncomePay.data;

import com.zenmind.common.hotSwap.HotSwap;

public class StoreIncomePayCacheDAO {

	public static StoreIncomePayCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(StoreIncomePayCacheDAO.class);
	}

	public void save(StoreIncomePay target) {
		StoreIncomePayRedisDAO.getInstance().save(target);
	}
	
	public StoreIncomePay get(long id) {
		return StoreIncomePayRedisDAO.getInstance().get(id);
	}

	public void delete(StoreIncomePay target) {
		StoreIncomePayRedisDAO.getInstance().delete(target.getId());
	}
}

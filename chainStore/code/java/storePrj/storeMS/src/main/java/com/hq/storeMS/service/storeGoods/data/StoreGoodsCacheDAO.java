package com.hq.storeMS.service.storeGoods.data;

import com.zenmind.common.hotSwap.HotSwap;

public class StoreGoodsCacheDAO {

	public static StoreGoodsCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(StoreGoodsCacheDAO.class);
	}

	public void save(StoreGoods target) {
		StoreGoodsRedisDAO.getInstance().save(target);
	}
	
	public StoreGoods get(long id) {
		return StoreGoodsRedisDAO.getInstance().get(id);
	}

	public void delete(StoreGoods target) {
		StoreGoodsRedisDAO.getInstance().delete(target.getId());
	}
}

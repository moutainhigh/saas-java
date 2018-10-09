package com.hq.chainMS.service.chainGoods.data;

import com.zenmind.common.hotSwap.HotSwap;

public class ChainGoodsCacheDAO {

	public static ChainGoodsCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(ChainGoodsCacheDAO.class);
	}

	public void save(ChainGoods target) {
		ChainGoodsRedisDAO.getInstance().save(target);
	}
	
	public ChainGoods get(long id) {
		return ChainGoodsRedisDAO.getInstance().get(id);
	}

	public void delete(ChainGoods target) {
		ChainGoodsRedisDAO.getInstance().delete(target.getId());
	}
	
}

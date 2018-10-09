package com.hq.chainStore.service.chainGoods.bs;

import com.hq.chainStore.service.chainGoods.data.ChainGoods;
import com.hq.chainStore.service.chainGoods.data.ChainGoodsDAO;
import com.hq.chainStore.service.chainGoods.data.GoodsDetail;
import com.zenmind.common.hotSwap.HotSwap;

public class ChainGoodsMgr {

	public static ChainGoodsMgr getInstance(){
		return HotSwap.getInstance().getSingleton(ChainGoodsMgr.class);
	}
	
	public ChainGoods get(long chainId) {
		return ChainGoodsDAO.getInstance().get(chainId);
	}
	
	public GoodsDetail findGoodsDetail(String goodsId, long chainId) {
		return ChainGoodsDAO.getInstance().findGoodsDetail(goodsId, chainId);
	}
	
}

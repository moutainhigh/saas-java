package com.hq.storeMS.service.chainGoods.bs;

import com.hq.chainClient.service.chainGoods.data.ChainGoods;
import com.hq.chainClient.service.chainGoods.data.GoodsDetail;
import com.zenmind.common.hotSwap.HotSwap;

public class ChainGoodsMgr {

	public static ChainGoodsMgr getInstance() {
		return HotSwap.getInstance().getSingleton(ChainGoodsMgr.class);
	}

	public ChainGoods getChainGoods(long chainId) {
		return ChainGoodsDataHolder.getInstance().get(chainId);
	}

	public GoodsDetail getGoodsDetail(String goodsId, long chainId) {
		return ChainGoodsDataHolder.getInstance().getGoodsDetail(goodsId, chainId);
	}
}

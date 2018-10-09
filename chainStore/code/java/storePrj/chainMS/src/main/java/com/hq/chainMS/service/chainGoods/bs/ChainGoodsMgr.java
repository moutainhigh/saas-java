package com.hq.chainMS.service.chainGoods.bs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.hq.chainMS.service.chainGoods.data.ChainGoods;
import com.hq.chainMS.service.chainGoods.data.Goods;
import com.hq.chainMS.service.common.EntityState;
import com.hq.chainMS.service.sellProduct.data.SellProduct;
import com.zenmind.common.hotSwap.HotSwap;

public class ChainGoodsMgr {

	public static ChainGoodsMgr getInstance() {
		return HotSwap.getInstance().getSingleton(ChainGoodsMgr.class);
	}

	public void addWithId(ChainGoods target) {
		ChainGoodsDataHolder.getInstance().addWithId(target);
	}

	public void delete(ChainGoods target) {
		ChainGoodsDataHolder.getInstance().delete(target);
	}

	public void update(ChainGoods target) {
		ChainGoodsDataHolder.getInstance().update(target);
	}
	
	public List<SellProduct> getSellProducts(long chainId){
		List<SellProduct> result = new ArrayList<SellProduct>();
		ChainGoods chainData = get(chainId);
		Collection<Goods> array = chainData.getGoodsMap().values();
		for (Goods data : array) {
			if(data.getEntityState() == EntityState.Deleted.ordinal()) {
				continue;
			}
			result.add(data.toSellProduct());
		}
		return result;
	} 

	public ChainGoods get(long chainId) {
		ChainGoods chainGoods = ChainGoodsDataHolder.getInstance().get(chainId);
		if(chainGoods == null){//不存在，则新增
			chainGoods = ChainGoods.newInstance(chainId);
			ChainGoodsDataHolder.getInstance().addWithId(chainGoods);
		}
		return chainGoods;
	}
}

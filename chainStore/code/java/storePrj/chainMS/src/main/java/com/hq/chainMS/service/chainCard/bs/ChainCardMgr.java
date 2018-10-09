package com.hq.chainMS.service.chainCard.bs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.hq.chainMS.service.chainCard.data.ChainCard;
import com.hq.chainMS.service.chainCard.data.ProductCard;
import com.hq.chainMS.service.common.EntityState;
import com.hq.chainMS.service.sellProduct.data.SellProduct;
import com.zenmind.common.hotSwap.HotSwap;

public class ChainCardMgr {

	public static ChainCardMgr getInstance() {
		return HotSwap.getInstance().getSingleton(ChainCardMgr.class);
	}

	public void updateChainCard(ChainCard chainCard) {
		ChainCardDataHolder.getInstance().update(chainCard);
	}
	
	public ChainCard get(long chainId) {
		ChainCard info = ChainCardDataHolder.getInstance().get(chainId);
		if(info == null){//不存在，则新增
			info = ChainCard.newInstance(chainId);
			ChainCardDataHolder.getInstance().addWithId(info);
		}
		return info;
	}
	
	public List<SellProduct> getSellProducts(long chainId){
		List<SellProduct> result = new ArrayList<SellProduct>();
		ChainCard chainData = get(chainId);
		Collection<ProductCard> array = chainData.getProductCardMap().values();
		for (ProductCard data : array) {
			if(data.getEntityState() == EntityState.Deleted.ordinal()) {
				continue;
			}
			result.add(data.toSellProduct());
		}
		return result;
	}

	public void addChainCard(ChainCard target) {
		ChainCardDataHolder.getInstance().addWithId(target);
	}
}

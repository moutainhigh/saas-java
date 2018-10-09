package com.hq.chainMS.service.chainProduct.bs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.hq.chainMS.service.chainProduct.data.ChainProduct;
import com.hq.chainMS.service.chainProduct.data.Product;
import com.hq.chainMS.service.common.EntityState;
import com.hq.chainMS.service.sellProduct.data.SellProduct;
import com.zenmind.common.hotSwap.HotSwap;

public class ChainProductMgr {

	public static ChainProductMgr getInstance() {
		return HotSwap.getInstance().getSingleton(ChainProductMgr.class);
	}

	public void addWithId(ChainProduct target) {
		ChainProductDataHolder.getInstance().addWithId(target);
	}

	public void update(ChainProduct target) {
		ChainProductDataHolder.getInstance().update(target);
	}

	public List<SellProduct> getSellProducts(long chainId){
		List<SellProduct> result = new ArrayList<SellProduct>();
		ChainProduct chainData = get(chainId);
		Collection<Product> array = chainData.getProductInfoMap().values();
		for (Product data : array) {
			if(data.getEntityState() == EntityState.Deleted.ordinal()) {
				continue;
			}
			result.add(data.toSellProduct());
		}
		return result;
	} 
	
	public ChainProduct get(long chainId) {
		ChainProduct chainProduct = ChainProductDataHolder.getInstance().get(chainId);
		if(chainProduct == null){//不存在，则新增
			chainProduct = ChainProduct.newInstance(chainId);
			ChainProductDataHolder.getInstance().addWithId(chainProduct);
		}
		return chainProduct;
	}
}

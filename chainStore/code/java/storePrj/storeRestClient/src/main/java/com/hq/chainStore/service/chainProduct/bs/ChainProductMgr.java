package com.hq.chainStore.service.chainProduct.bs;

import com.hq.chainStore.service.chainProduct.data.ChainProduct;
import com.hq.chainStore.service.chainProduct.data.ChainProductDAO;
import com.hq.chainStore.service.chainProduct.data.ProductDetail;
import com.zenmind.common.hotSwap.HotSwap;

public class ChainProductMgr {

	public static ChainProductMgr getInstance(){
		return HotSwap.getInstance().getSingleton(ChainProductMgr.class);
	}
	
	public ChainProduct get(long chainId) {
		return ChainProductDAO.getInstance().get(chainId);
	}
	
	public ProductDetail findProductDetail(String productId,long chainId) {
		return ChainProductDAO.getInstance().findProductDetail(productId, chainId);
	}
}

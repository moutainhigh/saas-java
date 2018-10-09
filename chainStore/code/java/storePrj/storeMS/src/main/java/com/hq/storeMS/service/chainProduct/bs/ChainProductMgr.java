package com.hq.storeMS.service.chainProduct.bs;

import com.hq.chainClient.service.chainProduct.data.ChainProduct;
import com.hq.chainClient.service.chainProduct.data.ProductDetail;
import com.zenmind.common.hotSwap.HotSwap;

public class ChainProductMgr {

	public static ChainProductMgr getInstance() {
		return HotSwap.getInstance().getSingleton(ChainProductMgr.class);
	}

	public ChainProduct getChainProduct(long chainId) {
		return ChainProductDataHolder.getInstance().get(chainId);
	}

	public ProductDetail getProductDetail(String ProductId, long chainId) {
		return ChainProductDataHolder.getInstance().getProductDetail(ProductId, chainId);
	}
}

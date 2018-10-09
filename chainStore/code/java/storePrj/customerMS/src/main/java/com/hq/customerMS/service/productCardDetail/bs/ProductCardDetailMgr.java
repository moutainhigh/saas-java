package com.hq.customerMS.service.productCardDetail.bs;

import com.hq.storeClient.service.productCardDetail.data.ProductCardDetail;
import com.zenmind.common.hotSwap.HotSwap;

public class ProductCardDetailMgr {

	public static ProductCardDetailMgr getInstance() {
		return HotSwap.getInstance().getSingleton(ProductCardDetailMgr.class);
	}

	public ProductCardDetail get(long storeId, String productCardDetailId) {
		return ProductCardDetailDataHolder.getInstance().get(storeId, productCardDetailId);
	}
}

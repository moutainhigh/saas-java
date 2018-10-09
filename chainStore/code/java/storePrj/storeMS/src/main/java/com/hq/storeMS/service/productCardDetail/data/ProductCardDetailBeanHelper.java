package com.hq.storeMS.service.productCardDetail.data;

import com.zenmind.common.hotSwap.HotSwap;

public class ProductCardDetailBeanHelper {
	public static ProductCardDetailBeanHelper getInstance() {
		return HotSwap.getInstance().getSingleton(ProductCardDetailBeanHelper.class);
	}
}

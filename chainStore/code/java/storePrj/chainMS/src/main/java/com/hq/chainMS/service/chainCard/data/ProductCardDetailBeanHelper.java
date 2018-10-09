package com.hq.chainMS.service.chainCard.data;

import com.zenmind.common.hotSwap.HotSwap;

public class ProductCardDetailBeanHelper {
	public static ProductCardDetailBeanHelper getInstance() {
		return HotSwap.getInstance().getSingleton(ProductCardDetailBeanHelper.class);
	}
}

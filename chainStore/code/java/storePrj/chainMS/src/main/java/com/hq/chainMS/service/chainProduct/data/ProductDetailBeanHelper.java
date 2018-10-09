package com.hq.chainMS.service.chainProduct.data;

import com.zenmind.common.hotSwap.HotSwap;

public class ProductDetailBeanHelper {
	public static ProductDetailBeanHelper getInstance() {
		return HotSwap.getInstance().getSingleton(ProductDetailBeanHelper.class);
	}
}

package com.hq.storeMS.service.goodsDetail.data;

import com.zenmind.common.hotSwap.HotSwap;

public class GoodsDetailBeanHelper {
	public static GoodsDetailBeanHelper getInstance() {
		return HotSwap.getInstance().getSingleton(GoodsDetailBeanHelper.class);
	}
}

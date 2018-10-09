package com.hq.chainMS.service.chainGoods.data;

import com.zenmind.common.hotSwap.HotSwap;

public class GoodsDetailBeanHelper {
	public static GoodsDetailBeanHelper getInstance() {
		return HotSwap.getInstance().getSingleton(GoodsDetailBeanHelper.class);
	}
}

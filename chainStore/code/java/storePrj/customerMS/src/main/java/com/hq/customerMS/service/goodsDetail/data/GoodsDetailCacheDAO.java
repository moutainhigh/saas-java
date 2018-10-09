package com.hq.customerMS.service.goodsDetail.data;

import com.hq.customerMS.common.util.AppUtils;
import com.hq.storeClient.service.goodsDetail.data.GoodsDetail;
import com.zenmind.common.hotSwap.HotSwap;

public class GoodsDetailCacheDAO {

	public static GoodsDetailCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(GoodsDetailCacheDAO.class);
	}

	final private String suffix = "storeGoodsDetail";

	public GoodsDetail get(long storeId, String id) {
		return GoodsDetailRedisDAO.getInstance().findByOne(getGroupName(storeId), id);
	}

	private String getGroupName(Object storeId) {
		return AppUtils.joinByUnderline(suffix, storeId);
	}
}

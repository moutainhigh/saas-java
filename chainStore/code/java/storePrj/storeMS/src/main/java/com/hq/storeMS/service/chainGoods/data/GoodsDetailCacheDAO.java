package com.hq.storeMS.service.chainGoods.data;

import com.hq.chainClient.service.chainGoods.data.GoodsDetail;
import com.hq.storeMS.common.util.AppUtils;
import com.zenmind.common.hotSwap.HotSwap;

public class GoodsDetailCacheDAO {

	public static GoodsDetailCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(GoodsDetailCacheDAO.class);
	}

	final private String suffix = "chainGoodsDetail";

	public GoodsDetail get(long chainId, String id) {
		return GoodsDetailRedisDAO.getInstance().findByOne(getGroupName(chainId), id);
	}

	private String getGroupName(long chainId) {
		return AppUtils.joinByUnderline(suffix, chainId);
	}
}

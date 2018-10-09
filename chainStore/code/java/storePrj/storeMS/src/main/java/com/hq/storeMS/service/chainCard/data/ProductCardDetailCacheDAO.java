package com.hq.storeMS.service.chainCard.data;

import com.hq.chainClient.service.chainCard.data.ProductCardDetail;
import com.hq.storeMS.common.util.AppUtils;
import com.zenmind.common.hotSwap.HotSwap;

public class ProductCardDetailCacheDAO {

	public static ProductCardDetailCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(ProductCardDetailCacheDAO.class);
	}
	
	final private String suffix = "chainProductCardDetail";

	public ProductCardDetail get(long chainId, String id) {
		return ProductCardDetailRedisDAO.getInstance().findByOne(getGroupName(chainId), id);
	}
	
	private String getGroupName(long chainId) {
		return AppUtils.joinByUnderline(suffix, chainId);
	}
}

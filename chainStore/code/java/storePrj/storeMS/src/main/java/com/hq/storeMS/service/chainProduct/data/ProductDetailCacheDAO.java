package com.hq.storeMS.service.chainProduct.data;

import com.hq.chainClient.service.chainProduct.data.ProductDetail;
import com.hq.storeMS.common.util.AppUtils;
import com.zenmind.common.hotSwap.HotSwap;

public class ProductDetailCacheDAO {

	public static ProductDetailCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(ProductDetailCacheDAO.class);
	}

	final private String suffix = "chainProductDetail";

	public ProductDetail get(long chainId, String id) {
		return ProductDetailRedisDAO.getInstance().findByOne(getGroupName(chainId), id);
	}

	private String getGroupName(long chainId) {
		return AppUtils.joinByUnderline(suffix, chainId);
	}
}

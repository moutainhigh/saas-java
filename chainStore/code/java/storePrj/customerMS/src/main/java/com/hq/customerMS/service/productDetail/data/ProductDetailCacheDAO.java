package com.hq.customerMS.service.productDetail.data;

import com.hq.customerMS.common.util.AppUtils;
import com.hq.storeClient.service.productDetail.data.ProductDetail;
import com.zenmind.common.hotSwap.HotSwap;

public class ProductDetailCacheDAO {

	public static ProductDetailCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(ProductDetailCacheDAO.class);
	}

	final private String suffix = "storeProductDetail";

	public ProductDetail get(long storeId, String id) {
		return ProductDetailRedisDAO.getInstance().findByOne(getGroupName(storeId), id);
	}

	private String getGroupName(Object storeId) {
		return AppUtils.joinByUnderline(suffix, storeId);
	}
}

package com.hq.customerMS.service.productCardDetail.data;

import com.hq.customerMS.common.util.AppUtils;
import com.hq.storeClient.service.productCardDetail.data.ProductCardDetail;
import com.zenmind.common.hotSwap.HotSwap;

public class ProductCardDetailCacheDAO {

	public static ProductCardDetailCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(ProductCardDetailCacheDAO.class);
	}

	final private String suffix = "storeProductCardDetail";

	public ProductCardDetail get(long storeId, String id) {
		return ProductCardDetailRedisDAO.getInstance().findByOne(getGroupName(storeId), id);
	}

	private String getGroupName(Object storeId) {
		return AppUtils.joinByUnderline(suffix, storeId);
	}
}

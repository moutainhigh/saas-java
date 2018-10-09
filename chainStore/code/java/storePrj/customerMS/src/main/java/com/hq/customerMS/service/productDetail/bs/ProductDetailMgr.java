package com.hq.customerMS.service.productDetail.bs;

import com.hq.storeClient.common.restClientResp.PageResp;
import com.hq.storeClient.service.productDetail.apiData.ProductDetailQueryForm;
import com.hq.storeClient.service.productDetail.data.ProductDetail;
import com.zenmind.common.hotSwap.HotSwap;

public class ProductDetailMgr {
	public static ProductDetailMgr getInstance() {
		return HotSwap.getInstance().getSingleton(ProductDetailMgr.class);
	}

	public PageResp<ProductDetail> getProductDetailPageInfo(ProductDetailQueryForm queryForm) {
		return ProductDetailDataHolder.getInstance().getProductDetailPageInfo(queryForm);
	}
	
	public ProductDetail getProductDetail(long storeId, String productId) {
		return ProductDetailDataHolder.getInstance().getProductDetail(storeId, productId);
	}
}

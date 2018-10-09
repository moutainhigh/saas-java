package com.hq.storeClient.service.productDetail.bs;

import com.hq.storeClient.common.restClientResp.PageResp;
import com.hq.storeClient.common.utils.StringUtils4Client;
import com.hq.storeClient.service.productDetail.apiData.ProductDetailQueryForm;
import com.hq.storeClient.service.productDetail.data.ProductDetail;
import com.hq.storeClient.service.productDetail.data.ProductDetailDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class ProductDetailClientMgr {

	public static ProductDetailClientMgr getInstance() {
		return HotSwap.getInstance().getSingleton(ProductDetailClientMgr.class);
	}
	
	public PageResp<ProductDetail> getProductDetailPageInfo(ProductDetailQueryForm queryForm) {
		final String findPath = "getProductDetailPageInfo";
		return ProductDetailDAO.getInstance().getProductDetailPageInfo(findPath, queryForm);
	}
	
	public ProductDetail getProductDetail(long storeId, String productId) {
		String uriPath = StringUtils4Client.format("{}/{}", storeId, productId);
		return ProductDetailDAO.getInstance().findOne(uriPath);
	}
}

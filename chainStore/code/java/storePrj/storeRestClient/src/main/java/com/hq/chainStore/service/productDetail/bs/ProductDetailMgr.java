package com.hq.chainStore.service.productDetail.bs;

import com.hq.chainStore.service.common.PageResp;
import com.hq.chainStore.service.productDetail.apiData.ProductDetailQueryForm;
import com.hq.chainStore.service.productDetail.data.ProductDetail;
import com.hq.chainStore.service.productDetail.data.ProductDetailDAO;
import com.hq.common.StringUtils4Client;
import com.zenmind.common.hotSwap.HotSwap;

public class ProductDetailMgr {

	public static ProductDetailMgr getInstance() {
		return HotSwap.getInstance().getSingleton(ProductDetailMgr.class);
	}
	
	public PageResp<ProductDetail> getProductDetailPageInfo(ProductDetailQueryForm queryForm) {
		final String findPath = "getProductDetailPageInfo";
		return ProductDetailDAO.getInstance().getProductDetailPageInfo(findPath, queryForm);
	}
	
	@Deprecated
	public ProductDetail getProductDetail(String productDetailId) {
		return ProductDetailDAO.getInstance().get(productDetailId);
	}
	
	public ProductDetail getProductDetail(long storeId, String productId) {
		String uriPath = StringUtils4Client.format("{}/{}", storeId, productId);
		return ProductDetailDAO.getInstance().findOne(uriPath);
	}
}

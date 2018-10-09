package com.hq.chainClient.service.chainProduct.bs;

import com.hq.chainClient.common.restClientResp.PageResp;
import com.hq.chainClient.service.chainProduct.apiData.ProductDetailQueryForm;
import com.hq.chainClient.service.chainProduct.data.ProductDetail;
import com.hq.chainClient.service.chainProduct.data.ProductDetailDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class ProductDetailClientMgr {

	public static ProductDetailClientMgr getInstance() {
		return HotSwap.getInstance().getSingleton(ProductDetailClientMgr.class);
	}
	
	public PageResp<ProductDetail> getProductDetailPageInfo(ProductDetailQueryForm queryForm) {
		final String findPath = "getProductDetailPageInfo";
		return ProductDetailDAO.getInstance().getProductDetailPageInfo(findPath, queryForm);
	}
	
	public ProductDetail getProductDetail(long chainId, String detailId) {
		return ProductDetailDAO.getInstance().get(chainId + "/" + detailId);
	}
}

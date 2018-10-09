package com.hq.chainStore.service.productCardDetail.bs;

import com.hq.chainStore.service.common.PageResp;
import com.hq.chainStore.service.productCardDetail.apiData.ProductCardDetailQueryForm;
import com.hq.chainStore.service.productCardDetail.data.ProductCardDetail;
import com.hq.chainStore.service.productCardDetail.data.ProductCardDetailDAO;
import com.hq.common.StringUtils4Client;
import com.zenmind.common.hotSwap.HotSwap;

public class ProductCardDetailMgr {

	public static ProductCardDetailMgr getInstance() {
		return HotSwap.getInstance().getSingleton(ProductCardDetailMgr.class);
	}
	
	public PageResp<ProductCardDetail> getProductCardDetailPageInfo(ProductCardDetailQueryForm queryForm) {
		final String findPath = "getProductCardDetailPageInfo";
		return ProductCardDetailDAO.getInstance().getProductCardDetailPageInfo(findPath, queryForm);
	}
	
	@Deprecated
	public ProductCardDetail getProductCardDetail(String productCardDetailId) {
		return ProductCardDetailDAO.getInstance().get(productCardDetailId);
	}
	
	public ProductCardDetail getProductCardDetail(long storeId, String productCardDetailId) {
		String uriPath = StringUtils4Client.format("{}/{}", storeId, productCardDetailId);
		return ProductCardDetailDAO.getInstance().findOne(uriPath);
	}
}

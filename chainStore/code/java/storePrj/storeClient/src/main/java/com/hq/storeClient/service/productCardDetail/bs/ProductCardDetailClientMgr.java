package com.hq.storeClient.service.productCardDetail.bs;

import com.hq.storeClient.common.restClientResp.PageResp;
import com.hq.storeClient.common.utils.StringUtils4Client;
import com.hq.storeClient.service.productCardDetail.apiData.ProductCardDetailQueryForm;
import com.hq.storeClient.service.productCardDetail.data.ProductCardDetail;
import com.hq.storeClient.service.productCardDetail.data.ProductCardDetailDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class ProductCardDetailClientMgr {

	public static ProductCardDetailClientMgr getInstance() {
		return HotSwap.getInstance().getSingleton(ProductCardDetailClientMgr.class);
	}
	
	public ProductCardDetail getProductCardDetail(long storeId, String productCardDetailId) {
		String uriPath = StringUtils4Client.format("{}/{}", storeId, productCardDetailId);
		return ProductCardDetailDAO.getInstance().findOne(uriPath);
	}
	
	public PageResp<ProductCardDetail> getProductCardDetailPageInfo(ProductCardDetailQueryForm queryForm) {
		final String findPath = "getProductCardDetailPageInfo";
		return ProductCardDetailDAO.getInstance().getProductCardDetailPageInfo(findPath, queryForm);
	}
	
}

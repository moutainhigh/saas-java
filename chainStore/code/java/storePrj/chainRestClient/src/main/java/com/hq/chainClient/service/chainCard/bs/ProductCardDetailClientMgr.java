package com.hq.chainClient.service.chainCard.bs;

import com.hq.chainClient.common.restClientResp.PageResp;
import com.hq.chainClient.service.chainCard.apiData.ProductCardDetailQueryForm;
import com.hq.chainClient.service.chainCard.data.ProductCardDetail;
import com.hq.chainClient.service.chainCard.data.ProductCardDetailDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class ProductCardDetailClientMgr {

	public static ProductCardDetailClientMgr getInstance() {
		return HotSwap.getInstance().getSingleton(ProductCardDetailClientMgr.class);
	}

	public PageResp<ProductCardDetail> getProductCardDetailPageInfo(ProductCardDetailQueryForm queryForm) {
		final String findPath = "getProductCardDetailPageInfo";
		return ProductCardDetailDAO.getInstance().getProductCardDetailPageInfo(findPath, queryForm);
	}

	public ProductCardDetail getProductCardDetail(long chainId, String detailId) {
		return ProductCardDetailDAO.getInstance().get(chainId+"/"+detailId);
	}

}

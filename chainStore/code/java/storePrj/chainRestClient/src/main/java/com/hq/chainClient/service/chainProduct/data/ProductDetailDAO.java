package com.hq.chainClient.service.chainProduct.data;

import com.hq.chainClient.common.restClientResp.PageResp;
import com.hq.chainClient.common.restClientResp.RestClientCfg;
import com.hq.chainClient.common.utils.JsonUtil4Client;
import com.hq.chainClient.service.chainProduct.apiData.ProductDetailQueryForm;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;
import com.zenmind.dao.rest.RestResp;

public class ProductDetailDAO extends RestDao<ProductDetail> {

	public static ProductDetailDAO getInstance(){
		return HotSwap.getInstance().getSingleton(ProductDetailDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getService();
	}

	public PageResp<ProductDetail> getProductDetailPageInfo(String findPath, ProductDetailQueryForm queryForm) {
		RestResp restResp = super.rawGetReq(findPath, queryForm.toReqMap(), queryForm.getPageItemCount(), queryForm.getPageNo());
		return JsonUtil4Client.getInstance().parseTPage(restResp.gettJson(), ProductDetail.class);
	}
	
}

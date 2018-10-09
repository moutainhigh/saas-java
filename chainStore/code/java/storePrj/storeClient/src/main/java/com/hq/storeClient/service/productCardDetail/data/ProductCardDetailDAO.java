package com.hq.storeClient.service.productCardDetail.data;

import com.hq.storeClient.common.restClientResp.PageResp;
import com.hq.storeClient.common.restClientResp.RestClientCfg;
import com.hq.storeClient.common.utils.JsonUtil4Client;
import com.hq.storeClient.service.productCardDetail.apiData.ProductCardDetailQueryForm;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;
import com.zenmind.dao.rest.RestResp;

public class ProductCardDetailDAO extends RestDao<ProductCardDetail> {

	public static ProductCardDetailDAO getInstance() {
		return HotSwap.getInstance().getSingleton(ProductCardDetailDAO.class);
	}
	
	@Override
	public String getService() {
		return RestClientCfg.getService();
	}
	
	public PageResp<ProductCardDetail> getProductCardDetailPageInfo(String findPath, ProductCardDetailQueryForm queryForm) {
		RestResp restResp = super.rawGetReq(findPath, queryForm.toReqMap(), queryForm.getPageItemCount(), queryForm.getPageNo());
		return JsonUtil4Client.getInstance().parseTPage(restResp.gettJson(), ProductCardDetail.class);
	}

}

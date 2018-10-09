package com.hq.storeClient.service.productDetail.data;

import com.hq.storeClient.common.restClientResp.PageResp;
import com.hq.storeClient.common.restClientResp.RestClientCfg;
import com.hq.storeClient.common.utils.JsonUtil4Client;
import com.hq.storeClient.service.productDetail.apiData.ProductDetailQueryForm;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;
import com.zenmind.dao.rest.RestDaoException;
import com.zenmind.dao.rest.RestProxyException;
import com.zenmind.dao.rest.RestResp;

public class ProductDetailDAO  extends RestDao<ProductDetail> {

	public static ProductDetailDAO getInstance() {
		return HotSwap.getInstance().getSingleton(ProductDetailDAO.class);
	}
	
	@Override
	public String getService() {
		return RestClientCfg.getService();
	}

	public PageResp<ProductDetail> getProductDetailPageInfo(String findPath, ProductDetailQueryForm queryForm) {
		try {
			RestResp restResp = super.rawGetReq(findPath, queryForm.toReqMap(), queryForm.getPageItemCount(), queryForm.getPageNo());
			return JsonUtil4Client.getInstance().parseTPage(restResp.gettJson(), ProductDetail.class);
		} catch (Exception e) {
			if(e instanceof RestProxyException){
				throw((RestProxyException)e);
			}
			throw(RestDaoException.newInstance("ProductDetailDAO.getProductDetailPageInfo()", "productDetail", "", e));
		}
	}
}

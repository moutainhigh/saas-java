package com.hq.chainStore.service.productCardDetail.data;

import com.hq.chainStore.service.common.PageResp;
import com.hq.chainStore.service.common.RestClientCfg;
import com.hq.chainStore.service.productCardDetail.apiData.ProductCardDetailQueryForm;
import com.hq.common.JsonUtil4Client;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;
import com.zenmind.dao.rest.RestDaoException;
import com.zenmind.dao.rest.RestProxyException;
import com.zenmind.dao.rest.RestResp;

public class ProductCardDetailDAO extends RestDao<ProductCardDetail> {

	public static ProductCardDetailDAO getInstance(){
		return HotSwap.getInstance().getSingleton(ProductCardDetailDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getStoreService();
	}

	public PageResp<ProductCardDetail> getProductCardDetailPageInfo(String findPath, ProductCardDetailQueryForm queryForm) {
		try {
			RestResp restResp = super.rawGetReq(findPath, queryForm.toReqMap(), queryForm.getPageItemCount(), queryForm.getPageNo());
			return JsonUtil4Client.getInstance().parseTPage(restResp.gettJson(), ProductCardDetail.class);
		} catch (Exception e) {
			if(e instanceof RestProxyException){
				throw((RestProxyException)e);
			}
			throw(RestDaoException.newInstance("ProductCardDetailDAO.getProductCardDetailPageInfo()", "productCardDetail", "", e));
		}
	}
	
}

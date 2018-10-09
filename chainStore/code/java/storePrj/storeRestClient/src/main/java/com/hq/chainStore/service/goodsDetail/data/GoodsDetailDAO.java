package com.hq.chainStore.service.goodsDetail.data;

import com.hq.chainStore.service.common.PageResp;
import com.hq.chainStore.service.common.RestClientCfg;
import com.hq.chainStore.service.goodsDetail.apiData.GoodsDetailQueryForm;
import com.hq.common.JsonUtil4Client;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;
import com.zenmind.dao.rest.RestDaoException;
import com.zenmind.dao.rest.RestProxyException;
import com.zenmind.dao.rest.RestResp;

public class GoodsDetailDAO extends RestDao<GoodsDetail> {

	public static GoodsDetailDAO getInstance(){
		return HotSwap.getInstance().getSingleton(GoodsDetailDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getStoreService();
	}

	public PageResp<GoodsDetail> getGoodsDetailPageInfo(String findPath, GoodsDetailQueryForm queryForm) {
		try {
			RestResp restResp = super.rawGetReq(findPath, queryForm.toReqMap(), queryForm.getPageItemCount(), queryForm.getPageNo());
			return JsonUtil4Client.getInstance().parseTPage(restResp.gettJson(), GoodsDetail.class);
		} catch (Exception e) {
			if(e instanceof RestProxyException){
				throw((RestProxyException)e);
			}
			throw(RestDaoException.newInstance("GoodsDetailDAO.getGoodsDetailPageInfo()", "goodsDetail", "", e));
		}
	}
	
}

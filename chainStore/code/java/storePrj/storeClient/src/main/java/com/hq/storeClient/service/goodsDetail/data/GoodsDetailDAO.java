package com.hq.storeClient.service.goodsDetail.data;

import com.hq.storeClient.common.restClientResp.PageResp;
import com.hq.storeClient.common.restClientResp.RestClientCfg;
import com.hq.storeClient.common.utils.JsonUtil4Client;
import com.hq.storeClient.service.goodsDetail.apiData.GoodsDetailQueryForm;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;
import com.zenmind.dao.rest.RestResp;

public class GoodsDetailDAO  extends RestDao<GoodsDetail> {

	public static GoodsDetailDAO getInstance() {
		return HotSwap.getInstance().getSingleton(GoodsDetailDAO.class);
	}
	
	@Override
	public String getService() {
		return RestClientCfg.getService();
	}

	public PageResp<GoodsDetail> getGoodsDetailPageInfo(String findPath, GoodsDetailQueryForm queryForm) {
		RestResp restResp = super.rawGetReq(findPath, queryForm.toReqMap(), queryForm.getPageItemCount(), queryForm.getPageNo());
		return JsonUtil4Client.getInstance().parseTPage(restResp.gettJson(), GoodsDetail.class);
	}
}

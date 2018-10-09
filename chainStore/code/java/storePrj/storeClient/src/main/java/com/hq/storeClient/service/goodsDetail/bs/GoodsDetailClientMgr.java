package com.hq.storeClient.service.goodsDetail.bs;

import com.hq.storeClient.common.restClientResp.PageResp;
import com.hq.storeClient.common.utils.StringUtils4Client;
import com.hq.storeClient.service.goodsDetail.apiData.GoodsDetailQueryForm;
import com.hq.storeClient.service.goodsDetail.data.GoodsDetail;
import com.hq.storeClient.service.goodsDetail.data.GoodsDetailDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class GoodsDetailClientMgr {

	public static GoodsDetailClientMgr getInstance() {
		return HotSwap.getInstance().getSingleton(GoodsDetailClientMgr.class);
	}
	
	public PageResp<GoodsDetail> getGoodsDetailPageInfo(GoodsDetailQueryForm queryForm) {
		final String findPath = "getGoodsDetailPageInfo";
		return GoodsDetailDAO.getInstance().getGoodsDetailPageInfo(findPath, queryForm);
	}
	
	public GoodsDetail getGoodsDetail(long storeId, String goodsId) {
		String uriPath = StringUtils4Client.format("{}/{}", storeId, goodsId);
		return GoodsDetailDAO.getInstance().findOne(uriPath);
	}
}

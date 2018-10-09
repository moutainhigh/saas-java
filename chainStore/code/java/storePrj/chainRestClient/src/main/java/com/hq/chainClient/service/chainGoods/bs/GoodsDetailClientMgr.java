package com.hq.chainClient.service.chainGoods.bs;

import com.hq.chainClient.common.restClientResp.PageResp;
import com.hq.chainClient.service.chainGoods.apiData.GoodsDetailQueryForm;
import com.hq.chainClient.service.chainGoods.data.GoodsDetail;
import com.hq.chainClient.service.chainGoods.data.GoodsDetailDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class GoodsDetailClientMgr {

	public static GoodsDetailClientMgr getInstance() {
		return HotSwap.getInstance().getSingleton(GoodsDetailClientMgr.class);
	}
	
	public PageResp<GoodsDetail> getGoodsDetailPageInfo(GoodsDetailQueryForm queryForm) {
		final String findPath = "getGoodsDetailPageInfo";
		return GoodsDetailDAO.getInstance().getGoodsDetailPageInfo(findPath, queryForm);
	}
	
	public GoodsDetail getGoodsDetail(long chainId, String detailId) {
		return GoodsDetailDAO.getInstance().get(chainId+"/"+detailId);
	}
}

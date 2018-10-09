package com.hq.chainStore.service.goodsDetail.bs;

import com.hq.chainStore.service.common.PageResp;
import com.hq.chainStore.service.goodsDetail.apiData.GoodsDetailQueryForm;
import com.hq.chainStore.service.goodsDetail.data.GoodsDetail;
import com.hq.chainStore.service.goodsDetail.data.GoodsDetailDAO;
import com.hq.common.StringUtils4Client;
import com.zenmind.common.hotSwap.HotSwap;

public class GoodsDetailMgr {

	public static GoodsDetailMgr getInstance() {
		return HotSwap.getInstance().getSingleton(GoodsDetailMgr.class);
	}
	
	public PageResp<GoodsDetail> getGoodsDetailPageInfo(GoodsDetailQueryForm queryForm) {
		final String findPath = "getGoodsDetailPageInfo";
		return GoodsDetailDAO.getInstance().getGoodsDetailPageInfo(findPath, queryForm);
	}
	
	@Deprecated
	public GoodsDetail getGoodsDetail(String goodsDetailId) {
		return GoodsDetailDAO.getInstance().get(goodsDetailId);
	}
	
	public GoodsDetail getGoodsDetail(long storeId, String goodsId) {
		String uriPath = StringUtils4Client.format("{}/{}", storeId, goodsId);
		return GoodsDetailDAO.getInstance().findOne(uriPath);
	}
}

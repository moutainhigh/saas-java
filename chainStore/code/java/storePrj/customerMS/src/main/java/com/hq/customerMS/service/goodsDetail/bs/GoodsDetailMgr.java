package com.hq.customerMS.service.goodsDetail.bs;

import com.hq.storeClient.common.restClientResp.PageResp;
import com.hq.storeClient.service.goodsDetail.apiData.GoodsDetailQueryForm;
import com.hq.storeClient.service.goodsDetail.data.GoodsDetail;
import com.zenmind.common.hotSwap.HotSwap;

public class GoodsDetailMgr {

	public static GoodsDetailMgr getInstance() {
		return HotSwap.getInstance().getSingleton(GoodsDetailMgr.class);
	}

	public PageResp<GoodsDetail> getGoodsDetailPageInfo(GoodsDetailQueryForm queryForm) {
		return GoodsDetailDataHolder.getInstance().getGoodsDetailPageInfo(queryForm);
	}

	public GoodsDetail get(long storeId, String id) {
		return GoodsDetailDataHolder.getInstance().get(storeId, id);
	}

}

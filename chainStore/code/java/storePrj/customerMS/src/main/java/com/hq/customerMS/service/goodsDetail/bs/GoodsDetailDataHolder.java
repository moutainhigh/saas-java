package com.hq.customerMS.service.goodsDetail.bs;

import com.hq.customerMS.common.constants.ServerConstants;
import com.hq.customerMS.common.validate.AppIdThreadLocal;
import com.hq.customerMS.service.goodsDetail.data.GoodsDetailCacheDAO;
import com.hq.storeClient.common.restClientResp.PageResp;
import com.hq.storeClient.service.goodsDetail.apiData.GoodsDetailQueryForm;
import com.hq.storeClient.service.goodsDetail.bs.GoodsDetailClientMgr;
import com.hq.storeClient.service.goodsDetail.data.GoodsDetail;
import com.zenmind.common.hotSwap.HotSwap;

public class GoodsDetailDataHolder {
	
	public static GoodsDetailDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(GoodsDetailDataHolder.class);
	}
	
	public PageResp<GoodsDetail> getGoodsDetailPageInfo(GoodsDetailQueryForm queryForm) {
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		PageResp<GoodsDetail> data = GoodsDetailClientMgr.getInstance().getGoodsDetailPageInfo(queryForm);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}
	
	public GoodsDetail get(long storeId, String id) {
		GoodsDetail data = GoodsDetailCacheDAO.getInstance().get(storeId, id);
		if(data!=null) {
			return data;
		}
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		data = GoodsDetailClientMgr.getInstance().getGoodsDetail(storeId, id);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}

}

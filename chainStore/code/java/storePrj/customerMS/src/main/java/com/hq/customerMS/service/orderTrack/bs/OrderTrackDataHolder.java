package com.hq.customerMS.service.orderTrack.bs;

import com.hq.customerMS.common.constants.ServerConstants;
import com.hq.customerMS.common.validate.AppIdThreadLocal;
import com.hq.customerMS.service.orderTrack.data.OrderTrackCacheDAO;
import com.hq.storeClient.service.orderTrack.apiData.OrderTrackUpdateApiForm;
import com.hq.storeClient.service.orderTrack.bs.OrderTrackClientMgr;
import com.hq.storeClient.service.orderTrack.data.OrderTrack;
import com.zenmind.common.hotSwap.HotSwap;

public class OrderTrackDataHolder {

	public static OrderTrackDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(OrderTrackDataHolder.class);
	}

//	public PageResp<OrderTrack> findOrderTrackPageInfo(OrderTrackQueryForm params) {
//		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
//		PageResp<OrderTrack> page = OrderTrackClientMgr.getInstance().findOrderTrackPageInfo(params);
//		AppIdThreadLocal.getInstance().set(null);
//		return page;
//	}

	public OrderTrack get(long storeId, long orderId) {
		OrderTrack data = OrderTrackCacheDAO.getInstance().get(storeId, orderId);
		if (data != null) {
			return data;
		}
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		data = OrderTrackClientMgr.getInstance().getOrderTrack(storeId, orderId);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}

	public OrderTrack update(long storeId, long orderId, OrderTrackUpdateApiForm updateApiForm) {
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		OrderTrack data = OrderTrackClientMgr.getInstance().update(storeId, orderId, updateApiForm);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}

}

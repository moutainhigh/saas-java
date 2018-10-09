package com.hq.customerMS.service.orderDetail.bs;

import com.hq.customerMS.common.constants.ServerConstants;
import com.hq.customerMS.common.validate.AppIdThreadLocal;
import com.hq.storeClient.service.orderDetail.bs.OrderDetailClientMgr;
import com.hq.storeClient.service.orderDetail.data.OrderDetail;
import com.zenmind.common.hotSwap.HotSwap;

public class OrderDetailDataHolder {

	public static OrderDetailDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(OrderDetailDataHolder.class);
	}

	public OrderDetail get(long storeId, long orderId) {
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		OrderDetail data = OrderDetailClientMgr.getInstance().getOrderDetail(storeId, orderId);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}

}

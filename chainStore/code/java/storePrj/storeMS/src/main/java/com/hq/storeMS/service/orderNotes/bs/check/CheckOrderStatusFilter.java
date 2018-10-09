package com.hq.storeMS.service.orderNotes.bs.check;

import com.hq.orderRestClient.service.order.data.Order;
import com.hq.orderRestClient.service.order.data.OrderStatusEnum;
import com.hq.orderRestClient.service.order.data.OrderTypeEnum;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.orderNotes.apiData.ChargeBackRecordAddForm;
import com.hq.storeMS.service.orderNotes.data.OrderNotes;
import com.zenmind.common.hotSwap.HotSwap;

public class CheckOrderStatusFilter {
	
	public static CheckOrderStatusFilter getInstance() {
		return HotSwap.getInstance().getSingleton(CheckOrderStatusFilter.class);
	}
	
	public OperateTips check(Order order, OrderNotes orderNotes, ChargeBackRecordAddForm inputForm){
		OperateTips operateTips = OperateTips.newInstance(true);
		if(order.getOrderType() == OrderTypeEnum.PURCHASE.ordinal()){//购买消费
			if(order.getStatus() == OrderStatusEnum.CHARGEBACK_ALL.ordinal()) {
				operateTips.setTips("订单已退单,不能再次退单");
				operateTips.setSuccess(false);
			}
		}
		return operateTips;
	}
}

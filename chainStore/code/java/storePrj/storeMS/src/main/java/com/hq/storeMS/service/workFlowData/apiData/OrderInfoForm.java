package com.hq.storeMS.service.workFlowData.apiData;

import com.hq.storeMS.service.workFlowData.data.OrderInfo;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class OrderInfoForm {
	// 店铺会员卡类型ID
	private long orderId;

	public static OrderInfoForm newInstance() {
		return new OrderInfoForm();
	}
	
	public OrderInfo toOrderInfo() {
		OrderInfo data = OrderInfo.newInstance();
		FastBeanCopyer.getInstance().copy(this, data);
		return data;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

}

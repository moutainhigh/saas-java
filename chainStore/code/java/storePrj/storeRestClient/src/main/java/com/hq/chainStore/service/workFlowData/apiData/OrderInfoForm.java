package com.hq.chainStore.service.workFlowData.apiData;

public class OrderInfoForm {
	// 店铺会员卡类型ID
	private long orderId;

	public static OrderInfoForm newInstance() {
		return new OrderInfoForm();
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

}

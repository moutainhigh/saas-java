package com.hq.customerRestClient.service.order.apiData;

public class OrderDeleteForm {
	private long orderId;

	public static OrderDeleteForm newInstance() {
		return new OrderDeleteForm();
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
}

package com.hq.orderRestClient.service.order.apiData;

public class OrderUpdateStatusApiForm {
	private int status;// 取消

	public static OrderUpdateStatusApiForm newInstance() {
		return new OrderUpdateStatusApiForm();
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}

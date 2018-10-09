package com.hq.orderRestClient.service.order.apiData;



public class OrderUpdateInfoApiForm {
	// 应结金额
	private float cost;

	public static OrderUpdateInfoApiForm newInstance() {
		return new OrderUpdateInfoApiForm();
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

}

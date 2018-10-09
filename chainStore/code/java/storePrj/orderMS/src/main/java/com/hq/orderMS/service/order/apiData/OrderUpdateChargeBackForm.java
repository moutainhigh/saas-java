package com.hq.orderMS.service.order.apiData;

public class OrderUpdateChargeBackForm {
	private float chargeBackCost;//退款金额

	public static OrderUpdateChargeBackForm newInstance() {
		return new OrderUpdateChargeBackForm();
	}

	public float getChargeBackCost() {
		return chargeBackCost;
	}

	public void setChargeBackCost(float chargeBackCost) {
		this.chargeBackCost = chargeBackCost;
	}
}

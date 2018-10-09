package com.hq.chainStore.service.cardOrder.apiData;


public class PayCardOrderForm {
	private int status;// 已支付
	private int payType;// 支付方式
	private float cost;//实付金额

	public static PayCardOrderForm newInstance() {
		return new PayCardOrderForm();
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getPayType() {
		return payType;
	}

	public void setPayType(int payType) {
		this.payType = payType;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

}

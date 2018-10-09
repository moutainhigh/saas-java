package com.hq.chainStore.service.order.apiData;


public class OrderUpdateStatusApiForm {
	private int status;// 取消
	
	/** =========================遗留的字段========================= **/
	private int payType;// 支付方式
	private float cost;//实付金额

	public static OrderUpdateStatusApiForm newInstance() {
		return new OrderUpdateStatusApiForm();
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

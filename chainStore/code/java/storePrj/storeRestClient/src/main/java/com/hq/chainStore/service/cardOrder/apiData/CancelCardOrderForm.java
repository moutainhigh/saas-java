package com.hq.chainStore.service.cardOrder.apiData;

public class CancelCardOrderForm {
	private int status;// 取消订单

	public static CancelCardOrderForm newInstance() {
		return new CancelCardOrderForm();
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}

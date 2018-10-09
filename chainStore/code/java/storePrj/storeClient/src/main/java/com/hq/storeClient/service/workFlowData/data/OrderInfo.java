package com.hq.storeClient.service.workFlowData.data;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class OrderInfo {
	private long orderId;

	public static OrderInfo newInstance() {
		return new OrderInfo();
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

}

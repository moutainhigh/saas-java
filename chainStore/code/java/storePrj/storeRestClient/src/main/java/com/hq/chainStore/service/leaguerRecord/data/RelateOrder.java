package com.hq.chainStore.service.leaguerRecord.data;

public class RelateOrder {
	// 订单ID
	private long orderId;
	// 订单内容
	private String orderContent;

	public static RelateOrder newInstance() {
		RelateOrder data = new RelateOrder();
		return data;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public String getOrderContent() {
		return orderContent;
	}

	public void setOrderContent(String orderContent) {
		this.orderContent = orderContent;
	}

}

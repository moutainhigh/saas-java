package com.hq.orderMS.service.order.data;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class OrderCount {
	// 店铺ID
	private long storeId;
	// 订单总数
	private long count;
	// 店铺订单总额
	private float orderCost;

	public static OrderCount newInstance() {
		OrderCount data = new OrderCount();
		return data;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public float getOrderCost() {
		return orderCost;
	}

	public void setOrderCost(float orderCost) {
		this.orderCost = orderCost;
	}

}

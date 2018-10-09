package com.hq.chainStore.service.orderDetail.data;

import java.util.ArrayList;
import java.util.List;

import com.hq.chainStore.service.order.data.PayItem;

public class OrderPayInfo {
	// 支付明细
	private List<PayItem> payItems = new ArrayList<PayItem>();

	public static OrderPayInfo newInstance() {
		OrderPayInfo data = new OrderPayInfo();
		return data;
	}

	public List<PayItem> getPayItems() {
		return payItems;
	}

	public void setPayItems(List<PayItem> payItems) {
		this.payItems = payItems;
	}

}

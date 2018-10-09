package com.hq.orderRestClient.service.order.apiData;

import java.util.ArrayList;
import java.util.List;

import com.hq.orderRestClient.service.order.data.PayItem;

public class OrderUpdatePayItemApiForm {
	private List<PayItem> payItems = new ArrayList<PayItem>();

	public static OrderUpdatePayItemApiForm newInstance() {
		return new OrderUpdatePayItemApiForm();
	}

	public List<PayItem> getPayItems() {
		return payItems;
	}

	public void setPayItems(List<PayItem> payItems) {
		this.payItems = payItems;
	}
	
	public float getRealPay() {
		float total = 0f;
		for (PayItem payItem : payItems) {
			total += payItem.getCost();
		}
		return total;
	}
}

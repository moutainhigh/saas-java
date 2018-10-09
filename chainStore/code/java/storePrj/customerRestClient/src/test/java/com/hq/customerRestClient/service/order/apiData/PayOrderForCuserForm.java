package com.hq.customerRestClient.service.order.apiData;

import java.util.ArrayList;
import java.util.List;

import com.hq.customerRestClient.service.order.data.PayItem;

public class PayOrderForCuserForm {
	private List<PayItem> payItems = new ArrayList<PayItem>();
	// 店铺ID
	private long storeId;
	private long orderId;
	// 操作者ID
	private long creatorId;
	
	public static PayOrderForCuserForm newInstance() {
		return new PayOrderForCuserForm();
	}

	public List<PayItem> getPayItems() {
		return payItems;
	}

	public void setPayItems(List<PayItem> payItems) {
		this.payItems = payItems;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(long creatorId) {
		this.creatorId = creatorId;
	}
}

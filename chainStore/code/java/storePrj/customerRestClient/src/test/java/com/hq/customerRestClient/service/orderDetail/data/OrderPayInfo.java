package com.hq.customerRestClient.service.orderDetail.data;

import java.util.ArrayList;
import java.util.List;

import com.hq.customerRestClient.service.order.data.PayItem;
import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
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

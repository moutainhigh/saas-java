package com.hq.chainStore.service.arrearage.apiData;

import java.util.ArrayList;
import java.util.List;

import com.hq.chainStore.service.order.data.PayItem;

public class ArrearageAddPaymentHistoryApiForm {
	// 还款明细
	private List<PayItem> payItems = new ArrayList<PayItem>();

	public static ArrearageAddPaymentHistoryApiForm newInstance() {
		return new ArrearageAddPaymentHistoryApiForm();
	}

	public List<PayItem> getPayItems() {
		return payItems;
	}

	public void setPayItems(List<PayItem> payItems) {
		this.payItems = payItems;
	}
	
	public void addPayItmes(PayItem item){
		this.payItems.add(item);
	}

}

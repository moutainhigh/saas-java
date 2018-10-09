package com.hq.storeClient.service.order.apiData;

import java.util.ArrayList;
import java.util.List;

import com.hq.storeClient.service.order.data.PayItem;

public class PayOrderWithNoteApiForm {
	
	private List<PayItem> payItems = new ArrayList<PayItem>();
	private String remark;//订单备注信息

	public static PayOrderWithNoteApiForm newInstance() {
		return new PayOrderWithNoteApiForm();
	}

	public List<PayItem> getPayItems() {
		return payItems;
	}

	public void setPayItems(List<PayItem> payItems) {
		this.payItems = payItems;
	}
	
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public float getRealPay() {
		float total = 0f;
		for (PayItem payItem : payItems) {
			total += payItem.getCost();
		}
		return total;
	}
}

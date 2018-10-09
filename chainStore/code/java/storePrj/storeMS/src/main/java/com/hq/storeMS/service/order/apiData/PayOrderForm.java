package com.hq.storeMS.service.order.apiData;

import java.util.ArrayList;
import java.util.List;

import com.hq.orderRestClient.service.order.data.PayItem;
import com.hq.storeMS.service.buser.data.BUser;

public class PayOrderForm {
	private BUser creator;
	// 支付信息
	private List<PayItem> payItems = new ArrayList<PayItem>();
	// 订单备注信息
	private String remark;

	public static PayOrderForm newInstance() {
		return new PayOrderForm();
	}
	
	public static PayOrderForm newInstance(List<PayItem> payItemsP, String remarkP, BUser creatorP) {
		PayOrderForm data = new PayOrderForm();
		data.payItems=payItemsP;
		data.remark=remarkP;
		data.creator=creatorP;
		return data;
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

	public BUser getCreator() {
		return creator;
	}

	public void setCreator(BUser creator) {
		this.creator = creator;
	}

}

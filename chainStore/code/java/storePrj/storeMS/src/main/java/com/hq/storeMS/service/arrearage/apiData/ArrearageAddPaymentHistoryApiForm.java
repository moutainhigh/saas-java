package com.hq.storeMS.service.arrearage.apiData;

import java.util.ArrayList;
import java.util.List;

import com.hq.orderRestClient.service.order.data.PayItem;
import com.hq.storeMS.service.arrearage.data.PaymentTypeEnum;

public class ArrearageAddPaymentHistoryApiForm {
	//从登陆者的session信息里获取
	// 创建者ID
	private long creatorId;
	// 创建者名称
	private String creatorName;
	
	//还款类型 PaymentTypeEnum
	private int paymentType;
	// 还款明细
	private List<PayItem> payItems = new ArrayList<PayItem>();

	public static ArrearageAddPaymentHistoryApiForm newInstance() {
		ArrearageAddPaymentHistoryApiForm data = new ArrearageAddPaymentHistoryApiForm();
		data.paymentType = PaymentTypeEnum.Payment.ordinal();
		return data;
	}
	
	public void addPayItem(PayItem item) {
		payItems.add(item);
	}

	public List<PayItem> getPayItems() {
		return payItems;
	}

	public void setPayItems(List<PayItem> payItems) {
		this.payItems = payItems;
	}

	public long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(long creatorId) {
		this.creatorId = creatorId;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public int getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(int paymentType) {
		this.paymentType = paymentType;
	}

}

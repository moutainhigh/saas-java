package com.hq.storeMS.service.order.apiData;

import java.util.ArrayList;
import java.util.List;

import com.hq.orderRestClient.service.order.apiData.OrderAddApiForm;
import com.hq.orderRestClient.service.order.data.PayItem;
import com.hq.storeMS.service.workFlowData.apiData.BonusInfoAddForm;

public class OrderItemAddForm {
	// 订单基本信息
	private OrderAddApiForm orderAddApiForm;
	// 提成基本信息
	private List<BonusInfoAddForm> bonusInfoAddForms = new ArrayList<BonusInfoAddForm>();
	// 支付信息
	private List<PayItem> payItems = new ArrayList<PayItem>();
	
	//订单备注信息
	private String remark;

	public static OrderItemAddForm newInstance() {
		return new OrderItemAddForm();
	}

	public List<BonusInfoAddForm> getBonusInfoAddForms() {
		return bonusInfoAddForms;
	}

	public void setBonusInfoAddForms(List<BonusInfoAddForm> bonusInfoAddForms) {
		this.bonusInfoAddForms = bonusInfoAddForms;
	}

	public List<PayItem> getPayItems() {
		return payItems;
	}

	public void setPayItems(List<PayItem> payItems) {
		this.payItems = payItems;
	}

	public OrderAddApiForm getOrderAddApiForm() {
		return orderAddApiForm;
	}

	public void setOrderAddApiForm(OrderAddApiForm orderAddApiForm) {
		this.orderAddApiForm = orderAddApiForm;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}

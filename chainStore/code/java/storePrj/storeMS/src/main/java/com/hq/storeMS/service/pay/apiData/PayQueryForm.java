package com.hq.storeMS.service.pay.apiData;

public class PayQueryForm {
	
	//api类型 ApiTypeEnum
	private int apiType = -1;
	// (必填) 商户网站订单系统中唯一订单号，要求32个字符内，只能包含字母、数字、下划线，
	// 需保证商户系统端不能重复
	private String outTradeNo;
	
	private long orderId;

	public static PayQueryForm newInstance(){
		return new PayQueryForm();
	}

	public int getApiType() {
		return apiType;
	}

	public PayQueryForm setApiType(int apiType) {
		this.apiType = apiType;
		return this;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public PayQueryForm setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
		return this;
	}

	public long getOrderId() {
		return orderId;
	}

	public PayQueryForm setOrderId(long orderId) {
		this.orderId = orderId;
		return this;
	}

}

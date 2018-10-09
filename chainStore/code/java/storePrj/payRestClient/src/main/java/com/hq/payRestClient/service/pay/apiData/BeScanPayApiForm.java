package com.hq.payRestClient.service.pay.apiData;


public class BeScanPayApiForm{
	
	//api类型 ApiTypeEnum
	private int apiType = -1;
	
	//用于查询商户账号、密钥等信息; 
	private long storeId;
	
	//订单号
	private long orderId;
	
	//订单号来源类型，用来区分如何回调；OrderOriginTypeEnum
	private int orderOriginType;
	
	//商户交易号，要求32个字符内，只能包含字母、数字、下划线，需保证商户系统端不能重复
	private String outTradeNo;

	//订单总金额，单位为元，不能超过1亿元
	private String totalAmount;
	
	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	public int getApiType() {
		return apiType;
	}

	public void setApiType(int apiType) {
		this.apiType = apiType;
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

	public int getOrderOriginType() {
		return orderOriginType;
	}

	public void setOrderOriginType(int orderOriginType) {
		this.orderOriginType = orderOriginType;
	}
	
	
}

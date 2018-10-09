package com.hq.payRestClient.service.pay.apiData;

public class MiniProgramPayApiForm {
	//小程序的appid
	private String appid;
	
	//微信用户在小程序appid下的唯一标识
	private String openid;
	
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
	
	public static MiniProgramPayApiForm newInstance() {
		return new MiniProgramPayApiForm();
	}
	
	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
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

	public int getOrderOriginType() {
		return orderOriginType;
	}

	public void setOrderOriginType(int orderOriginType) {
		this.orderOriginType = orderOriginType;
	}
		
}

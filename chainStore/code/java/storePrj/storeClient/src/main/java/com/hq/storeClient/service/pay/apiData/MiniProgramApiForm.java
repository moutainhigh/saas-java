package com.hq.storeClient.service.pay.apiData;

public class MiniProgramApiForm {

	//小程序的appid
	private String appid;
	
	//微信用户在小程序appid下的唯一标识
	private String openid;
	
	//用于查询商户账号、密钥等信息; 
	private long storeId;
	
	//storeMS订单号
	private long orderId;

	// (必填) 订单总金额，单位为元，不能超过1亿元
	private String totalAmount;
	
	/******************* 遗留字段 *********************/
	
	//微信用户在小程序下的jsCode（有效期为5分钟），用于获取openid 
	private String jsCode;
	
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

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getJsCode() {
		return jsCode;
	}

	public void setJsCode(String jsCode) {
		this.jsCode = jsCode;
	}
	
}


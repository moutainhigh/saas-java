package com.hq.storeClient.service.pay.apiData;

public class ScanApiForm {
	
	private int apiType;//ApiTypeEnum  WXPAY("微信支付API"), ALIPAY("支付宝API"),
	private String totalAmount;
	private String authCode; // 付款条码示例，286648048691290423
	private long storeId;
	private long orderId;//订单id

	public static ScanApiForm newInstance(){
		return new ScanApiForm();
	}

	public int getApiType() {
		return apiType;
	}

	public ScanApiForm setApiType(int apiType) {
		this.apiType = apiType;
		return this;
	}

	public long getStoreId() {
		return storeId;
	}

	public ScanApiForm setStoreId(long storeId) {
		this.storeId = storeId;
		return this;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public ScanApiForm setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
		return this;
	}

	public String getAuthCode() {
		return authCode;
	}

	public ScanApiForm setAuthCode(String authCode) {
		this.authCode = authCode;
		return this;
	}

	public long getOrderId() {
		return orderId;
	}

	public ScanApiForm setOrderId(long orderId) {
		this.orderId = orderId;
		return this;
	}

}

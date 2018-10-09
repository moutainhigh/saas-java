package com.hq.chainStore.service.pay.apiData;

public class BeScanApiForm {
	
	private int apiType;//ApiTypeEnum  WXPAY("微信支付API"), ALIPAY("支付宝API"),
	private String totalAmount;
	private long storeId;
	private long orderId;//订单id

	public static BeScanApiForm newInstance(){
		return new BeScanApiForm();
	}

	public int getApiType() {
		return apiType;
	}

	public BeScanApiForm setApiType(int apiType) {
		this.apiType = apiType;
		return this;
	}

	public long getStoreId() {
		return storeId;
	}

	public BeScanApiForm setStoreId(long storeId) {
		this.storeId = storeId;
		return this;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public BeScanApiForm setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
		return this;
	}

	public long getOrderId() {
		return orderId;
	}

	public BeScanApiForm setOrderId(long orderId) {
		this.orderId = orderId;
		return this;
	}

}

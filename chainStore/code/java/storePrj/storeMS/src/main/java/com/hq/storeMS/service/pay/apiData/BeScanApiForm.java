package com.hq.storeMS.service.pay.apiData;

import com.hq.payRestClient.service.pay.apiData.BeScanPayApiForm;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class BeScanApiForm {
	private int apiType;//ApiTypeEnum  WXPAY("微信支付API"), ALIPAY("支付宝API"),
	private String totalAmount;
	private long storeId;
	private long orderId;//订单id
	//订单号来源类型，用来区分如何回调；OrderOriginTypeEnum
	private int orderOriginType;

	public static BeScanApiForm newInstance(){
		return new BeScanApiForm();
	}
	
	public BeScanPayApiForm toBeScanPayApiForm() {
		BeScanPayApiForm data = new BeScanPayApiForm();
		FastBeanCopyer.getInstance().copy(this, data);
		return data;
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

	public int getOrderOriginType() {
		return orderOriginType;
	}

	public void setOrderOriginType(int orderOriginType) {
		this.orderOriginType = orderOriginType;
	}

}

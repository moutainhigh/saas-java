package com.hq.storeMS.service.pay.apiData;

public class PayCallbackForm {
	private long storeId;
	private String tradeNo;// 支付宝或微信的内部交易流水号
	// (必填) 商户网站订单系统中唯一订单号，要求32个字符内，只能包含字母、数字、下划线，
	// 需保证商户系统端不能重复
	private String outTradeNo;

	private long orderId;
	// 订单号来源类型，用来区分如何回调；OrderOriginTypeEnum
	private int orderOriginType;

	private int payType;// PayTypeEnum

	private float amount;

	public static PayCallbackForm newInstance() {
		return new PayCallbackForm();
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public PayCallbackForm setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
		return this;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public PayCallbackForm setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
		return this;
	}

	public long getOrderId() {
		return orderId;
	}

	public PayCallbackForm setOrderId(long orderId) {
		this.orderId = orderId;
		return this;
	}

	public int getPayType() {
		return payType;
	}

	public PayCallbackForm setPayType(int payType) {
		this.payType = payType;
		return this;
	}

	public float getAmount() {
		return amount;
	}

	public PayCallbackForm setAmount(float amount) {
		this.amount = amount;
		return this;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public int getOrderOriginType() {
		return orderOriginType;
	}

	public void setOrderOriginType(int orderOriginType) {
		this.orderOriginType = orderOriginType;
	}

}

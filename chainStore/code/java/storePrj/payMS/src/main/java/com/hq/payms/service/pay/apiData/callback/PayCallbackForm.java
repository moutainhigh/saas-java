package com.hq.payms.service.pay.apiData.callback;

import com.hq.payms.service.alipayRecord.data.AlipayRecord;
import com.hq.payms.service.wxpayRecord.data.WxpayRecord;

public class PayCallbackForm {
	//支付宝或微信的内部交易流水号
	private String tradeNo;
	//商户交易号，要求32个字符内，只能包含字母、数字、下划线，需保证商户系统端不能重复
	private String outTradeNo;
	
	private long storeId;
	
	private long orderId;
	
	//订单号来源类型，用来区分如何回调；OrderOriginTypeEnum
	private int orderOriginType;
	
	//PayTypeEnum
	private int payType;
	
	private float amount;

	public static PayCallbackForm newInstance(){
		return new PayCallbackForm();
	}
	
	public static PayCallbackForm fromWxpayRecord(WxpayRecord payRecord) {
		PayCallbackForm instance = PayCallbackForm.newInstance();
		instance.setStoreId(payRecord.getStoreId());
		instance.setOrderId(payRecord.getOrderId());
		instance.setOrderOriginType(payRecord.getOrderOriginType());
		instance.setOutTradeNo(payRecord.getOut_trade_no());
		instance.setTradeNo(payRecord.getTransaction_id());
		instance.setPayType(PayTypeEnum.WECHAT.ordinal());
		instance.setAmount(Float.parseFloat(payRecord.getTotal_fee()) / 100); //分转成元
		return instance;
	}
	
	public static PayCallbackForm fromAlipayRecord(AlipayRecord payRecord) {
		PayCallbackForm instance = PayCallbackForm.newInstance();
		instance.setStoreId(payRecord.getStoreId());
		instance.setOrderId(payRecord.getOrderId());
		instance.setOrderOriginType(payRecord.getOrderOriginType());
		instance.setOutTradeNo(payRecord.getOutTradeNo());
		instance.setTradeNo(payRecord.getTradeNo());
		instance.setPayType(PayTypeEnum.ALIPAY.ordinal());
		instance.setAmount(Float.parseFloat(payRecord.getTotalAmount()));
		return instance;
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

	@Override
	public String toString() {
		return "PayCallbackForm [tradeNo=" + tradeNo + ", outTradeNo=" + outTradeNo + ", storeId=" + storeId
				+ ", orderId=" + orderId + ", orderOriginType=" + orderOriginType + ", payType=" + payType + ", amount="
				+ amount + "]";
	}

}

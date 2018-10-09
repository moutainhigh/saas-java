package com.hq.storeManagerMS.service.charge.data;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class ChargePayItem {
	// 支付方式 ChargeChannelEnum
	private int payType;
	// 金额
	private long cost;
	// 随机生成的订单号
	private String outTradeNo;
	// 交易成功的流水号
	private String tradeNo;
	// 付款时间
	private long createdTime;

	public static ChargePayItem newInstance() {
		ChargePayItem data = new ChargePayItem();
		return data;
	}

	public int getPayType() {
		return payType;
	}

	public void setPayType(int payType) {
		this.payType = payType;
	}

	public long getCost() {
		return cost;
	}

	public void setCost(long cost) {
		this.cost = cost;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public long getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}

}

package com.hq.orderRestClient.service.order.data;

import com.zenmind.dataSyn.annotation.SynClass;

/**
 * 支付明细
 */
@SynClass
public class PayItem {
	// 支付方式 PayTypeEnum
	private int payType;
	// 金额
	private float cost;
	
	/***************************选择扫码线上支付*******************************/
	//随机生成的订单号
	private String outTradeNo; 
	//交易成功的流水号
	private String tradeNo;
	
	private long createdTime;

	public static PayItem newInstance() {
		PayItem data = new PayItem();
		
		long curTime = System.currentTimeMillis();
		data.createdTime = curTime;
		return data;
	}

	public int getPayType() {
		return payType;
	}

	public void setPayType(int payType) {
		this.payType = payType;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
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

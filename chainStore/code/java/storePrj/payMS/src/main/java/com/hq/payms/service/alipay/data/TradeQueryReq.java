package com.hq.payms.service.alipay.data;

public class TradeQueryReq extends CommonReq{
	
	private String outTradeNo;
	
	public static TradeQueryReq newInstance() {
		TradeQueryReq instance = new TradeQueryReq();
		return instance;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}
	
	

}

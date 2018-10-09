package com.hq.payRestClient.service.pay.apiData;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class PayQueryResp {
		
	private String tradeNo;//支付宝或微信的内部交易号
	
	public static PayQueryResp newInstance(String tradeNo) {
		PayQueryResp instance = new PayQueryResp();
		instance.tradeNo = tradeNo;
		return instance;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

}

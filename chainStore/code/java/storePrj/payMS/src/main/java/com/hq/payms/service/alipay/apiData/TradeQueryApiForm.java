package com.hq.payms.service.alipay.apiData;

import com.hq.payms.service.alipay.data.TradeQueryReq;
import com.hq.payms.service.common.BaseApiForm;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class TradeQueryApiForm extends BaseApiForm{
	// (必填) 外部订单号，需要退款交易的商户外部订单号
	private String outTradeNo;
	
	public static TradeQueryApiForm newInstance() {
		return new TradeQueryApiForm();
	}
	
	public TradeQueryReq toTradeQueryReq() {
		TradeQueryReq target = TradeQueryReq.newInstance();
		FastBeanCopyer.getInstance().copy(this, target);
		return target;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}
	
	
}

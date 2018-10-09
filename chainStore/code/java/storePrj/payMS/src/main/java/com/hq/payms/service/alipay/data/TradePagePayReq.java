package com.hq.payms.service.alipay.data;

import com.alipay.demo.trade.model.ExtendParams;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hq.payms.common.config.PayMSCfgMgr;

public class TradePagePayReq extends CommonReq implements IntfNotifiableReq {
	
	//支付结果异步通知地址；由于同步返回的不可靠性，支付结果必须以异步通知或查询接口返回为准，不能依赖同步跳转。
	@JsonIgnore
	private String notifyUrl;
	
	//同步返回参数地址（无支付结果，不重要）
	@JsonIgnore
	private String returnUrl;
	
	private ExtendParams extendParams;
	
	//(必填)商户订单号
	private String outTradeNo;
	
	//(必填)订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]
	private String totalAmount;
	
	//(必填)订单标题
	private String subject;
	
	//销售产品码，与支付宝签约的产品码名称。 注：目前仅支持FAST_INSTANT_TRADE_PAY
	private String productCode;
	
	
	//该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。
	private String timeoutExpress;
	
	//PC扫码支付的方式，支持前置模式和跳转模式。 2：订单码-跳转模式
	private String qrPayMode;

	public static TradePagePayReq newInstance() {
		TradePagePayReq instance = new TradePagePayReq();
		instance.productCode = "FAST_INSTANT_TRADE_PAY";
		instance.timeoutExpress = PayMSCfgMgr.getProp().getZmAlipayCfg().getAlipayTimeoutExpress() / 60 + "m";
		instance.qrPayMode = "2";
		return instance;
	}
	
	public String getReturnUrl() {
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public ExtendParams getExtendParams() {
		return extendParams;
	}

	public void setExtendParams(ExtendParams extendParams) {
		this.extendParams = extendParams;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTimeoutExpress() {
		return timeoutExpress;
	}

	public void setTimeoutExpress(String timeoutExpress) {
		this.timeoutExpress = timeoutExpress;
	}

	public String getQrPayMode() {
		return qrPayMode;
	}

	public void setQrPayMode(String qrPayMode) {
		this.qrPayMode = qrPayMode;
	}
	
}

package com.hq.payms.zenmind.zmWxpay.common;

public class ZmWXPayConstants {
	private ZmWXPayConstants() {
		
	}
	public static final String SUBJECT = "智美通-消费-微信支付"; 
	public static final String SUBJECT_MINI_PROGROM = "小程序-消费-微信支付"; 
	
	public static final String FEE_TYPE = "CNY"; //货币类型
	
	public static final String TRADE_TYPE_JSAPI = "JSAPI"; //公众号支付；或小程序支付
	public static final String TRADE_TYPE_NATIVE = "NATIVE"; //原生扫码支付
	public static final String TRADE_TYPE_APP = "APP"; //app支付
	public static final String TRADE_TYPE_MICROPAY = "MICROPAY"; //刷卡支付，刷卡支付有单独的支付接口，不调用统一下单接口
	
	public static final String PRODUCT_ID = "product_id"; //商品id，目前写固定值
	
	/*
	  * 交易状态： 
	  * NOTPAY—未支付 USERPAYING--用户支付中
	  * SUCCESS—支付成功
	  * REFUND—转入退款  CLOSED—已关闭  REVOKED—已撤销(刷卡支付)  PAYERROR--支付失败(其他原因，如银行返回失败)
	  */
	public static final String TS_NOTPAY = "NOTPAY";
	public static final String TS_USERPAYING = "USERPAYING";
	
	public static final String TS_SUCCESS = "SUCCESS";
	
	public static final String TS_REFUND = "REFUND";
	public static final String TS_CLOSED = "CLOSED";
	public static final String TS_REVOKED = "REVOKED";
	public static final String TS_PAYERROR = "PAYERROR";
}

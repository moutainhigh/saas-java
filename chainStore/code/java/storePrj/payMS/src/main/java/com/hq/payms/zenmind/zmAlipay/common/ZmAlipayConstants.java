package com.hq.payms.zenmind.zmAlipay.common;

public class ZmAlipayConstants {
	private ZmAlipayConstants(){
		
	}
	
	public static final String SUBJECT = "智美通-消费-支付宝支付";
	
	/*
	 * 交易状态：WAIT_BUYER_PAY（交易创建，等待买家付款）、TRADE_CLOSED（未付款交易超时关闭，或支付完成后全额退款）、TRADE_SUCCESS（交易支付成功）、TRADE_FINISHED（交易结束，不可退款）
	 */
	public static final String TS_WAIT_BUYER_PAY = "WAIT_BUYER_PAY"; 
	public static final String TS_TRADE_CLOSED = "TRADE_CLOSED"; 
	public static final String TS_TRADE_SUCCESS = "TRADE_SUCCESS";
	public static final String TS_TRADE_FINISHED = "TRADE_FINISHED"; 
}

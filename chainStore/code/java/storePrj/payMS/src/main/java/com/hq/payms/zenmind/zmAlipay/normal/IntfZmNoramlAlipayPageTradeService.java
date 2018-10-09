package com.hq.payms.zenmind.zmAlipay.normal;

import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.demo.trade.zm.ZmAlipayAppParam;

public interface IntfZmNoramlAlipayPageTradeService {
	
	 public String tradePagePay(AlipayTradePagePayRequest alipayRequest);
	 
//	 default String tradePagePay(String appId, String privateKey, AlipayTradePagePayRequest alipayRequest) {
//		 throw new RuntimeException("接口中的默认方法");
//	 }
	 
	 default String tradePagePay(ZmAlipayAppParam appParam, AlipayTradePagePayRequest alipayRequest) {
		 throw new RuntimeException("接口中的默认方法");
	 }
	 
}

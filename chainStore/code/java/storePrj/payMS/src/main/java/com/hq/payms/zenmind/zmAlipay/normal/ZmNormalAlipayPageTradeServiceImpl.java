package com.hq.payms.zenmind.zmAlipay.normal;

import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.demo.trade.zm.ZmAlipayAppParam;

/**
 *  电脑网站支付服务<br>
 *  支持多商户<br>
 *  一定要在创建AlipayPageTradeService之前调用Configs.init("xxxxxx");设置参数<br>
 *  
 *  关于安全性：<br>
 *  暂时加synchronized确保支付方法是线程安全的;<br>
 *  主要是保证每个线程调用支付方法时，client不会被其它线程切换，导致支付错<br>
 */
public class ZmNormalAlipayPageTradeServiceImpl extends ZmNormalAlipayPageTradeServiceSimpleImpl{
	
	private void setClient(ZmAlipayAppParam appParam) {
		//设置client
    	super.client = ZmNormalAlipayClientSingleMgr.getInstance().getClient(appParam);
	}

	@Override
	public synchronized String tradePagePay(ZmAlipayAppParam appParam, AlipayTradePagePayRequest alipayRequest) {
		setClient(appParam);
        return super.tradePagePay(alipayRequest);
    }

}
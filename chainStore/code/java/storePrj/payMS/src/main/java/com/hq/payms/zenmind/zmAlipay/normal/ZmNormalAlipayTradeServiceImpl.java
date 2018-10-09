package com.hq.payms.zenmind.zmAlipay.normal;

import com.alipay.demo.trade.model.builder.AlipayTradePayRequestBuilder;
import com.alipay.demo.trade.model.builder.AlipayTradePrecreateRequestBuilder;
import com.alipay.demo.trade.model.builder.AlipayTradeQueryRequestBuilder;
import com.alipay.demo.trade.model.builder.AlipayTradeRefundRequestBuilder;
import com.alipay.demo.trade.model.result.AlipayF2FPayResult;
import com.alipay.demo.trade.model.result.AlipayF2FPrecreateResult;
import com.alipay.demo.trade.model.result.AlipayF2FQueryResult;
import com.alipay.demo.trade.model.result.AlipayF2FRefundResult;
import com.alipay.demo.trade.service.impl.AlipayTradeServiceImpl;
import com.alipay.demo.trade.zm.ZmAlipayAppParam;

/**
 * 支持向多个商户付款<br>
 * 需配置多个商户appid和私钥和支付宝公钥(可放到数据库)，然后动态更换去调用支付接口，即可实现多账号收款<br>
 *
 * 关于安全性：<br>
 * 暂时加synchronized确保支付方法是线程安全的;<br>
 * 主要是保证每个线程调用支付方法时，client不会被其它线程切换，导致支付错<br>
 * 
 * @see https://openclub.alipay.com/read.php?tid=2123&fid=56<br>
 * @author: wujunwei 
 * @version: v1.0  
 * @since: JDK 1.8
 */
public class ZmNormalAlipayTradeServiceImpl extends AlipayTradeServiceImpl {

	private void setClient(ZmAlipayAppParam appParam) {
		//设置client
    	super.client = ZmNormalAlipayClientSingleMgr.getInstance().getClient(appParam);
	}

	@Override
    public synchronized AlipayF2FPayResult tradePay(ZmAlipayAppParam appParam, AlipayTradePayRequestBuilder builder) {
    	setClient(appParam);
    	return super.tradePay(builder);
    }

	@Override
    public synchronized AlipayF2FQueryResult queryTradeResult(ZmAlipayAppParam appParam, AlipayTradeQueryRequestBuilder builder) {
    	setClient(appParam);
        return super.queryTradeResult(builder);
    }
	
	@Override
    public synchronized AlipayF2FRefundResult tradeRefund(ZmAlipayAppParam appParam, AlipayTradeRefundRequestBuilder builder) {
    	setClient(appParam);
        return super.tradeRefund(builder);
    }
    
	@Override
    public synchronized AlipayF2FPrecreateResult tradePrecreate(ZmAlipayAppParam appParam, AlipayTradePrecreateRequestBuilder builder) {
    	setClient(appParam);
        return super.tradePrecreate(builder);
    }
    
}

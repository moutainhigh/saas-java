package com.hq.payms.zenmind.zmAlipay.safeNormal;

import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.demo.trade.model.builder.AlipayTradePayRequestBuilder;
import com.alipay.demo.trade.model.builder.AlipayTradePrecreateRequestBuilder;
import com.alipay.demo.trade.model.builder.AlipayTradeQueryRequestBuilder;
import com.alipay.demo.trade.model.builder.AlipayTradeRefundRequestBuilder;
import com.alipay.demo.trade.model.result.AlipayF2FPayResult;
import com.alipay.demo.trade.model.result.AlipayF2FPrecreateResult;
import com.alipay.demo.trade.model.result.AlipayF2FQueryResult;
import com.alipay.demo.trade.model.result.AlipayF2FRefundResult;
import com.alipay.demo.trade.zm.ZmAlipayAppParam;
import com.hq.payms.zenmind.zmAlipay.common.ZmAlipayClientPoolMgr;

public class ZmSafeNormalAlipayTradeServiceImplAdapter extends ZmSafeNormalAlipayTradeServiceImpl{
	
	public ZmSafeNormalAlipayTradeServiceImplAdapter() {
	}

    public AlipayF2FPayResult tradePay(ZmAlipayAppParam appParam, AlipayTradePayRequestBuilder builder) {
    	return super.tradePay(
    			ZmAlipayClientPoolMgr.getInstance().getClient(appParam),
    			builder);
    }

    public AlipayF2FQueryResult queryTradeResult(ZmAlipayAppParam appParam, AlipayTradeQueryRequestBuilder builder) {
        return super.queryTradeResult(
        		ZmAlipayClientPoolMgr.getInstance().getClient(appParam),
        		builder);
    }
	
    public AlipayF2FRefundResult tradeRefund(ZmAlipayAppParam appParam, AlipayTradeRefundRequestBuilder builder) {
        return super.tradeRefund(
        		ZmAlipayClientPoolMgr.getInstance().getClient(appParam),
        		builder);
    }
    
    public AlipayF2FPrecreateResult tradePrecreate(ZmAlipayAppParam appParam, AlipayTradePrecreateRequestBuilder builder) {
        return super.tradePrecreate(
        		ZmAlipayClientPoolMgr.getInstance().getClient(appParam),
        		builder);
    }
    
	public String tradePagePay(ZmAlipayAppParam appParam, AlipayTradePagePayRequest alipayRequest) {
        return super.tradePagePay(
        		ZmAlipayClientPoolMgr.getInstance().getClient(appParam),
        		alipayRequest);
    }

}

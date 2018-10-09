package com.hq.payms.service.alipay.bs;

import com.alipay.demo.trade.model.result.AlipayF2FPayResult;
import com.alipay.demo.trade.model.result.AlipayF2FPrecreateResult;
import com.alipay.demo.trade.model.result.AlipayF2FQueryResult;
import com.alipay.demo.trade.model.result.AlipayF2FRefundResult;
import com.hq.payms.service.alipay.data.TradePagePayReq;
import com.hq.payms.service.alipay.data.TradePayReq;
import com.hq.payms.service.alipay.data.TradePrecreateReq;
import com.hq.payms.service.alipay.data.TradeQueryReq;
import com.hq.payms.service.alipay.data.TradeRefundReq;
import com.zenmind.common.hotSwap.HotSwap;

public class AlipayMgr {

	public static AlipayMgr getInstance() {
		return HotSwap.getInstance().getSingleton(AlipayMgr.class);
	}

	public AlipayF2FPrecreateResult doTradePrecreate(TradePrecreateReq param) {
		return AlipayDataHolder.getInstance().doTradePrecreate(param);
	}

	public AlipayF2FPayResult doTradePay(TradePayReq param) {
		return AlipayDataHolder.getInstance().doTradePay(param);
	}

	public AlipayF2FQueryResult doTradeQuery(TradeQueryReq param) {
		return AlipayDataHolder.getInstance().doTradeQuery(param);
	}
	
	public AlipayF2FRefundResult doTradeRefund(TradeRefundReq param) {
    	return AlipayDataHolder.getInstance().doTradeRefund(param);
    }
	
	public String doTradePagePay(TradePagePayReq param) {
    	return AlipayDataHolder.getInstance().doTradePagePay(param);
    }
}

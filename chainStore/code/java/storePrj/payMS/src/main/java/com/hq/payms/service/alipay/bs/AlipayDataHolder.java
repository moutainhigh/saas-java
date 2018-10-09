package com.hq.payms.service.alipay.bs;

import com.alipay.demo.trade.model.result.AlipayF2FPayResult;
import com.alipay.demo.trade.model.result.AlipayF2FPrecreateResult;
import com.alipay.demo.trade.model.result.AlipayF2FQueryResult;
import com.alipay.demo.trade.model.result.AlipayF2FRefundResult;
import com.hq.payms.service.alipay.data.AlipayRestDAO;
import com.hq.payms.service.alipay.data.TradePagePayReq;
import com.hq.payms.service.alipay.data.TradePayReq;
import com.hq.payms.service.alipay.data.TradePrecreateReq;
import com.hq.payms.service.alipay.data.TradeQueryReq;
import com.hq.payms.service.alipay.data.TradeRefundReq;
import com.zenmind.common.hotSwap.HotSwap;

public class AlipayDataHolder {
	
	public static AlipayDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(AlipayDataHolder.class);
	}
	
	private AlipayRestDAO DAO = AlipayRestDAO.getInstance();
	
    public AlipayF2FPrecreateResult doTradePrecreate(TradePrecreateReq param) {
    	return DAO.doTradePrecreate(param);
    	
    }
    
    public AlipayF2FPayResult doTradePay(TradePayReq param) {
    	return DAO.doTradePay(param);
    }
    
    public AlipayF2FQueryResult doTradeQuery(TradeQueryReq param) {
    	return DAO.doTradeQuery(param);
    }
    
    public AlipayF2FRefundResult doTradeRefund(TradeRefundReq param) {
    	return DAO.doTradeRefund(param);
    }
	
    public String doTradePagePay(TradePagePayReq param) {
		return DAO.doTradePagePay(param);
	}
}

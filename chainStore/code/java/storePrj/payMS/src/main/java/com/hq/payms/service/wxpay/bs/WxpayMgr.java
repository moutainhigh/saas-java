package com.hq.payms.service.wxpay.bs;


import java.util.Map;

import com.hq.payms.service.wxpay.data.CommonResp;
import com.hq.payms.service.wxpay.data.DownloadBillReq;
import com.hq.payms.service.wxpay.data.MicroPayReq;
import com.hq.payms.service.wxpay.data.MicroPayResp;
import com.hq.payms.service.wxpay.data.OrderQueryOrCloseReq;
import com.hq.payms.service.wxpay.data.OrderQueryResp;
import com.hq.payms.service.wxpay.data.RefundReq;
import com.hq.payms.service.wxpay.data.ShortUrlReq;
import com.hq.payms.service.wxpay.data.ShortUrlResp;
import com.hq.payms.service.wxpay.data.UnifiedOrderReq;
import com.hq.payms.service.wxpay.data.UnifiedOrderResp;
import com.zenmind.common.hotSwap.HotSwap;

public class WxpayMgr {

	public static WxpayMgr getInstance(){
		return HotSwap.getInstance().getSingleton(WxpayMgr.class);
	}
	
	public UnifiedOrderResp doUnifiedOrder(UnifiedOrderReq param) {
		return WxpayDataHolder.getInstance().doUnifiedOrder(param);
	}

	public ShortUrlResp doShortUrl(ShortUrlReq param) {
		return WxpayDataHolder.getInstance().doShortUrl(param);
	}

	public OrderQueryResp doOrderQuery(OrderQueryOrCloseReq param) {
		return WxpayDataHolder.getInstance().doOrderQuery(param);
	}
	
	public CommonResp doOrderClose(OrderQueryOrCloseReq param) {
		return WxpayDataHolder.getInstance().doOrderClose(param);
	}
	

	public MicroPayResp doMicroPay(MicroPayReq param) {
		return WxpayDataHolder.getInstance().doMicroPay(param);
	}
	
	public CommonResp doOrderReverse(OrderQueryOrCloseReq param) {
		return WxpayDataHolder.getInstance().doOrderReverse(param);
	}
	
	public CommonResp doRefund(RefundReq param) {
		return WxpayDataHolder.getInstance().doRefund(param);
	}
	
	public Map<String, String> doDownloadBill(DownloadBillReq param){
		return WxpayDataHolder.getInstance().doDownloadBill(param);
	}
	
}

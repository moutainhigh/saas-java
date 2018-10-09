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
import com.hq.payms.service.wxpay.data.WxpayRestDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class WxpayDataHolder {
	
	public static WxpayDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(WxpayDataHolder.class);
	}
	
	
	public UnifiedOrderResp doUnifiedOrder(UnifiedOrderReq param){
		return WxpayRestDAO.getInstance().doUnifiedOrder(param);
	}
	
	public ShortUrlResp doShortUrl(ShortUrlReq param){
		return WxpayRestDAO.getInstance().doShortUrl(param);
	}

	public OrderQueryResp doOrderQuery(OrderQueryOrCloseReq param) {
		return WxpayRestDAO.getInstance().doOrderQuery(param);
	}
	
	public CommonResp doOrderClose(OrderQueryOrCloseReq param) {
		return WxpayRestDAO.getInstance().doOrderClose(param);
	}
	
	public MicroPayResp doMicroPay(MicroPayReq param) {
		return WxpayRestDAO.getInstance().doMicroPay(param);
	}
	
	public CommonResp doOrderReverse(OrderQueryOrCloseReq param) {
		return WxpayRestDAO.getInstance().doOrderReverse(param);
	}
	
	public CommonResp doRefund(RefundReq param) {
		return WxpayRestDAO.getInstance().doRefund(param);
	}
	
	public Map<String, String> doDownloadBill(DownloadBillReq param) {
		return WxpayRestDAO.getInstance().doDownloadBill(param);
	}
}

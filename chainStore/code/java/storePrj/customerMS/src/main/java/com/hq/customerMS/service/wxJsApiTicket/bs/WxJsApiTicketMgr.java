package com.hq.customerMS.service.wxJsApiTicket.bs;

import com.hq.storeClient.service.wxJsApiTicket.apiData.GenJssdkSignatureApiForm;
import com.hq.storeClient.service.wxJsApiTicket.data.WxJsApiTicket;
import com.zenmind.common.hotSwap.HotSwap;

public class WxJsApiTicketMgr {
	
	public static WxJsApiTicketMgr getInstance(){
		return HotSwap.getInstance().getSingleton(WxJsApiTicketMgr.class);
	}
	
	public WxJsApiTicket genJssdkSignature(GenJssdkSignatureApiForm form) {
		return WxJsApiTicketDataHolder.getInstance().genJssdkSignature(form);
	}

}

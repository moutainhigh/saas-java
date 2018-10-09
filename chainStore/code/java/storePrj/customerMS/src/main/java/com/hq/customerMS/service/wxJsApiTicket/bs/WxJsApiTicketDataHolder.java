package com.hq.customerMS.service.wxJsApiTicket.bs;

import com.hq.customerMS.common.constants.ServerConstants;
import com.hq.customerMS.common.validate.AppIdThreadLocal;
import com.hq.storeClient.service.wxJsApiTicket.apiData.GenJssdkSignatureApiForm;
import com.hq.storeClient.service.wxJsApiTicket.bs.WxJsApiTicketClientMgr;
import com.hq.storeClient.service.wxJsApiTicket.data.WxJsApiTicket;
import com.zenmind.common.hotSwap.HotSwap;

public class WxJsApiTicketDataHolder{
	
	public static WxJsApiTicketDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(WxJsApiTicketDataHolder.class);
	}
	
	public WxJsApiTicket genJssdkSignature(GenJssdkSignatureApiForm form) {
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		WxJsApiTicket data = WxJsApiTicketClientMgr.getInstance().genJssdkSignature(form);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}
}
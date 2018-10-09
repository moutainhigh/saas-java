package com.hq.storeClient.service.wxJsApiTicket.bs;

import com.hq.storeClient.common.restClientResp.RestRespHelper;
import com.hq.storeClient.service.wxJsApiTicket.apiData.GenJssdkSignatureApiForm;
import com.hq.storeClient.service.wxJsApiTicket.data.WxJsApiTicket;
import com.hq.storeClient.service.wxJsApiTicket.data.WxJsApiTicketDAO;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestResp;

public class WxJsApiTicketClientMgr {

	public static WxJsApiTicketClientMgr getInstance() {
		return HotSwap.getInstance().getSingleton(WxJsApiTicketClientMgr.class);
	}
	
	public WxJsApiTicket genJssdkSignature(GenJssdkSignatureApiForm form){
		String actionName = "genJssdkSignature";
		RestResp restResp = WxJsApiTicketDAO.getInstance().rawReq(actionName, form);
		return RestRespHelper.gettJsonObj(restResp, WxJsApiTicket.class);
	}
}

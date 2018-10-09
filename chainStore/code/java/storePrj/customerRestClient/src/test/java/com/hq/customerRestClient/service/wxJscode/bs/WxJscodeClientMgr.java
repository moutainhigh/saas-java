package com.hq.customerRestClient.service.wxJscode.bs;

import com.hq.customerRestClient.service.wxJscode.apiData.DecryptWxAppletForm;
import com.hq.customerRestClient.service.wxJscode.apiData.WxJsCodeForm;
import com.hq.customerRestClient.service.wxJscode.data.UserInfo;
import com.hq.customerRestClient.service.wxJscode.data.WxJscode;
import com.hq.customerRestClient.service.wxJscode.data.WxJscodeDAO;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.common.json.JsonUtil;
import com.zenmind.dao.rest.RestResp;

public class WxJscodeClientMgr {

	public static WxJscodeClientMgr getInstance() {
		return HotSwap.getInstance().getSingleton(WxJscodeClientMgr.class);
	}
	
	public WxJscode jsCode2Session(WxJsCodeForm inputForm){
		String actionName = "jsCode2Session";
		RestResp restResp = WxJscodeDAO.getInstance().rawReq(actionName, inputForm);
		return JsonUtil.getInstance().fromJson(restResp.gettJson(), WxJscode.class);
	}
	
	public UserInfo decryptWXAppletInfo(DecryptWxAppletForm inputForm){
		String actionName = "decryptWXAppletInfo";
		RestResp restResp = WxJscodeDAO.getInstance().rawReq(actionName, inputForm);
		return JsonUtil.getInstance().fromJson(restResp.gettJson(), UserInfo.class);
	}
}

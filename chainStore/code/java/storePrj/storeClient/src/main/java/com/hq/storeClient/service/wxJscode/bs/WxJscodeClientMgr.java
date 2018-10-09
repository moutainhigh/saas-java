package com.hq.storeClient.service.wxJscode.bs;

import com.hq.storeClient.service.wxJscode.apiData.DecryptWxAppletForm;
import com.hq.storeClient.service.wxJscode.apiData.WxJsCodeForm;
import com.hq.storeClient.service.wxJscode.data.UserInfo;
import com.hq.storeClient.service.wxJscode.data.WxJscode;
import com.hq.storeClient.service.wxJscode.data.WxJscodeDAO;
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

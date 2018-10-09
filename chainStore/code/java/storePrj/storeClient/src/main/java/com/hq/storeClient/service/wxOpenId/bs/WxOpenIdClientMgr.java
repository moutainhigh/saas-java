package com.hq.storeClient.service.wxOpenId.bs;

import com.hq.storeClient.common.restClientResp.RestRespHelper;
import com.hq.storeClient.service.wxOpenId.apiData.GenOpenIdApiForm;
import com.hq.storeClient.service.wxOpenId.data.WxOpenId;
import com.hq.storeClient.service.wxOpenId.data.WxOpenIdDAO;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestResp;

public class WxOpenIdClientMgr {

	public static WxOpenIdClientMgr getInstance() {
		return HotSwap.getInstance().getSingleton(WxOpenIdClientMgr.class);
	}
	
	public WxOpenId genOpenId(GenOpenIdApiForm form){
		String actionName = "genOpenId";
		RestResp restResp = WxOpenIdDAO.getInstance().rawReq(actionName, form);
		return RestRespHelper.gettJsonObj(restResp, WxOpenId.class);
	}
}

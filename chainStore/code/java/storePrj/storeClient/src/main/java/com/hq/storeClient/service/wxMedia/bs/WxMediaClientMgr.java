package com.hq.storeClient.service.wxMedia.bs;

import com.hq.storeClient.common.restClientResp.ImgResp;
import com.hq.storeClient.common.restClientResp.RestRespHelper;
import com.hq.storeClient.service.wxMedia.apiData.WxMediaSaveApiForm;
import com.hq.storeClient.service.wxMedia.data.WxMediaDAO;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestResp;

public class WxMediaClientMgr {

	public static WxMediaClientMgr getInstance() {
		return HotSwap.getInstance().getSingleton(WxMediaClientMgr.class);
	}
	
	public ImgResp saveImg(WxMediaSaveApiForm form){
		String actionName = "saveImg";
		RestResp restResp = WxMediaDAO.getInstance().rawReq(actionName, form);
		return RestRespHelper.gettJsonObj(restResp, ImgResp.class);
	}
}

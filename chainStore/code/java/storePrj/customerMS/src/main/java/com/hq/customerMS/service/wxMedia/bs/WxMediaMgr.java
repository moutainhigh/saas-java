package com.hq.customerMS.service.wxMedia.bs;

import com.hq.storeClient.common.restClientResp.ImgResp;
import com.hq.storeClient.service.wxMedia.apiData.WxMediaSaveApiForm;
import com.zenmind.common.hotSwap.HotSwap;

public class WxMediaMgr {
	
	public static WxMediaMgr getInstance(){
		return HotSwap.getInstance().getSingleton(WxMediaMgr.class);
	}
	
	
	public ImgResp saveImg(WxMediaSaveApiForm form) {
		return WxMediaDataHolder.getInstance().saveImg(form);
	}
}

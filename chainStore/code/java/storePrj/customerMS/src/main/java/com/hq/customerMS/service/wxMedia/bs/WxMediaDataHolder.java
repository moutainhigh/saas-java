package com.hq.customerMS.service.wxMedia.bs;

import com.hq.customerMS.common.constants.ServerConstants;
import com.hq.customerMS.common.validate.AppIdThreadLocal;
import com.hq.storeClient.common.restClientResp.ImgResp;
import com.hq.storeClient.service.wxMedia.apiData.WxMediaSaveApiForm;
import com.hq.storeClient.service.wxMedia.bs.WxMediaClientMgr;
import com.zenmind.common.hotSwap.HotSwap;

public class WxMediaDataHolder {
	
	public static WxMediaDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(WxMediaDataHolder.class);
	}
	
	public ImgResp saveImg(WxMediaSaveApiForm form) {
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		ImgResp data = WxMediaClientMgr.getInstance().saveImg(form);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}	
}

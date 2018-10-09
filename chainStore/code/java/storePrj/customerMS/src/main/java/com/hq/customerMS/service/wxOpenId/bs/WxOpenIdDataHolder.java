package com.hq.customerMS.service.wxOpenId.bs;

import com.hq.customerMS.common.constants.ServerConstants;
import com.hq.customerMS.common.validate.AppIdThreadLocal;
import com.hq.storeClient.service.wxOpenId.apiData.GenOpenIdApiForm;
import com.hq.storeClient.service.wxOpenId.bs.WxOpenIdClientMgr;
import com.hq.storeClient.service.wxOpenId.data.WxOpenId;
import com.zenmind.common.hotSwap.HotSwap;

public class WxOpenIdDataHolder {
	
	public static WxOpenIdDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(WxOpenIdDataHolder.class);
	}
	
	public WxOpenId genOpenId(GenOpenIdApiForm form) {
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		WxOpenId data = WxOpenIdClientMgr.getInstance().genOpenId(form);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}	
}

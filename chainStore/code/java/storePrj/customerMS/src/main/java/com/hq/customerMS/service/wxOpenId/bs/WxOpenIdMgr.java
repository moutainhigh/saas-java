package com.hq.customerMS.service.wxOpenId.bs;

import com.hq.storeClient.service.wxOpenId.apiData.GenOpenIdApiForm;
import com.hq.storeClient.service.wxOpenId.data.WxOpenId;
import com.zenmind.common.hotSwap.HotSwap;

public class WxOpenIdMgr {
	
	public static WxOpenIdMgr getInstance(){
		return HotSwap.getInstance().getSingleton(WxOpenIdMgr.class);
	}
	
	
	public WxOpenId genOpenId(GenOpenIdApiForm form) {
		return WxOpenIdDataHolder.getInstance().genOpenId(form);
	}
}

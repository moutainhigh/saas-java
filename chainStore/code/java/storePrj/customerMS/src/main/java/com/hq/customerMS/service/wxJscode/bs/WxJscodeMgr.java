package com.hq.customerMS.service.wxJscode.bs;

import org.apache.commons.lang3.StringUtils;

import com.hq.customerMS.common.config.CustomerMSCfgMgr;
import com.hq.storeClient.service.wxJscode.apiData.DecryptWxAppletForm;
import com.hq.storeClient.service.wxJscode.apiData.WxJsCodeForm;
import com.hq.storeClient.service.wxJscode.data.UserInfo;
import com.hq.storeClient.service.wxJscode.data.WxJscode;
import com.zenmind.common.hotSwap.HotSwap;

public class WxJscodeMgr {
	
	public static WxJscodeMgr getInstance(){
		return HotSwap.getInstance().getSingleton(WxJscodeMgr.class);
	}
	
	public WxJscode jsCode2Session(WxJsCodeForm inputForm) {
		return WxJscodeDataHolder.getInstance().jsCode2Session(inputForm);
	}
	
	public UserInfo decryptWXAppletInfo(DecryptWxAppletForm inputForm) {
		if(StringUtils.isBlank(inputForm.getAppId())) {
			inputForm.setAppId(CustomerMSCfgMgr.getProp().getWxMarketingAppId());
		}
		return WxJscodeDataHolder.getInstance().decryptWXAppletInfo(inputForm);
	}
}

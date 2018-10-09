package com.hq.customerMS.service.wxJscode.bs;

import com.hq.customerMS.common.constants.ServerConstants;
import com.hq.customerMS.common.validate.AppIdThreadLocal;
import com.hq.storeClient.service.wxJscode.apiData.DecryptWxAppletForm;
import com.hq.storeClient.service.wxJscode.apiData.WxJsCodeForm;
import com.hq.storeClient.service.wxJscode.bs.WxJscodeClientMgr;
import com.hq.storeClient.service.wxJscode.data.UserInfo;
import com.hq.storeClient.service.wxJscode.data.WxJscode;
import com.zenmind.common.hotSwap.HotSwap;

public class WxJscodeDataHolder {

	public static WxJscodeDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(WxJscodeDataHolder.class);
	}

	public WxJscode jsCode2Session(WxJsCodeForm inputForm) {
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		WxJscode data = WxJscodeClientMgr.getInstance().jsCode2Session(inputForm);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}

	public UserInfo decryptWXAppletInfo(DecryptWxAppletForm inputForm) {
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		UserInfo data = WxJscodeClientMgr.getInstance().decryptWXAppletInfo(inputForm);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}

}

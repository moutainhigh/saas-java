package com.hq.storeMS.service.wxJscode.bs;

import com.hq.storeMS.service.wxJscode.apiData.DecryptWxAppletForm;
import com.hq.storeMS.service.wxJscode.data.UserInfo;
import com.hq.storeMS.service.wxJscode.data.WxJscode;
import com.hq.storeMS.service.wxJscode.data.WxJscodeRedisDAO;
import com.hq.storeMS.service.wxJscode.data.WxUserInfoAESUtil;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.wx.WxProxy;
import com.zenmind.wx.jscode2session.JsCode2SessionReqeustParams;
import com.zenmind.wx.jscode2session.JsCode2SessionResponse;

public class WxJscodeDataHolder {

	public static WxJscodeDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(WxJscodeDataHolder.class);
	}

	public JsCode2SessionResponse jsCode2Session(JsCode2SessionReqeustParams params) {
		return WxProxy.getInstance().jscode2session(params);
	}

	public WxJscode getWxJscode(String id) {
		return WxJscodeRedisDAO.getInstance().get(id);
	}

	public void saveWxJscode(WxJscode target) {
		WxJscodeRedisDAO.getInstance().save(target);
	}

	public UserInfo decryptWXAppletInfo(String sessionKey, DecryptWxAppletForm inputForm) {
		return WxUserInfoAESUtil.getInstance().getUserInfo(sessionKey, inputForm.getEncryptedData(), inputForm.getIv());
	}

}

package com.hq.storeMS.service.wxJscode.bs;

import org.apache.commons.lang3.StringUtils;

import com.hq.storeMS.common.config.StoreMSCfg;
import com.hq.storeMS.common.config.StoreMSCfgMgr;
import com.hq.storeMS.service.wxJscode.apiData.DecryptWxAppletForm;
import com.hq.storeMS.service.wxJscode.apiData.WxJsCodeForm;
import com.hq.storeMS.service.wxJscode.data.UserInfo;
import com.hq.storeMS.service.wxJscode.data.WxJscode;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.wx.jscode2session.JsCode2SessionReqeustParams;
import com.zenmind.wx.jscode2session.JsCode2SessionResponse;

public class WxJscodeMgr {
	
	public static WxJscodeMgr getInstance(){
		return HotSwap.getInstance().getSingleton(WxJscodeMgr.class);
	}
	
	public WxJscode jsCode2Session(WxJsCodeForm inputForm) {
		String appId = inputForm.getAppId();
		String jscode = inputForm.getJscode();
		JsCode2SessionReqeustParams params = buildJsCode2SessionReqeustParams(appId, jscode);
		//appId不存在
		if(params == null) {
			return null;
		}
		JsCode2SessionResponse response = WxJscodeDataHolder.getInstance().jsCode2Session(params);
		if(response == null) {
			return null;
		}
		WxJscode target = WxJscode.newInstance(jscode, response);
		//获取信息成功 保存sessionKey
		if(target.getErrcode() == 0) {
			WxJscodeDataHolder.getInstance().saveWxJscode(target);
		}
		return target;
	}
	
	public UserInfo decryptWXAppletInfo(DecryptWxAppletForm inputForm) {
		String jscode = inputForm.getJscode();
		WxJscode target = WxJscodeDataHolder.getInstance().getWxJscode(jscode);
		if(target == null) {
			WxJsCodeForm params = WxJsCodeForm.newInstance();
			params.setAppId(inputForm.getAppId());
			params.setJscode(jscode);
			target = jsCode2Session(params);
		} 
		if(target == null || StringUtils.isBlank(target.getSessionKey())) {
			return null;
		}
		String sessionKey = target.getSessionKey();
		return WxJscodeDataHolder.getInstance().decryptWXAppletInfo(sessionKey, inputForm);
	}
	
	private JsCode2SessionReqeustParams buildJsCode2SessionReqeustParams(String appId, String jscode) {
		StoreMSCfg cfg = StoreMSCfgMgr.getProp();
		String secret = "";
		//小程序的appId和主体公众号的appId分别对应不同的openid
		if (cfg.getWxAppId().equals(appId)) {
		    secret = cfg.getWxSecret();
		} else if (cfg.getWxMainAppId().equals(appId)) {
			secret = cfg.getWxMainSecret();
		} else if(cfg.getWxMarketingAppId().equals(appId)){
			secret = cfg.getWxMarketingSecret();
		}else {
			return null;
		}
		return JsCode2SessionReqeustParams.newInstance(appId, secret, jscode);
	}
}

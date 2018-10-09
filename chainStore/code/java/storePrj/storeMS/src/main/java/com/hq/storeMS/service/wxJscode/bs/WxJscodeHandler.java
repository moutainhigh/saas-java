package com.hq.storeMS.service.wxJscode.bs;

import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RespStatus;
import com.hq.storeMS.service.wxJscode.apiData.DecryptWxAppletForm;
import com.hq.storeMS.service.wxJscode.apiData.WxJsCodeForm;
import com.hq.storeMS.service.wxJscode.data.UserInfo;
import com.hq.storeMS.service.wxJscode.data.WxJscode;
import com.zenmind.common.hotSwap.HotSwap;

public class WxJscodeHandler {
	public static WxJscodeHandler getInstance() {
		return HotSwap.getInstance().getSingleton(WxJscodeHandler.class);
	}

	public ReqResult<WxJscode> jsCode2Session(WxJsCodeForm inputForm) {
		ReqResult<WxJscode> result = ReqResult.newInstance(false, WxJscode.class);
		try {
			WxJscode target = WxJscodeMgr.getInstance().jsCode2Session(inputForm);
			result.setTarget(target);
			result.setSuccess(true);
		} catch (Exception e) {
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(LogModule.WeChat, "WxJscodeHandler[jsCode2Session]", "", e);
		}
		return result;
	}
	
	public ReqResult<UserInfo> decryptWXAppletInfo(DecryptWxAppletForm inputForm) {
		ReqResult<UserInfo> result = ReqResult.newInstance(false, UserInfo.class);
		try {
			UserInfo target = WxJscodeMgr.getInstance().decryptWXAppletInfo(inputForm);
			result.setTarget(target);
			result.setSuccess(true);
		} catch (Exception e) {
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(LogModule.WeChat, "WxJscodeHandler[decryptWXAppletInfo]", "", e);
		}
		return result;
	}

}

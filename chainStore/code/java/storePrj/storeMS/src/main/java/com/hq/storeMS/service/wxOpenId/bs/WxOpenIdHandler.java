package com.hq.storeMS.service.wxOpenId.bs;

import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RespStatus;
import com.hq.storeMS.service.wxOpenId.apiData.GenOpenIdApiForm;
import com.hq.storeMS.service.wxOpenId.data.WxOpenId;
import com.zenmind.common.hotSwap.HotSwap;

public class WxOpenIdHandler {
	public static WxOpenIdHandler getInstance() {
		return HotSwap.getInstance().getSingleton(WxOpenIdHandler.class);
	}
	
	public ReqResult<WxOpenId> genOpenId(GenOpenIdApiForm form) {
		ReqResult<WxOpenId> result = ReqResult.newInstance(false, WxOpenId.class);
		try {
			WxOpenId target = WxOpenIdMgr.getInstance().genOpenId(form.getAppId(), form.getJsCode());
			result.setTarget(target);
			result.setSuccess(true);
		} catch (Exception e) {
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(LogModule.WeChat, "WxOpenIdHandler[genOpenId]", "", e);
		}
		return result;
	}

}

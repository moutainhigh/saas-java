package com.hq.customerMS.service.wxOpenId.bs;

import com.hq.customerMS.common.log.LogHelper;
import com.hq.customerMS.common.log.LogModule;
import com.hq.customerMS.service.common.ExceptionInfo;
import com.hq.customerMS.service.common.HandlerHelper;
import com.hq.customerMS.service.common.ReqResult;
import com.hq.storeClient.service.wxOpenId.apiData.GenOpenIdApiForm;
import com.hq.storeClient.service.wxOpenId.data.WxOpenId;
import com.zenmind.common.hotSwap.HotSwap;

public class WxOpenIdHandler {
	public static WxOpenIdHandler getInstance() {
		return HotSwap.getInstance().getSingleton(WxOpenIdHandler.class);
	}
	
	public ReqResult<WxOpenId> genOpenId(GenOpenIdApiForm form) {
		ReqResult<WxOpenId> result = ReqResult.newInstance(false, WxOpenId.class);
		try {
			WxOpenId target = WxOpenIdMgr.getInstance().genOpenId(form);
			result.setTarget(target);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "WxOpenIdHandler[genOpenId]";
			final String reason = LogHelper.getInstance().exceptionReason(form);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(LogModule.WeChat)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}

}

package com.hq.customerMS.service.wxMedia.bs;

import com.hq.customerMS.common.log.LogHelper;
import com.hq.customerMS.common.log.LogModule;
import com.hq.customerMS.service.common.ExceptionInfo;
import com.hq.customerMS.service.common.HandlerHelper;
import com.hq.customerMS.service.common.ReqResult;
import com.hq.storeClient.common.restClientResp.ImgResp;
import com.hq.storeClient.service.wxMedia.apiData.WxMediaSaveApiForm;
import com.zenmind.common.hotSwap.HotSwap;

public class WxMediaHandler {
	public static WxMediaHandler getInstance() {
		return HotSwap.getInstance().getSingleton(WxMediaHandler.class);
	}
	
	public ReqResult<ImgResp> saveImg(WxMediaSaveApiForm form) {
		ReqResult<ImgResp> result = ReqResult.newInstance(false, ImgResp.class);
		try {
			ImgResp target = WxMediaMgr.getInstance().saveImg(form);
			result.setTarget(target);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "WxMediaHandler[saveImg]";
			final String reason = LogHelper.getInstance().exceptionReason(form);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(LogModule.WeChat)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}

}

package com.hq.customerMS.service.wxJsApiTicket.bs;

import com.hq.customerMS.common.log.LogHelper;
import com.hq.customerMS.common.log.LogModule;
import com.hq.customerMS.service.common.ExceptionInfo;
import com.hq.customerMS.service.common.HandlerHelper;
import com.hq.customerMS.service.common.ReqResult;
import com.hq.storeClient.service.wxJsApiTicket.apiData.GenJssdkSignatureApiForm;
import com.hq.storeClient.service.wxJsApiTicket.data.WxJsApiTicket;
import com.zenmind.common.hotSwap.HotSwap;

public class WxJsApiTicketHandler {
	public static WxJsApiTicketHandler getInstance() {
		return HotSwap.getInstance().getSingleton(WxJsApiTicketHandler.class);
	}
	
	public ReqResult<WxJsApiTicket> genJssdkSignature(GenJssdkSignatureApiForm form) {
		ReqResult<WxJsApiTicket> result = ReqResult.newInstance(false, WxJsApiTicket.class);
		try {
			WxJsApiTicket target = WxJsApiTicketMgr.getInstance().genJssdkSignature(form);
			result.setTarget(target);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "WxJsApiTicketHandler[genJssdkSignature]";
			final String reason = LogHelper.getInstance().exceptionReason(form);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(LogModule.WeChat)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}

}

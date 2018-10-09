package com.hq.storeMS.service.wxJsApiTicket.bs;

import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RespStatus;
import com.hq.storeMS.service.wxJsApiTicket.apiData.GenJssdkSignatureApiForm;
import com.hq.storeMS.service.wxJsApiTicket.bs.helper.WxJsApiTicketHelper;
import com.hq.storeMS.service.wxJsApiTicket.data.WxJsApiTicket;
import com.zenmind.common.hotSwap.HotSwap;

public class WxJsApiTicketHandler {
	public static WxJsApiTicketHandler getInstance() {
		return HotSwap.getInstance().getSingleton(WxJsApiTicketHandler.class);
	}
	
	public ReqResult<WxJsApiTicket> genJssdkSignature(GenJssdkSignatureApiForm form) {
		ReqResult<WxJsApiTicket> result = ReqResult.newInstance(false, WxJsApiTicket.class);
		try {
			WxJsApiTicket target = WxJsApiTicketMgr.getInstance().findByAppId(form.getAppId());
			if (target != null) {
				target = WxJsApiTicketHelper.getInstance().generateSignature(target, form.getPageUrl());
				result.setTarget(target);
				result.setSuccess(true);
			} else {
				result.setTips("WxJsApiTicket获取失败");
				result.setStatus(RespStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(LogModule.WeChat, "WxJsApiTicketHandler[genJssdkSignature]", "", e);
		}
		return result;
	}

}

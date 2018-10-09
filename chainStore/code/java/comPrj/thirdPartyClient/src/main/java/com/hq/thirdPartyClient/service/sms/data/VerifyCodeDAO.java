package com.hq.thirdPartyClient.service.sms.data;

import com.hq.thirdPartyClient.service.common.ThirdPartyClientCfg;
import com.zenmind.common.StringFormatUtil;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.common.json.JsonUtil;
import com.zenmind.dao.rest.RestProxy;
import com.zenmind.dao.rest.RestResp;

public class VerifyCodeDAO{

	public static VerifyCodeDAO getInstance() {
		return HotSwap.getInstance().getSingleton(VerifyCodeDAO.class);
	}
	
	private final String sendSmsPath = "sms/sendSmsByYunPian";
	
	public SmsResp sendSmsByYunPian(SmsServerForm apiForm) {
		final String uriPattern = "{}/{}";
		String uri = StringFormatUtil.format(uriPattern, ThirdPartyClientCfg.getThirdPartyServer(), this.sendSmsPath);
		RestResp restResp = RestProxy.getInstance().rawReq(uri, apiForm);
		return JsonUtil.getInstance().fromJson(restResp.gettJson(), SmsResp.class);
	}
	
}

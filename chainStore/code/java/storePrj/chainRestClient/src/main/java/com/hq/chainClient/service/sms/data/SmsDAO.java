package com.hq.chainClient.service.sms.data;

import com.hq.chainClient.common.restClientResp.RestClientCfg;
import com.hq.chainClient.service.sms.apiData.RandomCodeAPIForm;
import com.hq.chainClient.service.sms.apiData.SmsResp;
import com.zenmind.common.StringFormatUtil;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.common.json.JsonUtil;
import com.zenmind.dao.rest.RestProxy;
import com.zenmind.dao.rest.RestResp;

public class SmsDAO {

	public static SmsDAO getInstance() {
		return HotSwap.getInstance().getSingleton(SmsDAO.class);
	}
	
	public String getService() {
		return RestClientCfg.getService();
	}

	public SmsResp sendRandomCode(RandomCodeAPIForm apiForm) {
		final String uriPattern = "{}/{}/{}";
		String uri = StringFormatUtil.format(uriPattern, getService(), "sms", "sendRandomCode");
		RestResp restResp = RestProxy.getInstance().rawReq(uri, apiForm);
		return JsonUtil.getInstance().fromJson(restResp.gettJson(), SmsResp.class);
	}
	
}

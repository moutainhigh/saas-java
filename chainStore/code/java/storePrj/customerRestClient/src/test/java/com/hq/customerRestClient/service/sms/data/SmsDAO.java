package com.hq.customerRestClient.service.sms.data;

import com.hq.customerRestClient.common.restClientResp.RestClientCfg;
import com.hq.customerRestClient.service.sms.apiData.RandomCodeAPIForm;
import com.zenmind.common.StringFormatUtil;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.common.json.JsonUtil;
import com.zenmind.dao.rest.RestProxy;
import com.zenmind.dao.rest.RestResp;

public class SmsDAO {

	public static SmsDAO getInstance() {
		return HotSwap.getInstance().getSingleton(SmsDAO.class);
	}
	
	private final String module = "sms";
	
	public SmsResp sendRandomCode(RandomCodeAPIForm apiForm) {
		final String uriPattern = "{}/{}/{}";
		String uri = StringFormatUtil.format(uriPattern, RestClientCfg.getService(), this.module, "sendRandomCode");
		RestResp restResp = RestProxy.getInstance().rawReq(uri, apiForm);
		return JsonUtil.getInstance().fromJson(restResp.gettJson(), SmsResp.class);
	}
	
}

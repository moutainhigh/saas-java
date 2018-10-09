package com.hq.customerRestClient.service.sms.bs;

import com.hq.customerRestClient.service.sms.apiData.RandomCodeAPIForm;
import com.hq.customerRestClient.service.sms.data.SmsDAO;
import com.hq.customerRestClient.service.sms.data.SmsResp;
import com.zenmind.common.hotSwap.HotSwap;

public class SmsClientMgr {

	public static SmsClientMgr getInstance() {
		return HotSwap.getInstance().getSingleton(SmsClientMgr.class);
	}
	
	public SmsResp sendRandomCode(RandomCodeAPIForm apiForm){
		return SmsDAO.getInstance().sendRandomCode(apiForm);
	}

}

package com.hq.storeManagerRestClient.service.sms.bs;

import com.hq.storeManagerRestClient.service.sms.apiData.RandomCodeAPIForm;
import com.hq.storeManagerRestClient.service.sms.apiData.SmsResp;
import com.hq.storeManagerRestClient.service.sms.data.SmsDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class SmsMgr {

	public static SmsMgr getInstance() {
		return HotSwap.getInstance().getSingleton(SmsMgr.class);
	}
	
	public SmsResp sendRandomCode(RandomCodeAPIForm apiForm){
		return SmsDAO.getInstance().sendRandomCode(apiForm);
	}
}

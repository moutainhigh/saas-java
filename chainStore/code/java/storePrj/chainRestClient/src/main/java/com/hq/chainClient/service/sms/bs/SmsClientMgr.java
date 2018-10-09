package com.hq.chainClient.service.sms.bs;

import com.hq.chainClient.service.sms.apiData.RandomCodeAPIForm;
import com.hq.chainClient.service.sms.apiData.SmsResp;
import com.hq.chainClient.service.sms.data.SmsDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class SmsClientMgr {

	public static SmsClientMgr getInstance() {
		return HotSwap.getInstance().getSingleton(SmsClientMgr.class);
	}
	
	public SmsResp sendRandomCode(RandomCodeAPIForm apiForm){
		return SmsDAO.getInstance().sendRandomCode(apiForm);
	}

}

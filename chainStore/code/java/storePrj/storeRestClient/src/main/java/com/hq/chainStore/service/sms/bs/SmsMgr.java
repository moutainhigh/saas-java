package com.hq.chainStore.service.sms.bs;

import com.hq.chainStore.service.sms.apiData.RandomCodeAPIForm;
import com.hq.chainStore.service.sms.apiData.SmsAPIForm;
import com.hq.chainStore.service.sms.apiData.SmsResp;
import com.hq.chainStore.service.sms.data.SmsDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class SmsMgr {

	public static SmsMgr getInstance() {
		return HotSwap.getInstance().getSingleton(SmsMgr.class);
	}
	
	public SmsResp sendSms(SmsAPIForm apiForm){
        return SmsDAO.getInstance().sendSms(apiForm);
	}
	
	public SmsResp sendRandomCode(RandomCodeAPIForm apiForm){
		return SmsDAO.getInstance().sendRandomCode(apiForm);
	}

}

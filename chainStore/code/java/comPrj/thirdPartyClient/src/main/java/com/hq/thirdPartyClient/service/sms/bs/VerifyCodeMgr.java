package com.hq.thirdPartyClient.service.sms.bs;

import com.hq.thirdPartyClient.service.sms.apiData.VerifyCodeForm;
import com.hq.thirdPartyClient.service.sms.data.SmsResp;
import com.hq.thirdPartyClient.service.sms.data.SmsResult;
import com.hq.thirdPartyClient.service.sms.data.SmsServerForm;
import com.hq.thirdPartyClient.service.sms.data.SmsSuccessEnum;
import com.hq.thirdPartyClient.service.sms.data.VerifyCodeDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class VerifyCodeMgr {

	public static VerifyCodeMgr getInstance() {
		return HotSwap.getInstance().getSingleton(VerifyCodeMgr.class);
	}
	
	public SmsResult sendSmsByYunPian(VerifyCodeForm verifyCodeForm){
		SmsResult result = SmsResult.newInstance(false);
		SmsServerForm apiForm = SmsServerForm.newInstanceByVerifyCodeForm(verifyCodeForm);
		SmsResp smsResp = VerifyCodeDAO.getInstance().sendSmsByYunPian(apiForm);
		if(smsResp != null && SmsSuccessEnum.RESPONSE_SUCCESS.getCode().equals(smsResp.getCode()+"")) {
			result.setSuccess(true);
			result.setVerifyCode(apiForm.getVerifyCode());
		}
		return result;
	}

}

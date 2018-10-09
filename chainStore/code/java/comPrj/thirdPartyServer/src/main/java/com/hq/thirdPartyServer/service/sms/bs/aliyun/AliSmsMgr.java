package com.hq.thirdPartyServer.service.sms.bs.aliyun;

import com.alibaba.cloudapi.sdk.core.model.ApiResponse;
import com.hq.thirdPartyServer.common.config.ThirdPartyServerCfgMgr;
import com.hq.thirdPartyServer.service.sms.apiData.SmsAPIForm;
import com.hq.thirdPartyServer.service.sms.apiData.SmsTypeEnum;
import com.hq.thirdPartyServer.service.sms.data.SyncApiClientSms;
import com.zenmind.common.hotSwap.HotSwap;

/**
 * 阿里短信
 * @author Administrator
 *
 */
public class AliSmsMgr {

	public static AliSmsMgr getInstance() {
		return HotSwap.getInstance().getSingleton(AliSmsMgr.class);
	}
	
	public ApiResponse sendSms(SmsAPIForm apiForm){
		SyncApiClientSms clientSms = SyncApiClientSms.newBuilder()
					.appKey(ThirdPartyServerCfgMgr.getProp().getAppKey())
					.appSecret(ThirdPartyServerCfgMgr.getProp().getAppSecret())
					.build();
		ApiResponse response = null;
		SmsTypeEnum smsType = SmsTypeEnum.valueOf(apiForm.getSmsType());
		switch (smsType) {
			case B_VERIFICATION_CODE:
		        response = clientSms.singleSendSms(ThirdPartyServerCfgMgr.getProp().getRegCode(), apiForm.getSignName(), apiForm.getPhoneNumbers(), apiForm.getContent());
				break;
			case C_VERIFICATION_CODE:
				response = clientSms.singleSendSms(ThirdPartyServerCfgMgr.getProp().getRegCode(), apiForm.getSignName(), apiForm.getPhoneNumbers(), apiForm.getContent());
				break;
			case INVITATION_CODE:
				response = clientSms.singleSendSms(ThirdPartyServerCfgMgr.getProp().getInviteMessage(), apiForm.getSignName(), apiForm.getPhoneNumbers(), apiForm.getContent());
				break;
			default:
				break;
		}
        return response;
	}
}

package com.hq.storeMS.service.sms.bs;

import org.apache.commons.lang3.StringUtils;

import com.hq.storeMS.common.config.StoreMSCfgMgr;
import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.service.common.HandlerHelper;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.common.OriginTypeEnum;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RespStatus;
import com.hq.storeMS.service.sms.apiData.RandomCodeAPIForm;
import com.hq.storeMS.service.sms.apiData.SmsAPIForm;
import com.hq.storeMS.service.sms.apiData.SmsResp;
import com.hq.thirdPartyClient.service.sms.apiData.VerifyCodeForm;
import com.hq.thirdPartyClient.service.sms.data.SmsResult;
import com.hq.thirdPartyClient.service.sms.data.SmsSuccessEnum;
import com.hq.thirdPartyClient.service.sms.data.SmsTypeEnum;
import com.zenmind.common.hotSwap.HotSwap;

public class SmsHandler {

	public static SmsHandler getInstance() {
		return HotSwap.getInstance().getSingleton(SmsHandler.class);
	}
	
	private final LogModule logModule = LogModule.Sms;

	public ReqResult<SmsResp> sendSms(SmsAPIForm apiForm) {
		RandomCodeAPIForm randomCodeForm = RandomCodeAPIForm.newInstance();
		randomCodeForm.setPhoneNumber(apiForm.getPhoneNumbers());
		return sendRandomCode(randomCodeForm);
	}
	
	public ReqResult<SmsResp> sendRandomCode(RandomCodeAPIForm randomCodeForm) {
		ReqResult<SmsResp> result = ReqResult.newInstance(false, SmsResp.class);
		try {
			SmsResp smsResp = SmsResp.newInstance();
			if(!StoreMSCfgMgr.getProp().isSmsCodeOpen()) {
				smsResp.setCode(SmsSuccessEnum.RETURN_SUCCESS.getCode());
				smsResp.setMessage("发送成功");
				result.setTarget(smsResp);
				result.setSuccess(true);
				return result;
			}
			
			OperateTips operateTips =checkPhoneNumber(randomCodeForm.getPhoneNumber()); 
			if(!operateTips.isSuccess()){
				HandlerHelper.getInstance().handleReqResult(result, operateTips);
				return result;
			}
			
			VerifyCodeForm verifyCodeForm = VerifyCodeForm.newInstance(randomCodeForm.getPhoneNumber(), SmsTypeEnum.B_VERIFICATION_CODE.ordinal(), OriginTypeEnum.STORE.ordinal());
			SmsResult smsResult=SmsMgr.getInstance().sendSms(verifyCodeForm);
			if(smsResult.isSuccess()){
				smsResp.setCode(SmsSuccessEnum.RETURN_SUCCESS.getCode());
				smsResp.setMessage("发送成功");
				result.setTarget(smsResp);
				result.setSuccess(true);
			} else {
				result.setTips("send message failure");
				result.setStatus(RespStatus.INVALID_REQUEST);
			}
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(randomCodeForm);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(logModule, "SmsHandler[sendRandomCode]", reason, e);
		}
		return result;
	}
	
	// 是否包含有无效的手机号码
	private OperateTips checkPhoneNumber(String phoneNumbers){
		OperateTips operateTips = OperateTips.newInstance();
		if(StringUtils.isEmpty(phoneNumbers)){
			operateTips.setSuccess(false);
			operateTips.setTips("手机号码为空");
		}
		return operateTips;
	}
}

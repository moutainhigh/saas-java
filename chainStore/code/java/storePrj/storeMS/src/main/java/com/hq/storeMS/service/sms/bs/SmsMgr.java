package com.hq.storeMS.service.sms.bs;

import java.util.List;

import com.hq.storeMS.common.constants.ServerConstants;
import com.hq.storeMS.service.areaCode.apiData.AreaCodeQueryForm;
import com.hq.storeMS.service.areaCode.bs.AreaCodeMgr;
import com.hq.storeMS.service.areaCode.data.AreaCode;
import com.hq.storeMS.service.sms.data.Sms;
import com.hq.thirdPartyClient.service.sms.apiData.VerifyCodeForm;
import com.hq.thirdPartyClient.service.sms.bs.VerifyCodeMgr;
import com.hq.thirdPartyClient.service.sms.data.SmsResult;
import com.hq.thirdPartyClient.service.sms.data.SmsTypeEnum;
import com.zenmind.common.hotSwap.HotSwap;

public class SmsMgr {

	public static SmsMgr getInstance() {
		return HotSwap.getInstance().getSingleton(SmsMgr.class);
	}
	
	public SmsResult sendSms(VerifyCodeForm verifyCodeForm){
		SmsResult result = VerifyCodeMgr.getInstance().sendSmsByYunPian(verifyCodeForm);
		if(result.isSuccess()){
			saveVerifyCode(verifyCodeForm, result.getVerifyCode());
		}
        return result;
	}
	
	public void addAndReturnId(Sms target){
		SmsDataHolder.getInstance().addAndReturnId(target);
	}
	
	public void update(Sms target){
		SmsDataHolder.getInstance().updpate(target);
	}
	
	public void save2Redis(Sms target){
		SmsDataHolder.getInstance().save2Redis(target);
	}
	
	public void removeSmsRedis(String phone){
		SmsDataHolder.getInstance().deleteFromRedis(phone);
	}
	
	public Sms getSmsFromRedis(String phone){
		return SmsDataHolder.getInstance().getFromRedis(phone);
	}
	
	//保存验证码到Redis和mongodb
	private void saveVerifyCode(VerifyCodeForm verifyCodeForm, String verifyCode){
		SmsTypeEnum smsTypeEnum = SmsTypeEnum.valueOf(verifyCodeForm.getSmsType());
		if(SmsTypeEnum.B_VERIFICATION_CODE == smsTypeEnum || SmsTypeEnum.C_VERIFICATION_CODE == smsTypeEnum){//短信验证
			String phoneStr = verifyCodeForm.getPhoneNumbers().split(",")[0];
			Sms sms = Sms.newInstance();
			sms.setPhone(takePhone(phoneStr));
			sms.setVerifyCode(verifyCode);
			addAndReturnId(sms);
			save2Redis(sms);
		}
	}
	
	private long takePhone(String phoneStr) {
		String phone = phoneStr;
		AreaCodeQueryForm queryForm = AreaCodeQueryForm.newInstance();
		queryForm.setPageItemCount(ServerConstants.PAGE_ITEM_COUNT);
		queryForm.setPageNo(1);
		List<AreaCode> areaCodeList = AreaCodeMgr.getInstance().findByCond(queryForm);
		for (AreaCode areaCode : areaCodeList) {
			if(phoneStr.startsWith(areaCode.getAreaCode())) {
				phone=phoneStr.replace(areaCode.getAreaCode(), "");
				break;
			}
		}
		return Long.valueOf(phone);
	}
}

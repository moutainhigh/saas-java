package com.hq.storeMS.service.sms.bs.check;

import org.apache.commons.lang3.StringUtils;

import com.hq.storeMS.common.config.StoreMSCfgMgr;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.sms.bs.SmsMgr;
import com.hq.storeMS.service.sms.data.Sms;
import com.hq.storeMS.service.sms.data.SmsUseEnum;
import com.zenmind.common.hotSwap.HotSwap;

public class SmsCheckMgr {

	public static SmsCheckMgr getInstance() {
		return HotSwap.getInstance().getSingleton(SmsCheckMgr.class);
	}
	
	//短信验证码
	public OperateTips checkSmsCode(long phone, String verifyCode) {
		String phoneStr = String.valueOf(phone);
		OperateTips tips = OperateTips.newInstance(false, "验证码错误");
		
		//不开启短信验证
		if(!StoreMSCfgMgr.getProp().isSmsCodeOpen()){
			tips.setSuccess(true);
			return tips;
		}
		
		//短信最少4位数字
		if( !(verifyCode!=null && verifyCode.length() > 3) ){
			return tips;
		}
		
		Sms sms = SmsMgr.getInstance().getSmsFromRedis(phoneStr);
		if(sms != null && StringUtils.equals(verifyCode, sms.getVerifyCode())){//短信验证成功
			sms.setUsingTime(System.currentTimeMillis());
			sms.setIsUse(SmsUseEnum.HAS_USE.ordinal());
			SmsMgr.getInstance().update(sms);
			SmsMgr.getInstance().removeSmsRedis(phoneStr);
			tips.setSuccess(true);
		}else{
			tips.setTips("输入的验证码错误，请重新输入");
		}
		return tips;
	}
}

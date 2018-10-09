package com.hq.chainMS.service.chainUser.bs.handler;

import com.hq.chainMS.service.chainUser.apiData.RegistForm;
import com.hq.chainMS.service.chainUser.bs.ChainUserMgr;
import com.hq.chainMS.service.chainUser.data.ChainUser;
import com.hq.chainMS.service.common.OperateTips;
import com.hq.chainMS.service.sms.bs.handle.SmsHandleHelper;
import com.zenmind.common.hotSwap.HotSwap;

public class ChainUserAddHandle {

	public static ChainUserAddHandle getInstance() {
		return HotSwap.getInstance().getSingleton(ChainUserAddHandle.class);
	}
	
	public OperateTips addChainUser(RegistForm inputForm){
		long phone = inputForm.getPhone();
		
		OperateTips operateTips = OperateTips.newInstance(false, "注册失败");
		operateTips =SmsHandleHelper.getInstance().checkSmsCode(phone, inputForm.getVerifyCode());
		if(!operateTips.isSuccess()){
			return operateTips;
		}
		
		operateTips = checkUserPhone(phone);
		if(!operateTips.isSuccess()){
			return operateTips;
		}
		ChainUser chainUser = inputForm.toChainUser();
		ChainUserMgr.getInstance().createUser(chainUser);
		operateTips.setSuccess(true);
		return operateTips;
	}
	
	private OperateTips checkUserPhone(long phone) {
		OperateTips tips = OperateTips.newInstance(false, "该手机号码已注册");
		if(phone == 0L){
			tips.setTips("无效的手机号码");
		}else{
			ChainUser existPhone = ChainUserMgr.getInstance().findByPhone(phone);
			if(existPhone == null){
				tips.setSuccess(true);
			}
		}
		return tips;
	}
}

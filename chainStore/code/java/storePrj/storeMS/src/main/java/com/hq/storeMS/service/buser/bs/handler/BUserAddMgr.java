package com.hq.storeMS.service.buser.bs.handler;

import com.hq.storeMS.service.buser.apiData.BUserAddApiForm;
import com.hq.storeMS.service.buser.apiData.BUserAddByChainForm;
import com.hq.storeMS.service.buser.bs.BUserModifyMgr;
import com.hq.storeMS.service.buser.bs.BUserQueryMgr;
import com.hq.storeMS.service.buser.data.BUser;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.sms.bs.check.SmsCheckMgr;
import com.zenmind.common.hotSwap.HotSwap;

public class BUserAddMgr {

	public static BUserAddMgr getInstance() {
		return HotSwap.getInstance().getSingleton(BUserAddMgr.class);
	}
	
	//注册用户 从连锁店后台直接注册
	public OperateTips regByChainForm(BUserAddByChainForm formInfo){
		OperateTips operateTips = OperateTips.newInstance(false);
		
		BUser buser = BUserQueryMgr.getInstance().findByPhone(formInfo.getPhone());
		if(buser == null){
			buser = formInfo.toBUser();
			BUserModifyMgr.getInstance().addUser(buser);
		}else {
			buser.getChainIds().add(formInfo.getChainId());
			BUserModifyMgr.getInstance().update(buser);
		}
		operateTips.setSuccess(true);
		return operateTips;
	}
	
	public OperateTips addBUser(BUserAddApiForm formInfo){
		long phone = formInfo.getPhone();
		
		OperateTips operateTips = SmsCheckMgr.getInstance().checkSmsCode(phone, formInfo.getVerifyCode());
		if(!operateTips.isSuccess()){
			return operateTips;
		}
		
//		operateTips = ActivateCodeMgr.getInstance().checkActiveCode(formInfo.getActivateCode());
//		if(!operateTips.isSuccess()){
//			return operateTips;
//		}
		
		operateTips = checkUserPhone(phone);
		if(!operateTips.isSuccess()){
			return operateTips;
		}
		
		BUser buser = formInfo.toBUser();
		
		BUserModifyMgr.getInstance().createUser(buser);
		operateTips.setSuccess(true);
		
		return operateTips;
	}
	
	private OperateTips checkUserPhone(long phone) {
		OperateTips tips = OperateTips.newInstance(false, "该手机号码已注册");
		if(phone == 0L){
			tips.setTips("无效的手机号码");
		}else{
			BUser existPhone = BUserQueryMgr.getInstance().findByPhone(phone);
			if(existPhone == null){
				tips.setSuccess(true);
			}
		}
		return tips;
	}
}

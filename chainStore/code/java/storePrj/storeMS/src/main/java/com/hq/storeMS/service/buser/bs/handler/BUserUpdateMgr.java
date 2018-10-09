package com.hq.storeMS.service.buser.bs.handler;

import org.apache.commons.lang3.StringUtils;

import com.hq.storeMS.service.auth.buser.BUserPwdHelper;
import com.hq.storeMS.service.buser.apiData.BUserBindingWeChatForm;
import com.hq.storeMS.service.buser.apiData.BUserChangePasswordApiData;
import com.hq.storeMS.service.buser.apiData.BUserUnBindingWeChatForm;
import com.hq.storeMS.service.buser.apiData.BUserUpdateInfoApiData;
import com.hq.storeMS.service.buser.apiData.BUserUpdateType;
import com.hq.storeMS.service.buser.bs.BUserModifyMgr;
import com.hq.storeMS.service.buser.bs.BUserQueryMgr;
import com.hq.storeMS.service.buser.data.BUser;
import com.hq.storeMS.service.common.OperateTips;
import com.zenmind.common.hotSwap.HotSwap;

public class BUserUpdateMgr {
	
	public static BUserUpdateMgr getInstance() {
		return HotSwap.getInstance().getSingleton(BUserUpdateMgr.class);
	}
	
	public OperateTips unBindingWeChat(BUserUnBindingWeChatForm inputData){
		OperateTips tips = OperateTips.newInstance(false, BUserUpdateType.UnBindingWeChat.getDescript()+"失败");
		BUser buser = BUserQueryMgr.getInstance().getSimple(inputData.getBuserId());
		inputData.updateBuser(buser);
		BUserModifyMgr.getInstance().update(buser);
		tips.setSuccess(true);
		return tips;
	}
	
	public OperateTips bindingWeChat(BUserBindingWeChatForm inputData){
		OperateTips tips = OperateTips.newInstance(false, BUserUpdateType.BindingWeChat.getDescript()+"失败");
		BUser buser = BUserQueryMgr.getInstance().getSimple(inputData.getBuserId());
		inputData.updateBuser(buser);
		BUserModifyMgr.getInstance().update(buser);
		tips.setSuccess(true);
		return tips;
	}
	
	public OperateTips updateInfo(BUserUpdateInfoApiData inputData){
		OperateTips tips = OperateTips.newInstance(false, BUserUpdateType.updateInfo.getDescript()+"失败");
		BUserModifyMgr.getInstance().updateInfo(inputData);
		tips.setSuccess(true);
		return tips;
	}

	public OperateTips changePassword(BUserChangePasswordApiData inputData){
		OperateTips tips = OperateTips.newInstance(false, BUserUpdateType.changePassword.getDescript()+"失败");
		BUser buser = BUserQueryMgr.getInstance().get(inputData.getBuserId());
		if(checkOldPassword(buser, inputData.getOldPassword())){
			BUserModifyMgr.getInstance().changePassword(buser, inputData.getPassword());
			tips.setSuccess(true);
		}
		return tips;
	}
	
	private boolean checkOldPassword(BUser buser,String oldPassword){
		String encryptPassword = BUserPwdHelper.getInstance().getEncryptPassword(buser, oldPassword);
		if(StringUtils.equals(encryptPassword, buser.getPassword())){
			return true;
		}else{
			return false;
		}
	}
}

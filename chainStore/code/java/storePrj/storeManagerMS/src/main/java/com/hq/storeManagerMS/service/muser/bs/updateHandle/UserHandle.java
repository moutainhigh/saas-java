package com.hq.storeManagerMS.service.muser.bs.updateHandle;

import org.apache.commons.lang3.StringUtils;

import com.hq.storeManagerMS.service.auth.muser.MUserAuthUtils;
import com.hq.storeManagerMS.service.auth.muser.MUserPwdHelper;
import com.hq.storeManagerMS.service.common.OperateTips;
import com.hq.storeManagerMS.service.muser.apiData.MUserChangePasswordApiData;
import com.hq.storeManagerMS.service.muser.apiData.MUserUpdateApiForm;
import com.hq.storeManagerMS.service.muser.apiData.MUserUpdateInfoApiData;
import com.hq.storeManagerMS.service.muser.apiData.MUserUpdateRoleSetApiData;
import com.hq.storeManagerMS.service.muser.apiData.MUserUpdateStatusApiData;
import com.hq.storeManagerMS.service.muser.apiData.MUserUpdateType;
import com.hq.storeManagerMS.service.muser.bs.MUserMgr;
import com.hq.storeManagerMS.service.muser.data.MUser;
import com.zenmind.common.hotSwap.HotSwap;

public class UserHandle {
	public static UserHandle getInstance() {
		return HotSwap.getInstance().getSingleton(UserHandle.class);
	}
	
	//修改用户基本信息
	public OperateTips updateInfo(MUserUpdateApiForm formInfo){
		MUserUpdateInfoApiData inputData= formInfo.getUpdateInfoData();
		OperateTips operateTips = OperateTips.newInstance(false, MUserUpdateType.updateInfo.getDescript()+"失败");

		MUser data = MUserMgr.getInstance().get(inputData.getMuserId());
		if(data != null){
			inputData.updateMUser(data);
			MUserMgr.getInstance().update(data);
			operateTips.setSuccess(true);
		}else{
			operateTips.setTips("用户不存在,更新信息失败");
		}
		return operateTips;
	}
	
	//给用户授权
	public OperateTips updRoleSet4Clerk(MUserUpdateApiForm formInfo) {
		MUserUpdateRoleSetApiData inputData = formInfo.getUpdateRoleSetApiData();
		OperateTips operateTips = OperateTips.newInstance(false, MUserUpdateType.updRoleSet4Clerk.getDescript()+"失败");
		
		MUser data = MUserMgr.getInstance().get(inputData.getMuserId());
		if(data != null){
			inputData.updateMUser(data);
			MUserMgr.getInstance().update(data);
			operateTips.setSuccess(true);
		}else{
			operateTips.setTips("用户不存在,授权失败");
		}
		return operateTips;
	}
	
	//更新用户状态
	public OperateTips updMUserStatus(MUserUpdateApiForm formInfo) {
		MUserUpdateStatusApiData inputData = formInfo.getUpdateStatusApiData();
		OperateTips operateTips = OperateTips.newInstance(false, MUserUpdateType.updMUserStatus.getDescript()+"失败");
		
		MUser data = MUserMgr.getInstance().get(inputData.getMuserId());
		if(data != null){
			inputData.updateMUser(data);
			MUserMgr.getInstance().update(data);
			operateTips.setSuccess(true);
		}else{
			operateTips.setTips("用户不存在,更新用户状态失败");
		}
		return operateTips;
	}
	
	//修改用户密码
	public OperateTips changePassword(MUserUpdateApiForm formInfo) {
		MUserChangePasswordApiData inputData = formInfo.getChangePasswordData();
		OperateTips operateTips = OperateTips.newInstance(false, MUserUpdateType.changePassword.getDescript()+"失败");
		
		long muserId = inputData.getMuserId();
		MUserAuthUtils.getInstance().checkMUser(muserId);
		MUser data = MUserMgr.getInstance().get(muserId);
		if(data == null){
			operateTips.setTips("用户不存在,更新用户密码失败");
			return operateTips;
		}
		if(checkOldPassword(data,inputData.getOldPassword())){
			String encryptPassword = MUserPwdHelper.getInstance().getEncryptPassword(data, inputData.getPassword());
			data.setPassword(encryptPassword);
			MUserMgr.getInstance().update(data);
			operateTips.setSuccess(true);
		}else{
			operateTips.setTips("旧密码错误,请重新输入");
		}
		return operateTips;
	}
	
	private boolean checkOldPassword(MUser muser, String oldPassword){
		String encryptPassword = MUserPwdHelper.getInstance().getEncryptPassword(muser, oldPassword);
		if(StringUtils.equals(encryptPassword, muser.getPassword())){
			return true;
		}else{
			return false;
		}
	}
}

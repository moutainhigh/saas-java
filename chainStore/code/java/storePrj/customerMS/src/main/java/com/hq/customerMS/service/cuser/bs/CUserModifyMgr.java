package com.hq.customerMS.service.cuser.bs;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import com.hq.customerMS.common.constants.ServerConstants;
import com.hq.customerMS.service.auth.cuser.CUserPwdHelper;
import com.hq.customerMS.service.cuser.apiData.CUserAddApiForm;
import com.hq.customerMS.service.cuser.apiData.CUserAddByWxForm;
import com.hq.customerMS.service.cuser.apiData.CUserChangePasswordApiData;
import com.hq.customerMS.service.cuser.apiData.CUserResetPasswordForm;
import com.hq.customerMS.service.cuser.apiData.CUserUpdateInfoApiData;
import com.hq.customerMS.service.cuser.apiData.CuserAdd4Ms;
import com.hq.customerMS.service.cuser.apiData.CuserUpdate4Ms;
import com.hq.customerMS.service.cuser.data.CUser;
import com.zenmind.common.hotSwap.HotSwap;

public class CUserModifyMgr {

	public static CUserModifyMgr getInstance(){
		return HotSwap.getInstance().getSingleton(CUserModifyMgr.class);
	}

	public void update(CUser cuser) {
		CUserDataHolder.getInstance().updpate(cuser);
	}
	
	public CUser updateInfo(long cuserId, CUserUpdateInfoApiData updateInfoData) {
		CUser cuser = CUserQueryMgr.getInstance().get(cuserId);
		updateInfoData.update(cuser);
		update(cuser);
		return cuser;
	}
	
	public CUser regWithWxInfo(CUserAddByWxForm formInfo) {
		CUser user = formInfo.toUser();
		CUserDataHolder.getInstance().addAndReturnId(user);
		//生成主账号(10位)与随机密码(6位)
		user.setPriAccountNum(StringUtils.leftPad(user.getId()+"", 10, "0"));
		String password = RandomStringUtils.random(6, ServerConstants.NUMBER_RANDOM);
		user.setPassword(password);
		// 加密密码
		CUserPwdHelper.getInstance().encryptUser(user);
		update(user);
		return user;
	}
	
	public CUser updateFromMs(CuserUpdate4Ms inputForm) {
		CUser cuser = CUserQueryMgr.getInstance().get(inputForm.getId());
		inputForm.update(cuser);
		update(cuser);
		return cuser;
	}
	
	public CUser addFromMs(CuserAdd4Ms addForm) {
		long phone = addForm.getPhone();
		CUser cuser = CUserQueryMgr.getInstance().findByPhone(phone);
		if(cuser == null){
			CUserAddApiForm formInfo = CUserAddApiForm.newInstance();
			formInfo.setPhone(phone);
			String password = RandomStringUtils.random(6, ServerConstants.NUMBER_RANDOM);
			formInfo.setPassword(password);
			cuser = formInfo.toUser();
			createUser(cuser);
		}
		return cuser;
	}
	
	public CUser createUser(CUser cuser) {
		CUserDataHolder.getInstance().addAndReturnId(cuser);
		//生成主账号(10位)与随机密码(6位)
		cuser.setPriAccountNum(StringUtils.leftPad(cuser.getId()+"", 10, "0"));
		// 加密密码
		CUserPwdHelper.getInstance().encryptUser(cuser);
		update(cuser);
		return cuser;
	}

	public CUser changePassword(long cuserId, CUserChangePasswordApiData changePasswordData) {
		String newPassword = changePasswordData.getPassword();
		CUser cuser = CUserQueryMgr.getInstance().get(cuserId);
		String encryptPassword = CUserPwdHelper.getInstance().getEncryptPassword(cuser, newPassword);
		cuser.setPassword(encryptPassword);
		update(cuser);
		return cuser;
	}
	
	public CUser resetPassword(CUserResetPasswordForm resetPasswordData) {
		long phone =  resetPasswordData.getPhone();
		String newPassword = resetPasswordData.getPassword();
		CUser cuser = CUserDataHolder.getInstance().findByPhone(phone);
		String encryptPassword = CUserPwdHelper.getInstance().getEncryptPassword(cuser, newPassword);
		cuser.setPassword(encryptPassword);
		update(cuser);
		return cuser;
	}
}

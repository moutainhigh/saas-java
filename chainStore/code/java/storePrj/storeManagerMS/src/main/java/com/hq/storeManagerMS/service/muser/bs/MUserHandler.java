package com.hq.storeManagerMS.service.muser.bs;

import com.hq.storeManagerMS.common.log.LogModule;
import com.hq.storeManagerMS.common.log.MainLog;
import com.hq.storeManagerMS.service.auth.muser.MUserAuthUtils;
import com.hq.storeManagerMS.service.auth.muser.MUserPwdHelper;
import com.hq.storeManagerMS.service.common.ExceptionInfo;
import com.hq.storeManagerMS.service.common.HandlerHelper;
import com.hq.storeManagerMS.service.common.OperateTips;
import com.hq.storeManagerMS.service.common.ReqResult;
import com.hq.storeManagerMS.service.common.RespStatus;
import com.hq.storeManagerMS.service.muser.apiData.LoginResp;
import com.hq.storeManagerMS.service.muser.apiData.MUserAddApiForm;
import com.hq.storeManagerMS.service.muser.apiData.MUserLoginApiForm;
import com.hq.storeManagerMS.service.muser.apiData.MUserUpdateApiForm;
import com.hq.storeManagerMS.service.muser.bs.updateHandle.MUserHandleHelper;
import com.hq.storeManagerMS.service.muser.data.MUser;
import com.hq.storeManagerMS.service.muser.data.MUserRO;
import com.zenmind.common.hotSwap.HotSwap;

public class MUserHandler {

	public static MUserHandler getInstance() {
		return HotSwap.getInstance().getSingleton(MUserHandler.class);
	}
	
	private final LogModule logModule = LogModule.MUser;
	private final String reason = "Exception happens.";
	
	public ReqResult<LoginResp> login(MUserLoginApiForm loginForm) {
		ReqResult<LoginResp> result = ReqResult.newInstance(false, LoginResp.class);
		try {
			String account = loginForm.getAccount();
			MUserRO muser = MUserMgr.getInstance().findByAccount(account);
			if(muser == null){
				result.setSuccess(false);
				result.setStatus(RespStatus.NOT_FOUND);
				result.setTips("用户不存在");
				return result;
			}
			String password = loginForm.getPassword();
			String encryptPassword = MUserPwdHelper.getInstance().getEncryptPassword(muser, password);
			
			String accessToken = MUserAuthUtils.getInstance().login(account, encryptPassword);
			if(accessToken != null){
				LoginResp resp = LoginResp.newInstance(muser, accessToken);
				result.setTarget(resp);
				result.setSuccess(true);
			}else{
				result.setSuccess(false);
				result.setStatus(RespStatus.NOT_FOUND);
				result.setTips("账号或密码错误");
			}
		} catch (Exception e) {
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(LogModule.MUser, "MUserHandler[login]", "", e);
		}
		return result;
	}
	
	public ReqResult<MUserRO> addMUser(MUserAddApiForm addForm) {
		ReqResult<MUserRO> result = ReqResult.newInstance(false, MUserRO.class);
		try {
			MUserRO tmpUser = MUserMgr.getInstance().findByAccount(addForm.getAccount());
			if(tmpUser!=null){
				result.setStatus(RespStatus.NOT_FOUND);
				result.setTips("账号已存在");
				return result;
			}
			MUser muser = addForm.toMUser();
//			MUserMgr.getInstance().addAndReturnId(muser);
			MUserMgr.getInstance().createUser(muser);
			result.setTarget(muser);
			result.setSuccess(true);
		} catch (Exception e) {
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(LogModule.MUser, "MUserHandler[addMUser]", "", e);
		}
		return result;
	}
	
	public ReqResult<MUserRO> update(MUserUpdateApiForm formInfo) {
		ReqResult<MUserRO> result = ReqResult.newInstance(false, MUserRO.class);
		try {
			OperateTips operateTips = MUserHandleHelper.getInstance().update(formInfo);
			if (operateTips.isSuccess()) {
				result.setSuccess(true);
			} else { 
				HandlerHelper.getInstance().handleReqResult(result, operateTips);
			}
		} catch (Exception e) {
			final String logId = "MUserAdminRoleHandler[update]";
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance()
					.withLogModule(logModule).withLogId(logId)
					.withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}

		return result;
	}
	
	public ReqResult<MUserRO> getMUser(long id) {
		ReqResult<MUserRO> result = ReqResult.newInstance(false, MUserRO.class);
		try {
			MUserRO muser = MUserMgr.getInstance().get(id);
			if(muser!=null){
				result.setTarget(muser);
				result.setSuccess(true);
			}else{
				result.setTips("用户不存在");
				result.setStatus(RespStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(LogModule.MUser, "MUserHandler[getMUser]", "", e);
		}
		return result;
	}
	
	public ReqResult<MUserRO> findByAccount(String account) {
		ReqResult<MUserRO> result = ReqResult.newInstance(false, MUserRO.class);
		try {
			MUserRO muser = MUserMgr.getInstance().findByAccount(account);
			if(muser!=null){
				result.setTarget(muser);
				result.setSuccess(true);
			}else{
				result.setTips("用户不存在");
				result.setStatus(RespStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(LogModule.MUser, "MUserHandler[findByAccount]", "", e);
		}
		return result;
	}
}

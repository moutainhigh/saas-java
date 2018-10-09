package com.hq.storeMS.service.opuser.bs;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.service.auth.opUser.OPUserAuthUtils;
import com.hq.storeMS.service.auth.opUser.OPUserPwdHelper;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RespStatus;
import com.hq.storeMS.service.opuser.apiData.OPLoginResp;
import com.hq.storeMS.service.opuser.apiData.OPUserAddApiForm;
import com.hq.storeMS.service.opuser.apiData.OPUserLoginApiForm;
import com.hq.storeMS.service.opuser.apiData.OPUserUpdateApiForm;
import com.hq.storeMS.service.opuser.apiData.OPUserUpdateType;
import com.hq.storeMS.service.opuser.apiData.OPuserQueryApiForm;
import com.hq.storeMS.service.opuser.data.OPUser;
import com.hq.storeMS.service.opuser.data.adminRole.OPAdminPermEnum;
import com.zenmind.common.hotSwap.HotSwap;

public class OPUserHandler {

	public static OPUserHandler getInstance() {
		return HotSwap.getInstance().getSingleton(OPUserHandler.class);
	}
	
	public ReqResult<OPLoginResp> login(OPUserLoginApiForm loginForm) {
		ReqResult<OPLoginResp> result = ReqResult.newInstance(false, OPLoginResp.class);
		try {
			String name = loginForm.getName();
			String password = loginForm.getPassword();
			OPUser opuser = OPUserMgr.getInstance().findByName(name);
			if(opuser == null){
				result.setSuccess(false);
				result.setStatus(RespStatus.NOT_FOUND);
				result.setTips("用户不存在");
				return result;
			}
			String encryptPassword = OPUserPwdHelper.getInstance().getEncryptPassword(opuser, password);
			
			String accessToken = OPUserAuthUtils.getInstance().login(name, encryptPassword);
			if(accessToken != null){
				OPLoginResp resp = OPLoginResp.newInstance(opuser, accessToken);
				result.setTarget(resp);
				result.setSuccess(true);
				result.setStatus(RespStatus.OK);
			}else{
				result.setSuccess(false);
				result.setStatus(RespStatus.NOT_FOUND);
				result.setTips("账号或密码错误");
			}
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(loginForm);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(LogModule.OPUser, "OPUserHandler[login]", reason, e);
		}
		return result;
	}
	
	
	public ReqResult<OPUser> get(long id) {
		ReqResult<OPUser> result = ReqResult.newInstance(false, OPUser.class);
		try {
			OPUser opuser = OPUserMgr.getInstance().get(id);
			if(opuser!=null){
				result.setTarget(opuser);
				result.setSuccess(true);
				result.setStatus(RespStatus.OK);
			}else{
				result.setStatus(RespStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(id);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(LogModule.OPUser, "OPUserHandler[get]", reason, e);
		}

		return result;
	}

	public ReqResult<OPUser> add(OPUserAddApiForm formInfo) {
		ReqResult<OPUser> result = ReqResult.newInstance(false, OPUser.class);
		try {
			if(StringUtils.isNotBlank(formInfo.getName())){
				result.setSuccess(false);
				result.setStatus(RespStatus.INVALID_REQUEST);
				result.setTips("用户名不能为空");
			}else if(!checkOPuserName(formInfo.getName())){
				result.setSuccess(false);
				result.setStatus(RespStatus.INVALID_REQUEST);
				result.setTips("用户已注册");
			}else{
				//验证权限
//				OPUserAuthUtils.getInstance().checkPermission(OPAdminPermEnum.OP_SUPER);
				OPUser opuser = formInfo.toOPUser();
				OPUserMgr.getInstance().createUser(opuser);
				
				result.setTarget(opuser);
				result.setSuccess(true);
				result.setStatus(RespStatus.OK);
			}
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(formInfo);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(LogModule.OPUser, "OPUserHandler[add]", reason, e);
		}

		return result;
	}

	
	public ReqResult<OPUser> update(OPUserUpdateApiForm formInfo) {
		ReqResult<OPUser> result = ReqResult.newInstance(false, OPUser.class);
		try {
			OPUserUpdateType updateType = OPUserUpdateType.valueOf(formInfo.getUpdateType());
			//验证权限
			OPUserAuthUtils.getInstance().checkPermission(OPAdminPermEnum.OP_SUPER);
			boolean success = false;
			switch (updateType) {
				case updateInfo:
					success = OPUserMgr.getInstance().updateInfo(formInfo.getUpdateInfoData());
					break;
				case updateRole:
					success = OPUserMgr.getInstance().updateRole(formInfo.getUpdateRoleData());
					break;
				case changePassword:
					success = OPUserMgr.getInstance().changePassword(formInfo.getChangePasswordData());
					break;
				default:
					break;
			}
			if (success) {
				result.setSuccess(true);
				result.setStatus(RespStatus.OK);
			} else {
				result.setTips("用户信息操作失败");
				result.setStatus(RespStatus.INVALID_REQUEST);
			}
			
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(formInfo);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(LogModule.OPUser, "OPUserHandler[update]", reason, e);
		}

		return result;
	}
	
	public ReqResult<OPUser> findByName(String name) {
		ReqResult<OPUser> result = ReqResult.newInstance(false, OPUser.class);
		try {
			OPUser findByName = OPUserMgr.getInstance().findByName(name);
			result.setTarget(findByName);
			result.setSuccess(true);
			result.setStatus(RespStatus.OK);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(name);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(LogModule.OPUser, "OPUserHandler[findByName]", reason, e);
		}

		return result;
	}
	
	public ReqResult<OPUser> findByPhone(long phone) {
		ReqResult<OPUser> result = ReqResult.newInstance(false, OPUser.class);
		try {
			OPUser opuserRO = OPUserMgr.getInstance().findByPhone(phone);
			if(opuserRO != null){
				result.setTarget(opuserRO);
				result.setSuccess(true);
				result.setStatus(RespStatus.OK);
			}else{
				result.setTips("用户不存在");
				result.setStatus(RespStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(phone);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(LogModule.OPUser, "OPUserHandler[findByPhone]", reason, e);
		}

		return result;
	}
	
	public ReqResult<OPUser> findOPuserList(OPuserQueryApiForm queryForm) {
		ReqResult<OPUser> result = ReqResult.newInstance(false, OPUser.class);
		try {
			List<OPUser> targetList = OPUserMgr.getInstance().findOPuserList(queryForm);
			result.setTargetList(targetList);
			result.setSuccess(true);
			result.setStatus(RespStatus.OK);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(queryForm);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(LogModule.OPUser, "OPUserHandler[findOPuserList]", reason, e);
		}

		return result;
	}
	
	//验证用户名账号是否已注册
	private boolean checkOPuserName(String name){
		boolean success = true;
		OPUser findByName = OPUserMgr.getInstance().findByName(name);
		if(findByName != null){
			success = false;
		}
		return success;
	}
	
}

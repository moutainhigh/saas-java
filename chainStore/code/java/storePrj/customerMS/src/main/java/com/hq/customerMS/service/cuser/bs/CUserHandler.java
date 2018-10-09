package com.hq.customerMS.service.cuser.bs;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.hq.customerMS.common.config.CustomerMSCfgMgr;
import com.hq.customerMS.common.log.LogHelper;
import com.hq.customerMS.common.log.LogModule;
import com.hq.customerMS.service.auth.cuser.CUserAuthUtils;
import com.hq.customerMS.service.auth.cuser.CUserPwdHelper;
import com.hq.customerMS.service.common.ExceptionInfo;
import com.hq.customerMS.service.common.GenderEnum;
import com.hq.customerMS.service.common.HandlerHelper;
import com.hq.customerMS.service.common.OperateTips;
import com.hq.customerMS.service.common.ReqResult;
import com.hq.customerMS.service.common.RespStatus;
import com.hq.customerMS.service.cuser.apiData.CUserAddApiForm;
import com.hq.customerMS.service.cuser.apiData.CUserAddByWxForm;
import com.hq.customerMS.service.cuser.apiData.CUserLoginApiForm;
import com.hq.customerMS.service.cuser.apiData.CUserLoginByCodeApiForm;
import com.hq.customerMS.service.cuser.apiData.CUserLoginWithJscodeForm;
import com.hq.customerMS.service.cuser.apiData.CUserLoginWithWxInfoForm;
import com.hq.customerMS.service.cuser.apiData.CUserResetPasswordForm;
import com.hq.customerMS.service.cuser.apiData.CUserUpdateApiForm;
import com.hq.customerMS.service.cuser.apiData.CUserUpdateType;
import com.hq.customerMS.service.cuser.apiData.CuserAdd4Ms;
import com.hq.customerMS.service.cuser.apiData.CuserUpdate4Ms;
import com.hq.customerMS.service.cuser.apiData.LoginCodeEnum;
import com.hq.customerMS.service.cuser.apiData.LoginResp;
import com.hq.customerMS.service.cuser.apiData.WxLoginResp;
import com.hq.customerMS.service.cuser.data.CUser;
import com.hq.customerMS.service.sms.bs.SmsMgr;
import com.hq.customerMS.service.sms.data.Sms;
import com.hq.customerMS.service.sms.data.SmsUseEnum;
import com.hq.customerMS.service.wxJscode.bs.WxJscodeMgr;
import com.hq.storeClient.service.wxJscode.apiData.WxJsCodeForm;
import com.hq.storeClient.service.wxJscode.data.UserInfo;
import com.hq.storeClient.service.wxJscode.data.WxJscode;
import com.zenmind.common.hotSwap.HotSwap;

public class CUserHandler {

	public static CUserHandler getInstance() {
		return HotSwap.getInstance().getSingleton(CUserHandler.class);
	}
	
	private final LogModule logModule = LogModule.CUser;

	public ReqResult<LoginResp> login(CUserLoginApiForm loginForm, HttpServletResponse response) {
		ReqResult<LoginResp> result = ReqResult.newInstance(false, LoginResp.class);
		try {
			long phone = loginForm.getPhone();
			CUser cuser = CUserQueryMgr.getInstance().findByPhone(phone);
			if (cuser == null) {
				result.setSuccess(false);
				result.setStatus(RespStatus.NOT_FOUND);
				result.setTips("用户不存在。");
				return result;
			}
			//对登录的明文密码进行加密 
			String password = loginForm.getPassword();
			String encryptPassword = CUserPwdHelper.getInstance().getEncryptPassword(cuser, password);
			String accessToken = CUserAuthUtils.getInstance().loginCuser(cuser, encryptPassword);
			if (accessToken != null) {
				LoginResp resp = LoginResp.newInstance(cuser, accessToken);
				result.setTarget(resp);
				result.setSuccess(true);
			} else {
				result.setSuccess(false);
				result.setStatus(RespStatus.NOT_FOUND);
				result.setTips("账号或密码错误。");
			}
		} catch (Exception e) {
			final String logId = "CUserHandler[login]";
			final String reason = LogHelper.getInstance().exceptionReason(loginForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<LoginResp> loginByCode(CUserLoginByCodeApiForm loginForm, HttpServletResponse response) {
		ReqResult<LoginResp> result = ReqResult.newInstance(false, LoginResp.class);
		try {
			long phone = loginForm.getPhone();
			String verifyCode = loginForm.getVerifyCode();
			//判定验证码的有效性
			OperateTips operateTips = checkSmsCode(phone, verifyCode);
			if(!operateTips.isSuccess()){
				HandlerHelper.getInstance().handleReqResult(result, operateTips);
				return result;
			}
			CuserAdd4Ms addForm = CuserAdd4Ms.newInstance();
			addForm.setPhone(phone);
			CUser cuser = CUserModifyMgr.getInstance().addFromMs(addForm);
			//直接用库里的密码登录
			String accessToken = CUserAuthUtils.getInstance().loginCuser(cuser, cuser.getPassword());
			if (accessToken != null) {
				LoginResp resp = LoginResp.newInstance(cuser, accessToken);
				result.setTarget(resp);
				result.setSuccess(true);
			} else {
				result.setSuccess(false);
				result.setStatus(RespStatus.NOT_FOUND);
				result.setTips("登录失败");
			}
		} catch (Exception e) {
			final String logId = "CUserHandler[loginByCode]";
			final String reason = LogHelper.getInstance().exceptionReason(loginForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<WxLoginResp> loginWithJscode(CUserLoginWithJscodeForm loginForm) {
		ReqResult<WxLoginResp> result = ReqResult.newInstance(false, WxLoginResp.class);
		try {
			String jscode = loginForm.getJscode();
			if(StringUtils.isBlank(jscode)){
				result.setSuccess(true);
				result.setTarget(WxLoginResp.newInstance(LoginCodeEnum.JsCodeEmpty));
				return result;
			}
			WxJsCodeForm inputForm = WxJsCodeForm.newInstance();
			inputForm.setJscode(jscode);
			inputForm.setAppId(CustomerMSCfgMgr.getProp().getWxMarketingAppId());
			WxJscode wxJscode = WxJscodeMgr.getInstance().jsCode2Session(inputForm);
			if(StringUtils.isBlank(wxJscode.getUnionId())) {
				result.setSuccess(true);
				result.setTarget(WxLoginResp.newInstance(LoginCodeEnum.UnionIdEmpty));
				return result;
			}
			CUser cuser = CUserQueryMgr.getInstance().findByWxUnionId(wxJscode.getUnionId());
			//已获取unionId 但cuser不存在，则添加cuser信息
			if(cuser == null){
				CUserAddByWxForm formInfo = CUserAddByWxForm.newInstance();
				formInfo.setAge(18);
				formInfo.setGender(GenderEnum.Women.ordinal());
				formInfo.setWxUnionId(wxJscode.getUnionId());
				formInfo.setName("未填写");
				formInfo.setHeadImg("");
				cuser = CUserModifyMgr.getInstance().regWithWxInfo(formInfo);
			}
			//直接用库里的密码登录
			String accessToken = CUserAuthUtils.getInstance().loginCuser(cuser, cuser.getPassword());
			if (accessToken != null) {
				result.setTarget(WxLoginResp.newInstance(cuser, accessToken));
				result.setSuccess(true);
			} else {
				result.setStatus(RespStatus.NOT_FOUND);
				result.setTips("登录失败");
			}
		} catch (Exception e) {
			final String logId = "CUserHandler[loginWithJscode]";
			final String reason = LogHelper.getInstance().exceptionReason(loginForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<WxLoginResp> loginByWxInfo(CUserLoginWithWxInfoForm loginForm) {
		ReqResult<WxLoginResp> result = ReqResult.newInstance(false, WxLoginResp.class);
		try {
			UserInfo userInfo = WxJscodeMgr.getInstance().decryptWXAppletInfo(loginForm.toDecryptWxAppletForm());
			if(userInfo == null){
				result.setSuccess(true);
				result.setTarget(WxLoginResp.newInstance(LoginCodeEnum.DecryptWXAppletInfo));
				return result;
			}
			if(StringUtils.isBlank(userInfo.getUnionId())) {
				result.setSuccess(true);
				result.setTarget(WxLoginResp.newInstance(LoginCodeEnum.UnionIdEmpty));
				return result;
			}
			CUser cuser = CUserQueryMgr.getInstance().findByWxUnionId(userInfo.getUnionId());
			//已获取unionId 但cuser不存在，则添加cuser信息
			if(cuser == null){
				CUserAddByWxForm formInfo = CUserAddByWxForm.newInstance();
				formInfo.setAge(18);
				formInfo.setGender(GenderEnum.Women.ordinal());
				formInfo.setWxUnionId(userInfo.getUnionId());
				formInfo.setName(userInfo.getNickName());
				formInfo.setHeadImg("");
				cuser = CUserModifyMgr.getInstance().regWithWxInfo(formInfo);
			}
			//直接用库里的密码登录
			String accessToken = CUserAuthUtils.getInstance().loginCuser(cuser, cuser.getPassword());
			if (accessToken != null) {
				result.setTarget(WxLoginResp.newInstance(cuser, accessToken));
				result.setSuccess(true);
			} else {
				result.setSuccess(false);
				result.setStatus(RespStatus.NOT_FOUND);
				result.setTips("登录失败");
			}
		} catch (Exception e) {
			final String logId = "CUserHandler[loginByWxInfo]";
			final String reason = LogHelper.getInstance().exceptionReason(loginForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}

	public ReqResult<CUser> get(long id) {
		ReqResult<CUser> result = ReqResult.newInstance(false, CUser.class);
		try {
			CUser cuser = CUserQueryMgr.getInstance().get(id);
			if (cuser != null) {
				result.setTarget(cuser);
				result.setSuccess(true);
			} else {
				result.setTips("用户不存在");
				result.setStatus(RespStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			final String logId = "CUserHandler[get]";
			final String reason = LogHelper.getInstance().exceptionReason(id);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}

	public ReqResult<CUser> findByPhone(long phone) {
		ReqResult<CUser> result = ReqResult.newInstance(false, CUser.class);
		try {
			CUser cuser = CUserQueryMgr.getInstance().findByPhone(phone);
			if (cuser != null) {
				result.setTarget(cuser);
				result.setSuccess(true);
			} else {
				result.setTips("用户不存在");
				result.setStatus(RespStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			final String logId = "CUserHandler[findByPhone]";
			final String reason = LogHelper.getInstance().exceptionReason(phone);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}

	public ReqResult<CUser> add(CUserAddApiForm formInfo) {
		ReqResult<CUser> result = ReqResult.newInstance(false, CUser.class);
		try {
			long phone = formInfo.getPhone();
			String verifyCode = formInfo.getVerifyCode();
			
			//验证顺序：短信有效性、手机号码
			OperateTips operateTips = checkSmsCode(phone, verifyCode);
			
			if(!operateTips.isSuccess()){
				HandlerHelper.getInstance().handleReqResult(result, operateTips);
				return result;
			}
			
			operateTips = checkUserPhone(phone);
			if(!operateTips.isSuccess()){
				HandlerHelper.getInstance().handleReqResult(result, operateTips);
				return result;
			}
			
			CUser cuser = formInfo.toUser();
			CUserModifyMgr.getInstance().createUser(cuser);
			result.setTarget(cuser);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "CUserHandler[add]";
			final String reason = LogHelper.getInstance().exceptionReason(formInfo);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	//短信验证码
	private OperateTips checkSmsCode(long phone, String verifyCode) {
		String phoneStr = String.valueOf(phone);
		OperateTips tips = OperateTips.newInstance();
		if(CustomerMSCfgMgr.getProp().isSmsCodeOpen()){//开启短信验证
			if(verifyCode.length() < 4){
				tips.setSuccess(false);
				tips.setTips("验证码错误");
			}else{
				Sms sms = SmsMgr.getInstance().getSmsFromRedis(phoneStr);
				if(sms != null && StringUtils.equals(verifyCode, sms.getVerifyCode())){//短信验证成功
					sms.setUsingTime(System.currentTimeMillis());
					sms.setIsUse(SmsUseEnum.HAS_USE.ordinal());
					SmsMgr.getInstance().update(sms);
					SmsMgr.getInstance().removeSmsRedis(phoneStr);
				}else{
					tips.setSuccess(false);
					tips.setTips("短信验证失败");
				}
			}
		}
		return tips;
	}

	// 验证手机号码是否已注册
	private OperateTips checkUserPhone(long phone) {
		OperateTips tips = OperateTips.newInstance();
		//手机号码的有效性校验
		if(phone == 0L){
			tips.setSuccess(false);
			tips.setTips("无效的手机号");
		}else{
			CUser existPhone = CUserQueryMgr.getInstance().findByPhone(phone);
			if(existPhone != null){
				tips.setSuccess(false);
				tips.setTips("手机号已注册");
			}
		}
		return tips;
	}

	public ReqResult<CUser> update(long cuserId, CUserUpdateApiForm formInfo) {
		ReqResult<CUser> result = ReqResult.newInstance(false, CUser.class);
		try {
			CUserAuthUtils.getInstance().checkUser(cuserId);
			CUserUpdateType updateType = CUserUpdateType.valueOf(formInfo.getUpdateType());
			CUser cuser = null;
			switch (updateType) {
			case UpdateInfo:
				cuser = CUserModifyMgr.getInstance().updateInfo(cuserId, formInfo.getUpdateInfoData());
				break;
			case ChangePassword:
				if (checkPassword(cuserId, formInfo)) {
					cuser = CUserModifyMgr.getInstance().changePassword(cuserId, formInfo.getChangePasswordData());
				}
				break;
			case AddAddress:
				cuser = CUserAddressMgr.getInstance().addAddress(cuserId, formInfo.getAddressAddData());
				break;
			case UpdateAddress:
				cuser = CUserAddressMgr.getInstance().updateAddress(cuserId, formInfo.getAddressUpdateData());
				break;
			case RemoveAddress:
				cuser = CUserAddressMgr.getInstance().removeAddress(cuserId, formInfo.getAddressRemoveData());
				break;
			case ChangeDefaultAddress:
				cuser = CUserAddressMgr.getInstance().changeDefaultAddress(cuserId, formInfo.getChangeDefaultAddressData());
				break;
			default:
				break;
			}
			if (cuser != null) {
				result.setTarget(cuser);
				result.setSuccess(true);
			} else {
				result.setTips("用户信息操作失败。");
				result.setStatus(RespStatus.INVALID_REQUEST);
			}

		} catch (Exception e) {
			final String logId = "CUserHandler[update]";
			final String reason = LogHelper.getInstance().exceptionReason(formInfo);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}

	// 验证旧密码
	private boolean checkPassword(long userId, CUserUpdateApiForm formInfo) {
		boolean success = false;
		CUser cuserRO = CUserQueryMgr.getInstance().get(userId);
		String oldPassword = formInfo.getChangePasswordData().getOldPassword();
		String encryptPassword = CUserPwdHelper.getInstance().getEncryptPassword(cuserRO, oldPassword);
		if (encryptPassword.equals(cuserRO.getPassword())) {
			success = true;
		}
		return success;
	}

	public ReqResult<CUser> resetPassword(CUserResetPasswordForm formInfo) {
		ReqResult<CUser> result = ReqResult.newInstance(false, CUser.class);
		try {
			CUser cuser = CUserQueryMgr.getInstance().findByPhone(formInfo.getPhone());
			if(cuser == null){
				result.setSuccess(false);
				result.setStatus(RespStatus.NOT_FOUND);
				result.setTips("用户不存在");
				return result;
			}
			OperateTips operateTips = checkSmsCode(formInfo.getPhone(), formInfo.getVerifyCode());
			if(!operateTips.isSuccess()){
				HandlerHelper.getInstance().handleReqResult(result, operateTips);
				return result;
			}
			
			cuser = CUserModifyMgr.getInstance().resetPassword(formInfo);
			if (cuser != null) {
				result.setTarget(cuser);
				result.setSuccess(true);
			} else {
				result.setTips("用户重置密码失败。");
				result.setStatus(RespStatus.INVALID_REQUEST);
			}
		} catch (Exception e) {
			final String logId = "CUserHandler[resetPassword]";
			final String reason = LogHelper.getInstance().exceptionReason(formInfo);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<CUser> addFromMs(CuserAdd4Ms addForm) {
		ReqResult<CUser> result = ReqResult.newInstance(false, CUser.class);
		try {
			CUser target = CUserModifyMgr.getInstance().addFromMs(addForm);
			result.setTarget(target);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "CUserHandler[addFromMs]";
			final String reason = LogHelper.getInstance().exceptionReason(addForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<CUser> updateFromMs(CuserUpdate4Ms updateForm) {
		ReqResult<CUser> result = ReqResult.newInstance(false, CUser.class);
		try {
			CUser target = CUserModifyMgr.getInstance().updateFromMs(updateForm);
			result.setTarget(target);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "CUserHandler[updateFromMs]";
			final String reason = LogHelper.getInstance().exceptionReason(updateForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
}

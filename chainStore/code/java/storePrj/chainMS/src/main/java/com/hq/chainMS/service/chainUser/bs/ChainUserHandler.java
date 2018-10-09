package com.hq.chainMS.service.chainUser.bs;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;

import com.hq.chainMS.common.log.LogHelper;
import com.hq.chainMS.common.log.LogModule;
import com.hq.chainMS.common.log.MainLog;
import com.hq.chainMS.service.auth.chainUser.ChainUserAuthUtils;
import com.hq.chainMS.service.auth.chainUser.ChainUserPwdHelper;
import com.hq.chainMS.service.chainClerk.bs.ChainClerkMgr;
import com.hq.chainMS.service.chainClerk.data.ChainClerk;
import com.hq.chainMS.service.chainUser.apiData.ChainUserLoginForm;
import com.hq.chainMS.service.chainUser.apiData.ChainUserQueryForm;
import com.hq.chainMS.service.chainUser.apiData.ChainUserUpdateForm;
import com.hq.chainMS.service.chainUser.apiData.LoginResp;
import com.hq.chainMS.service.chainUser.apiData.RegistForm;
import com.hq.chainMS.service.chainUser.apiData.ResetPasswordForm;
import com.hq.chainMS.service.chainUser.bs.handler.ChainUserAddHandle;
import com.hq.chainMS.service.chainUser.bs.handler.ChainUserUpdateHandlerHelper;
import com.hq.chainMS.service.chainUser.data.ChainUser;
import com.hq.chainMS.service.chainUser.data.ChainUserDto;
import com.hq.chainMS.service.common.HandlerHelper;
import com.hq.chainMS.service.common.OperateTips;
import com.hq.chainMS.service.common.PageResp;
import com.hq.chainMS.service.common.ReqResult;
import com.hq.chainMS.service.common.RespStatus;
import com.hq.chainMS.service.sms.bs.handle.SmsHandleHelper;
import com.zenmind.common.hotSwap.HotSwap;

public class ChainUserHandler {

	public static ChainUserHandler getInstance() {
		return HotSwap.getInstance().getSingleton(ChainUserHandler.class);
	}
	
	public ReqResult<LoginResp> login(ChainUserLoginForm loginForm, HttpServletResponse response) {
		ReqResult<LoginResp> result = ReqResult.newInstance(false, LoginResp.class);
		try {
			long phone = loginForm.getPhone();
			ChainUser chainUser = ChainUserMgr.getInstance().findByPhone(phone);
			if(chainUser == null){
				result.setStatus(RespStatus.NOT_FOUND);
				result.setTips("用户不存在");
				return result;
			}
			String password = loginForm.getPassword();
			String encryptPassword = ChainUserPwdHelper.getInstance().getEncryptPassword(chainUser, password);
			
			String accessToken = ChainUserAuthUtils.getInstance().login(phone, encryptPassword);
			if(accessToken != null){
				LoginResp resp = LoginResp.newInstance(chainUser, accessToken);
				result.setTarget(resp);
				result.setSuccess(true);
			}else{
				result.setStatus(RespStatus.NOT_FOUND);
				result.setTips("账号或密码错误");
			}
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(loginForm);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(LogModule.ChainUser, "ChainUserHandler[login]", reason, e);
		}
		return result;
	}
	
	public ReqResult<ChainUser> get(long id) {
		ReqResult<ChainUser> result = ReqResult.newInstance(false, ChainUser.class);
		try {
			ChainUser chainUser = ChainUserMgr.getInstance().get(id);
			checkUser4Result(chainUser, result);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(id);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(LogModule.ChainUser, "ChainUserHandler[get]", reason, e);
		}
		return result;
	}

	public ReqResult<ChainUser> findByPhone(long phone) {
		ReqResult<ChainUser> result = ReqResult.newInstance(false, ChainUser.class);
		try {
			ChainUser chainUser = ChainUserMgr.getInstance().findByPhone(phone);
			checkUser4Result(chainUser, result);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(phone);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(LogModule.ChainUser, "ChainUserHandler[findByPhone]", reason, e);
		}
		return result;
	}
	
	@SuppressWarnings("rawtypes")
	public ReqResult<PageResp> findByCond(ChainUserQueryForm queryForm) {
		ReqResult<PageResp> result = ReqResult.newInstance(false, PageResp.class);
		try {
			if(queryForm.getChainId() > 0 && CollectionUtils.isEmpty(queryForm.getChainUserIds())) {
				ChainClerk chainClerk = ChainClerkMgr.getInstance().get(queryForm.getChainId());
				queryForm.setChainUserIds(chainClerk.getClerkInfoMap().keySet());
			}
			PageResp<ChainUserDto> pageInfo = ChainUserMgr.getInstance().findByCond(queryForm);
			result.setTarget(pageInfo);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(queryForm);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(LogModule.ChainUser, "ChainUserHandler[findByCond]", reason, e);
		}
		return result;
	}
	
	public ReqResult<ChainUser> add(RegistForm formInfo) {
		ReqResult<ChainUser> result = ReqResult.newInstance(false, ChainUser.class);
		try {
			OperateTips operateTips = ChainUserAddHandle.getInstance().addChainUser(formInfo);
			if(operateTips.isSuccess()){
				result.setTarget(ChainUserMgr.getInstance().findByPhone(formInfo.getPhone()));
				result.setSuccess(true);
			}else{
				HandlerHelper.getInstance().handleReqResult(result, operateTips);
			}
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(formInfo);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(LogModule.ChainUser, "ChainUserHandler[add]", reason, e);
		}
		return result;
	}
	
	public ReqResult<ChainUser> resetPassword(ResetPasswordForm formInfo) {
		ReqResult<ChainUser> result = ReqResult.newInstance(false, ChainUser.class);
		try {
			ChainUser chainUser = ChainUserMgr.getInstance().findByPhone(formInfo.getPhone());
			checkUser4Result(chainUser, result);
			if(chainUser == null){
				return result;
			}
			
			OperateTips operateTips = SmsHandleHelper.getInstance().checkSmsCode(formInfo.getPhone(), formInfo.getVerifyCode());
			if(!operateTips.isSuccess()){
				HandlerHelper.getInstance().handleReqResult(result, operateTips);
				return result;
			}
			
			chainUser = ChainUserMgr.getInstance().resetPassword(formInfo);
			if (chainUser != null) {
				result.setTarget(chainUser);
				result.setSuccess(true);
			} else {
				result.setTips("密码修改失败");
				result.setStatus(RespStatus.INVALID_REQUEST);
			}
			
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(formInfo);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(LogModule.ChainUser, "ChainUserHandler[resetPassword]", reason, e);
		}
		return result;
	}
	
	public ReqResult<ChainUser> update(long chainUserId, ChainUserUpdateForm formInfo) {
		ReqResult<ChainUser> result = ReqResult.newInstance(false, ChainUser.class);
		try {
			OperateTips operateTips = ChainUserUpdateHandlerHelper.getInstance().update(formInfo);
			if (operateTips.isSuccess()) {
				result.setTarget(ChainUserMgr.getInstance().get(chainUserId));
				result.setSuccess(true);
			} else { 
				HandlerHelper.getInstance().handleReqResult(result, operateTips);
			}
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(chainUserId, formInfo);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(LogModule.ChainUser, "ChainUserHandler[update]", reason, e);
		}
		return result;
	}
	
	//检查用户是否存在
	private void checkUser4Result(ChainUser chainUser, ReqResult<ChainUser> result){
		if(chainUser!=null){
			result.setTarget(chainUser);
			result.setSuccess(true);
		}else{
			result.setTips("用户不存在");
			result.setStatus(RespStatus.NOT_FOUND);
		}
	}
	
}

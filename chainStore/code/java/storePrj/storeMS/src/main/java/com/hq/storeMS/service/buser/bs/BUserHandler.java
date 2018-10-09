package com.hq.storeMS.service.buser.bs;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.hq.storeMS.common.config.StoreMSCfgMgr;
import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.service.auth.buser.BUserAuthUtils;
import com.hq.storeMS.service.auth.buser.BUserPwdHelper;
import com.hq.storeMS.service.buser.apiData.BUserAddApiForm;
import com.hq.storeMS.service.buser.apiData.BUserCommQueryForm;
import com.hq.storeMS.service.buser.apiData.BUserLoginApiForm;
import com.hq.storeMS.service.buser.apiData.BUserLoginWithJsCodeForm;
import com.hq.storeMS.service.buser.apiData.BUserResetPasswordForm;
import com.hq.storeMS.service.buser.apiData.BUserUpdateApiForm;
import com.hq.storeMS.service.buser.apiData.LoginCodeEnum;
import com.hq.storeMS.service.buser.apiData.LoginResp;
import com.hq.storeMS.service.buser.apiData.QueryVipUserForm;
import com.hq.storeMS.service.buser.apiData.WxLoginResp;
import com.hq.storeMS.service.buser.bs.handler.BUserAddMgr;
import com.hq.storeMS.service.buser.bs.handler.BUserUpdateHandlerHelper;
import com.hq.storeMS.service.buser.data.BUser;
import com.hq.storeMS.service.buser.data.BuserRoleEnum;
import com.hq.storeMS.service.common.EvnMarkEnum;
import com.hq.storeMS.service.common.HandlerHelper;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.common.PageResp;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RespStatus;
import com.hq.storeMS.service.sms.bs.check.SmsCheckMgr;
import com.hq.storeMS.service.wxJscode.apiData.WxJsCodeForm;
import com.hq.storeMS.service.wxJscode.bs.WxJscodeMgr;
import com.hq.storeMS.service.wxJscode.data.WxJscode;
import com.zenmind.common.beanCopy.FastBeanCopyer;
import com.zenmind.common.hotSwap.HotSwap;

public class BUserHandler {

	public static BUserHandler getInstance() {
		return HotSwap.getInstance().getSingleton(BUserHandler.class);
	}
	
	@SuppressWarnings("rawtypes")
	public ReqResult<PageResp> findByCond(BUserCommQueryForm queryForm) {
		ReqResult<PageResp> result = ReqResult.newInstance(false, PageResp.class);
		try {
			PageResp<BUser> pageInfo = BUserQueryMgr.getInstance().findPageByCond(queryForm);
			result.setTarget(pageInfo);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(queryForm);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(LogModule.BUser, "BUserHandler[findByCond]", reason, e);
		}
		return result;
	}
	
	public ReqResult<LoginResp> login(BUserLoginApiForm loginForm, HttpServletResponse response) {
		ReqResult<LoginResp> result = ReqResult.newInstance(false, LoginResp.class);
		try {
			long phone = loginForm.getPhone();
			BUser buser = BUserQueryMgr.getInstance().findByPhone(phone);
			if(buser == null){
				result.setStatus(RespStatus.NOT_FOUND);
				result.setTips("用户不存在");
				return result;
			}
			String password = loginForm.getPassword();
			String encryptPassword = BUserPwdHelper.getInstance().getEncryptPassword(buser, password);
			
			String accessToken = BUserAuthUtils.getInstance().login(buser, encryptPassword);
			BUserModifyMgr.getInstance().checkExpiredTime((BUser)buser);
			if(accessToken != null){
				LoginResp resp = LoginResp.newInstance(buser, accessToken);
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
			MainLog.error(LogModule.BUser, "BUserHandler[login]", reason, e);
		}
		return result;
	}
	
	public ReqResult<WxLoginResp> loginWithJsCode(BUserLoginWithJsCodeForm loginForm) {
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
			inputForm.setAppId(StoreMSCfgMgr.getProp().getWxMarketingAppId());
			WxJscode wxJscode = WxJscodeMgr.getInstance().jsCode2Session(inputForm);
			if(StringUtils.isBlank(wxJscode.getUnionId())) {
				result.setSuccess(true);
				result.setTarget(WxLoginResp.newInstance(LoginCodeEnum.UnionIdEmpty));
				return result;
			}
			BUser buser = BUserQueryMgr.getInstance().findByWxUnionId(wxJscode.getUnionId());
			if(buser == null){
				result.setSuccess(true);
				result.setTarget(WxLoginResp.newInstance(LoginCodeEnum.UserNotExists));
				return result;
			}
			String encryptPassword = buser.getPassword();			
			String accessToken = BUserAuthUtils.getInstance().login(buser, encryptPassword);
			BUserModifyMgr.getInstance().checkExpiredTime((BUser)buser);
			if(accessToken != null){
				result.setSuccess(true);
				result.setTarget(WxLoginResp.newInstance(buser, accessToken));
			}else{
				result.setStatus(RespStatus.NOT_FOUND);
				result.setTips("微信登录失败");
			}
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(loginForm);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(LogModule.BUser, "BUserHandler[loginWithJsCode]", reason, e);
		}
		return result;
	}
	
	public ReqResult<LoginResp> loginWithTestPhone(BUserLoginApiForm loginForm, HttpServletResponse response) {
		ReqResult<LoginResp> result = ReqResult.newInstance(false, LoginResp.class);
		try {
			if(!EvnMarkEnum.Test.getMark().equals(StoreMSCfgMgr.getProp().getEnvMark())){//体验仿真环境
				result.setStatus(RespStatus.NOT_FOUND);
				result.setTips("服务暂不可用，请稍后尝试");
				return result;
			}
			
			long phone = loginForm.getPhone();
			long buserId = 0;
			BUser buserRO = BUserQueryMgr.getInstance().findByPhone(phone);
			if(buserRO == null){
				result.setStatus(RespStatus.NOT_FOUND);
				result.setTips("用户不存在");
				return result;
			}
			buserId = buserRO.getId();
			
			BUser buser = BUserQueryMgr.getInstance().get(buserId);
			String encryptPassword = buser.getPassword();
			
			String accessToken = BUserAuthUtils.getInstance().login(buserRO, encryptPassword);
			BUserModifyMgr.getInstance().checkExpiredTime((BUser)buser);
			if(accessToken != null){
				LoginResp resp = LoginResp.newInstance(buser, accessToken);
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
			MainLog.error(LogModule.BUser, "BUserHandler[loginWithTestPhone]", reason, e);
		}
		return result;
	}
	
	public ReqResult<BUser> get(long id) {
		ReqResult<BUser> result = ReqResult.newInstance(false, BUser.class);
		try {
			BUser buser = BUserQueryMgr.getInstance().getSimple(id);
			checkUser4Result(buser, result);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(id);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(LogModule.BUser, "BUserHandler[get]", reason, e);
		}
		return result;
	}

	public ReqResult<BUser> findByPhone(long phone) {
		ReqResult<BUser> result = ReqResult.newInstance(false, BUser.class);
		try {
			BUser buser = BUserQueryMgr.getInstance().findByPhone(phone);
			checkUser4Result(buser, result);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(phone);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(LogModule.BUser, "BUserHandler[findByPhone]", reason, e);
		}
		return result;
	}
	
	public ReqResult<BUser> findByMultitId(String idList, int pageItemCount, int pageNo) {
		ReqResult<BUser> result = ReqResult.newInstance(false, BUser.class);
		try {
			Set<Long> buserIdSet = getBUserIds(idList);
			BUserCommQueryForm queryForm = BUserCommQueryForm.newInstance();
			queryForm.setBuserIds(buserIdSet).setPageItemCount(pageItemCount).setPageNo(pageNo);
			List<BUser> targetList = BUserQueryMgr.getInstance().findBUserByCond(queryForm);
			result.setTargetList(targetList);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(idList, pageItemCount, pageNo);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(LogModule.BUser, "BUserHandler[findByMultitId]", reason, e);
		}
		return result;
	}
	
	//字符串转换成ID集合
	private Set<Long> getBUserIds(String idList){
		String[] arr = idList.split(",");
		Set<Long> buserIdSet = new HashSet<Long>();
		for (String id : arr) {
			if(StringUtils.isNotBlank(id)){
				buserIdSet.add(Long.valueOf(id));
			}
		}
		return buserIdSet;
	}

	public ReqResult<BUser> add(BUserAddApiForm formInfo) {
		ReqResult<BUser> result = ReqResult.newInstance(false, BUser.class);
		try {
			OperateTips operateTips = BUserAddMgr.getInstance().addBUser(formInfo);
			if(operateTips.isSuccess()){
				result.setTarget(BUserQueryMgr.getInstance().findByPhone(formInfo.getPhone()));
				result.setSuccess(true);
			}else{
				HandlerHelper.getInstance().handleReqResult(result, operateTips);
			}
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(formInfo);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(LogModule.BUser, "BUserHandler[add]", reason, e);
		}
		return result;
	}
	
	public ReqResult<BUser> resetPassword(BUserResetPasswordForm formInfo) {
		ReqResult<BUser> result = ReqResult.newInstance(false, BUser.class);
		try {
			BUser buser = BUserQueryMgr.getInstance().findByPhone(formInfo.getPhone());
			checkUser4Result(buser, result);
			if(buser == null){
				return result;
			}
			
			OperateTips operateTips = SmsCheckMgr.getInstance().checkSmsCode(formInfo.getPhone(), formInfo.getVerifyCode());
			if(!operateTips.isSuccess()){
				HandlerHelper.getInstance().handleReqResult(result, operateTips);
				return result;
			}
			
			buser = BUserModifyMgr.getInstance().resetPassword(formInfo);
			if (buser != null) {
				result.setTarget(buser);
				result.setSuccess(true);
			} else {
				result.setTips("密码修改失败");
				result.setStatus(RespStatus.INVALID_REQUEST);
			}
			
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(formInfo);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(LogModule.BUser, "BUserHandler[resetPassword]", reason, e);
		}
		return result;
	}
	
	/**
	 * 查询会员列表
	 * @param QueryVipUserForm
	 * @return
	 */
	public ReqResult<BUser> findVipUserList(QueryVipUserForm params) {
		ReqResult<BUser> result = ReqResult.newInstance(false, BUser.class);
		try {
			BUserCommQueryForm queryForm = BUserCommQueryForm.newInstance();
			FastBeanCopyer.getInstance().copy(params, queryForm);
			List<BUser> findVipList = BUserQueryMgr.getInstance().findBUserByCond(queryForm);
			result.setTargetList(findVipList);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(params);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(LogModule.BUser, "BUserHandler[findVipUserList]", reason, e);
		}
		return result;
	}
	
	/**
	 * 查询体验用户列表
	 * @param findDevUserList
	 * @return
	 */
	public ReqResult<BUser> findDevUserList(int pageItemCount, int pageNo) {
		ReqResult<BUser> result = ReqResult.newInstance(false, BUser.class);
		try {
			if(EvnMarkEnum.Test.getMark().equals(StoreMSCfgMgr.getProp().getEnvMark())){//体验仿真环境
				BUserCommQueryForm queryForm = BUserCommQueryForm.newInstance();
				queryForm.setPageNo(pageNo);
				queryForm.setPageItemCount(pageItemCount);
				queryForm.getRoleSet().add(BuserRoleEnum.BOSS.ordinal());
				List<BUser> list = BUserQueryMgr.getInstance().findBossList(queryForm);
				result.setTargetList(list);
				result.setSuccess(true);
			}else{
				result.setStatus(RespStatus.NOT_FOUND);
				result.setTips("服务暂不可用，请稍后尝试");
			}
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(pageItemCount, pageNo);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(LogModule.BUser, "BUserHandler[findDevUserList]", reason, e);
		}
		return result;
	}
	
	public ReqResult<BUser> update(long buserId, BUserUpdateApiForm formInfo) {
		ReqResult<BUser> result = ReqResult.newInstance(false, BUser.class);
		try {
			OperateTips operateTips = BUserUpdateHandlerHelper.getInstance().update(formInfo);
			if (operateTips.isSuccess()) {
				result.setTarget(BUserQueryMgr.getInstance().getSimple(buserId));
				result.setSuccess(true);
			} else { 
				HandlerHelper.getInstance().handleReqResult(result, operateTips);
			}
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(buserId, formInfo);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(LogModule.BUser, "BUserHandler[update]", reason, e);
		}
		return result;
	}
	
	//检查用户是否存在
	private void checkUser4Result(BUser buser, ReqResult<BUser> result){
		if(buser!=null){
			result.setTarget(buser);
			result.setSuccess(true);
		}else{
			result.setTips("用户不存在");
			result.setStatus(RespStatus.NOT_FOUND);
		}
	}
	
}

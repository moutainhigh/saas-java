package com.hq.storeMS.service.euser.bs;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.hq.storeMS.common.config.StoreMSCfgMgr;
import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.service.common.HandlerHelper;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RespStatus;
import com.hq.storeMS.service.euser.apiData.EUserAddForm;
import com.hq.storeMS.service.euser.apiData.EUserUpdateForm;
import com.hq.storeMS.service.euser.apiData.EUserUpdateType;
import com.hq.storeMS.service.euser.data.EUser;
import com.hq.storeMS.service.sms.bs.SmsMgr;
import com.hq.storeMS.service.sms.data.Sms;
import com.hq.storeMS.service.sms.data.SmsUseEnum;
import com.zenmind.common.hotSwap.HotSwap;

/** 
 * @ClassName: EUserHandler 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author helen 
 * @date 2018年1月19日 下午2:49:26 
 *  
 */
public class EUserHandler {
	
	public static EUserHandler getInstance() {
		return HotSwap.getInstance().getSingleton(EUserHandler.class);
	}

	public ReqResult<EUser> add(EUserAddForm addForm) {
		ReqResult<EUser> result = ReqResult.newInstance(false, EUser.class);
		try {
			long phone = addForm.getPhone();
			String verifyCode = addForm.getVerifyCode();
			
			//验证顺序：短信有效性、手机号码
			OperateTips operateTips = checkSmsCode(phone, verifyCode);
			if(!operateTips.isSuccess()){
				HandlerHelper.getInstance().handleReqResult(result, operateTips);
				return result;
			}
		
			operateTips = checkEUserPhone(phone);
			if(!operateTips.isSuccess()){
				HandlerHelper.getInstance().handleReqResult(result, operateTips);
				return result;
			}
			
			EUser euser = addForm.toEUser();
			EUserMgr.getInstance().createEUser(euser);
			result.setTarget(euser);
			result.setSuccess(true);
			result.setStatus(RespStatus.OK);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(addForm);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(LogModule.EUser, "EUserHandler[add]", reason, e);
		}
		return result;
	}
	
		//短信验证码
		private OperateTips checkSmsCode(long phone, String verifyCode) {
			String phoneStr = String.valueOf(phone);
			OperateTips tips = OperateTips.newInstance();
			if(StoreMSCfgMgr.getProp().isSmsCodeOpen()){//开启短信验证
				if(verifyCode.length() < 4){//短信最少4位数字
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
						tips.setTips("输入的验证码错误，请重新输入");
					}
				}
			}
			return tips;
		}
		
		//手机号码的有效性校验
		private OperateTips checkEUserPhone(long phone) {
			OperateTips tips = OperateTips.newInstance();
			//手机号码的有效性校验
			if(phone == 0L){
				tips.setSuccess(false);
				tips.setTips("无效的手机号");
			}
			return tips;
		}
		
		
		public ReqResult<EUser> update(EUserUpdateForm updateForm) {
			ReqResult<EUser> result = ReqResult.newInstance(false, EUser.class);
			try {
				EUserUpdateType updateType = EUserUpdateType.valueOf(updateForm.getUpdateType());
				EUser euser = null;
				switch (updateType) {
					case updateCount:
						euser = EUserMgr.getInstance().updateCount(updateForm.getUpdateCountData());
						break;
					default:
						break;
				}
				if (euser != null) {
					result.setTarget(euser);
					result.setSuccess(true);
					result.setStatus(RespStatus.OK);
				} else { 
					result.setTips(updateType.getDescript()+"失败");
					result.setStatus(RespStatus.INVALID_REQUEST);
				}
			} catch (Exception e) {
				final String reason = LogHelper.getInstance().exceptionReason(updateForm);
				result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
				result.setTips("服务暂不可用，请稍后尝试");
				MainLog.error(LogModule.EUser, "EUserHandler[update]", reason, e);
			}

			return result;
		}
		
		
		public ReqResult<EUser> get(long id) {
			ReqResult<EUser> result = ReqResult.newInstance(false, EUser.class);
			try {
				EUser euser = EUserMgr.getInstance().get(id);
				if(euser!=null){
					result.setTarget(euser);
					result.setSuccess(true);
					result.setStatus(RespStatus.OK);
				}else{
					result.setTips("体验用户不存在");
					result.setStatus(RespStatus.NOT_FOUND);
				}
			} catch (Exception e) {
				final String reason = LogHelper.getInstance().exceptionReason(id);
				result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
				result.setTips("服务暂不可用，请稍后尝试");
				MainLog.error(LogModule.EUser, "EUserHandler[add]", reason, e);
			}
			return result;
		}

		public ReqResult<EUser> findByPhone(long phone) {
			ReqResult<EUser> result = ReqResult.newInstance(false, EUser.class);
			try {
				EUser euser = EUserMgr.getInstance().findByPhone(phone);
				if(euser!=null){
					result.setTarget(euser);
					result.setSuccess(true);
					result.setStatus(RespStatus.OK);
				}else{
					result.setTips("体验用户不存在");
					result.setStatus(RespStatus.NOT_FOUND);
				}
			} catch (Exception e) {
				final String reason = LogHelper.getInstance().exceptionReason(phone);
				result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
				result.setTips("服务暂不可用，请稍后尝试");
				MainLog.error(LogModule.EUser, "EUserHandler[findByPhone]", reason, e);
			}
			return result;
		}
		
		
		public ReqResult<EUser> getList(int pageItemCount,int pageNo) {
			ReqResult<EUser> result = ReqResult.newInstance(false, EUser.class);
			try {
				List<EUser> euserList = EUserMgr.getInstance().getList(pageItemCount, pageNo);
				if(euserList.size()>0){
					result.setTargetList(euserList);
					result.setSuccess(true);
					result.setStatus(RespStatus.OK);
				}else{
					result.setTips("体验用户不存在");
					result.setStatus(RespStatus.NOT_FOUND);
				}
			} catch (Exception e) {
				final String reason = LogHelper.getInstance().exceptionReason(pageItemCount, pageNo);
				result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
				result.setTips("服务暂不可用，请稍后尝试");
				MainLog.error(LogModule.EUser, "EUserHandler[findByPhone]", reason, e);
			}
			return result;
		}
		
	
}

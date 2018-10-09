package com.hq.storeMS.service.appVersion.bs;

import java.util.List;

import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.service.appVersion.apiData.AddAppVersionForm;
import com.hq.storeMS.service.appVersion.apiData.QueryAppVersionForm;
import com.hq.storeMS.service.appVersion.apiData.UpdAppVersionForm;
import com.hq.storeMS.service.appVersion.apiData.UpdateAppVersionType;
import com.hq.storeMS.service.appVersion.data.AppVersion;
import com.hq.storeMS.service.common.ExceptionInfo;
import com.hq.storeMS.service.common.HandlerHelper;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RespStatus;
import com.zenmind.common.beanCopy.FastBeanCopyer;
import com.zenmind.common.hotSwap.HotSwap;

public class AppVersionHandler {

	public static AppVersionHandler getInstance() {
		return HotSwap.getInstance().getSingleton(AppVersionHandler.class);
	}
	
	private final LogModule logModule = LogModule.AppVersion;
	
	public ReqResult<AppVersion> findByCond(QueryAppVersionForm queryForm) {
		ReqResult<AppVersion> result = ReqResult.newInstance(false, AppVersion.class);
		try {
			List<AppVersion> appVersions = AppVersionMgr.getInstance().findByCond(queryForm);
			result.setTargetList(appVersions);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "AppVersionHandler[findByCond]";
			final String reason = LogHelper.getInstance().exceptionReason(queryForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<AppVersion> findByName(String appName) {
		ReqResult<AppVersion> result = ReqResult.newInstance(false, AppVersion.class);
		try {
			AppVersion appVersion = AppVersionMgr.getInstance().findOne(appName);
			result.setTarget(appVersion);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "AppVersionHandler[findByName]";
			final String reason = LogHelper.getInstance().exceptionReason(appName);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<AppVersion> getAppVersion(long appVersionId) {
		ReqResult<AppVersion> result = ReqResult.newInstance(false, AppVersion.class);
		try {
			AppVersion appVersion = AppVersionMgr.getInstance().get(appVersionId);
			result.setTarget(appVersion);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "AppVersionHandler[getAppVersion]";
			final String reason = LogHelper.getInstance().exceptionReason(appVersionId);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<AppVersion> updateAppVersion(long appVersionId, UpdAppVersionForm inputForm) {
		ReqResult<AppVersion> result = ReqResult.newInstance(false, AppVersion.class);
		try {
			UpdateAppVersionType updateType = UpdateAppVersionType.valueOf(inputForm.getUpdateType());
			AppVersion appVersion = AppVersionMgr.getInstance().get(appVersionId);
			boolean success = false;
			switch (updateType) {
			case UpdateAppVersionInfo:
				FastBeanCopyer.getInstance().copy(inputForm.getUpdAppVersionInfoForm(), appVersion);
				AppVersionMgr.getInstance().updateAppVersion(appVersion);
				success = true;
				break;
			case UpdateAppVersionStatus:
				appVersion.setStatus(inputForm.getUpdAppVersionStatusForm().getStatus());
				AppVersionMgr.getInstance().updateAppVersion(appVersion);
				success = true;
				break;
			default:
				break;
			}
			if(success){
				result.setTarget(appVersion);
				result.setSuccess(success);
			}else{
				result.setStatus(RespStatus.INVALID_REQUEST);
				result.setTips("更新失败");
			}
		} catch (Exception e) {
			final String logId = "AppVersionHandler[updateAppVersion]";
			final String reason = LogHelper.getInstance().exceptionReason(appVersionId, inputForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<AppVersion> addAppVersion(AddAppVersionForm inputForm) {
		ReqResult<AppVersion> result = ReqResult.newInstance(false, AppVersion.class);
		try {
			AppVersion target = AppVersion.newInstance();
			FastBeanCopyer.getInstance().copy(inputForm, target);
			AppVersionMgr.getInstance().addAndReturnId(target);
			result.setTarget(target);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "AppVersionHandler[addAppVersion]";
			final String reason = LogHelper.getInstance().exceptionReason(inputForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
}

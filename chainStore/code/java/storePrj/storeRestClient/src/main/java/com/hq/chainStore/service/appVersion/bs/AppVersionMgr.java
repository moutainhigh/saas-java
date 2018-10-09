package com.hq.chainStore.service.appVersion.bs;

import java.util.List;

import com.hq.chainStore.service.appVersion.apiData.AddAppVersionForm;
import com.hq.chainStore.service.appVersion.apiData.QueryAppVersionForm;
import com.hq.chainStore.service.appVersion.apiData.UpdAppVersionForm;
import com.hq.chainStore.service.appVersion.apiData.UpdAppVersionInfoForm;
import com.hq.chainStore.service.appVersion.apiData.UpdAppVersionStatusForm;
import com.hq.chainStore.service.appVersion.apiData.UpdateAppVersionType;
import com.hq.chainStore.service.appVersion.data.AppVersion;
import com.hq.chainStore.service.appVersion.data.AppVersionDAO;
import com.hq.chainStore.service.common.ClientConstants;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.ReqMap;

public class AppVersionMgr {

	public static AppVersionMgr getInstance(){
		return HotSwap.getInstance().getSingleton(AppVersionMgr.class);
	}
	
	public List<AppVersion> findByCond(QueryAppVersionForm queryForm) {
		final String findPath = "findByCond";
		ReqMap reqMap = ReqMap.newInstance();
		return AppVersionDAO.getInstance().findWithReqParam(findPath, reqMap, queryForm.getPageItemCount(), queryForm.getPageNo());
	}
	
	public AppVersion findByName(String appName) {
		final String findPath = "findByName";
		ReqMap reqMap = ReqMap.newInstance();
		reqMap.add("appName", appName);
		return AppVersionDAO.getInstance().findOneWithReqParam(findPath, reqMap);
	}
	
	public AppVersion findByNameUnAuth(String appName) {
		final String findPath = "findByName";
		ReqMap reqMap = ReqMap.newInstance();
		reqMap.add("appName", appName);
		return AppVersionDAO.getInstance().findByNameUnAuth(findPath, reqMap);
	}
	
	public AppVersion findZmtVersion() {
		final String findPath = "findByName";
		ReqMap reqMap = ReqMap.newInstance();
		reqMap.add("appName", ClientConstants.APP_NAME);
		return AppVersionDAO.getInstance().findOneWithReqParam(findPath, reqMap);
	}
	
	public AppVersion getAppVersion(long appVersionId) {
		return AppVersionDAO.getInstance().get(appVersionId);
	}
	
	public void updateAppVersion(long appVersionId, UpdAppVersionForm updAppVersionForm) {
		AppVersionDAO.getInstance().update(appVersionId, updAppVersionForm);
	}
	
	public void updAppVersionInfo(long appVersionId, UpdAppVersionInfoForm updAppVersionInfoForm) {
		UpdAppVersionForm updAppVersionForm = UpdAppVersionForm.newInstance();
		updAppVersionForm.setUpdateType(UpdateAppVersionType.UpdateAppVersionInfo.ordinal());
		updAppVersionForm.setUpdAppVersionInfoForm(updAppVersionInfoForm);
		AppVersionDAO.getInstance().update(appVersionId, updAppVersionForm);
	}
	
	public void updAppVersionStatus(long appVersionId, UpdAppVersionStatusForm updAppVersionStatusForm) {
		UpdAppVersionForm updAppVersionForm = UpdAppVersionForm.newInstance();
		updAppVersionForm.setUpdateType(UpdateAppVersionType.UpdateAppVersionStatus.ordinal());
		updAppVersionForm.setUpdAppVersionStatusForm(updAppVersionStatusForm);
		AppVersionDAO.getInstance().update(appVersionId, updAppVersionForm);
	}
	
	public AppVersion addAppVersion(AddAppVersionForm addAppVersionForm) {
		return AppVersionDAO.getInstance().add(addAppVersionForm);
	}
	
}

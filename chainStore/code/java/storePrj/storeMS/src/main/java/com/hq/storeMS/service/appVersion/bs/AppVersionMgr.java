package com.hq.storeMS.service.appVersion.bs;

import java.util.List;

import com.hq.storeMS.service.appVersion.apiData.QueryAppVersionForm;
import com.hq.storeMS.service.appVersion.data.AppVersion;
import com.zenmind.common.hotSwap.HotSwap;

public class AppVersionMgr {

	public static AppVersionMgr getInstance(){
		return HotSwap.getInstance().getSingleton(AppVersionMgr.class);
	}
	
	public void addAndReturnId(AppVersion target) {
		AppVersionDataHolder.getInstance().addAndReturnId(target);
	}
	
	public void updateAppVersion(AppVersion target) {
		AppVersionDataHolder.getInstance().update(target);
	}
	
	public AppVersion get(long id){
		return AppVersionDataHolder.getInstance().get(id);
	}
	
	public AppVersion findOne(String appName) {
		return AppVersionDataHolder.getInstance().findOne(appName);
	}
	
	public List<AppVersion> findByCond(QueryAppVersionForm queryForm) {
		return AppVersionDataHolder.getInstance().findByCond(queryForm);
	}

}

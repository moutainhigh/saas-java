package com.hq.storeMS.service.appVersion.bs;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.hq.storeMS.service.appVersion.apiData.QueryAppVersionForm;
import com.hq.storeMS.service.appVersion.data.AppVersion;
import com.hq.storeMS.service.appVersion.data.AppVersionCacheDAO;
import com.hq.storeMS.service.appVersion.data.AppVersionDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class AppVersionDataHolder{
	
	public static AppVersionDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(AppVersionDataHolder.class);
	}
	
	public void addAndReturnId(AppVersion target) {
		AppVersionDAO.getInstance().addAndReturnId(target);
		AppVersionCacheDAO.getInstance().delete(target);
	}

	public void update(AppVersion target) {
		target.incrVer();
		target.setLastUpdateTime(System.currentTimeMillis());
		AppVersionDAO.getInstance().updpate(target);
		AppVersionCacheDAO.getInstance().delete(target);
	}
	
	public void delete(AppVersion target) {
		AppVersionDAO.getInstance().delete(target.getId());
		AppVersionCacheDAO.getInstance().delete(target);
	}
	
	public AppVersion get(long id) {
		AppVersion target = AppVersionCacheDAO.getInstance().get(id);
		if(target == null){
			target = AppVersionDAO.getInstance().get(id);
			if(target != null){
				AppVersionCacheDAO.getInstance().save(target);
			}
		}
		return target;
	}
	
	public AppVersion findOne(String appName) {
		AppVersion target = AppVersionCacheDAO.getInstance().getOne(appName);
		if(target == null) {
			target = AppVersionDAO.getInstance().findByOne(appName);
			if(target != null){
				AppVersionCacheDAO.getInstance().saveOne(appName, target);
			}
		}
		return target;
	}
	
	public List<AppVersion> findByCond(QueryAppVersionForm queryForm) {
		List<AppVersion> list = AppVersionCacheDAO.getInstance().getList(queryForm);
		if(CollectionUtils.isEmpty(list)){
			list = AppVersionDAO.getInstance().findByCond(queryForm);
			if(CollectionUtils.isNotEmpty(list)){
				AppVersionCacheDAO.getInstance().saveList(queryForm, list);
			}
		}
		return list;
	}
	
}

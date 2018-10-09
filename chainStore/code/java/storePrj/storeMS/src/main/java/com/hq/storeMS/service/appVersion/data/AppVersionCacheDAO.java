package com.hq.storeMS.service.appVersion.data;

import java.util.List;

import com.hq.storeMS.common.util.AppUtils;
import com.hq.storeMS.service.appVersion.apiData.QueryAppVersionForm;
import com.zenmind.common.hotSwap.HotSwap;

public class AppVersionCacheDAO {

	public static AppVersionCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(AppVersionCacheDAO.class);
	}

	final private String suffix = "appVersion";

	public void saveList(QueryAppVersionForm queryForm, List<AppVersion> list) {
		AppVersionRedisDAO.getInstance().saveList(list, getGroupName(null), queryForm.getListId());
	}

	public List<AppVersion> getList(QueryAppVersionForm queryForm) {
		return AppVersionRedisDAO.getInstance().getList(getGroupName(null), queryForm.getListId());
	}
	
	//保存单个对象，放在组里面的一个列表  用户非ID的键  如：电话号码、名称等
	public void saveOne(String key, AppVersion target) {
		AppVersionRedisDAO.getInstance().saveOne(getGroupName(null), key, target);
	}
	
	//获取单个对象，从组里面的一个列表获取  用户非ID的键  如：电话号码、名称等
	public AppVersion getOne(String key) {
		return AppVersionRedisDAO.getInstance().findByOne(getGroupName(null), key);
	}
	
	public void save(AppVersion target) {
		AppVersionRedisDAO.getInstance().save(target);
	}
	
	public AppVersion get(long id) {
		return AppVersionRedisDAO.getInstance().get(id);
	}

	public void delete(AppVersion target) {
		AppVersionRedisDAO.getInstance().delete(target.getId());
		AppVersionRedisDAO.getInstance().deleteList(getGroupName(null));
	}

	private String getGroupName(Object storeId) {
		return AppUtils.joinByUnderline(suffix, storeId);
	}
}

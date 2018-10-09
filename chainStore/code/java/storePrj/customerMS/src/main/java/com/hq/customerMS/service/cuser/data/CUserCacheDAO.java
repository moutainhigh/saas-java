package com.hq.customerMS.service.cuser.data;

import java.util.List;

import com.hq.customerMS.common.util.AppUtils;
import com.hq.customerMS.service.cuser.apiData.CUserQueryForm;
import com.zenmind.common.hotSwap.HotSwap;

public class CUserCacheDAO {

	public static CUserCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(CUserCacheDAO.class);
	}

	final private String suffix = "cuser";

	public void saveList(CUserQueryForm queryForm, List<CUser> list) {
		CUserRedisDAO.getInstance().saveList(list, getGroupName(null), queryForm.getListId());
	}

	public List<CUser> getList(CUserQueryForm queryForm) {
		return CUserRedisDAO.getInstance().getList(getGroupName(null), queryForm.getListId());
	}

	public void save(CUser target) {
		CUserRedisDAO.getInstance().save(target);
	}

	public CUser get(long id) {
		return CUserRedisDAO.getInstance().get(id);
	}

	public void delete(CUser target) {
		CUserRedisDAO.getInstance().delete(target.getId());
		CUserRedisDAO.getInstance().deleteList(getGroupName(null));
	}

	// 保存单个对象，放在组里面的一个列表 用户非ID的键 如：电话号码、名称等
	public void saveOne(String key, CUser target) {
		CUserRedisDAO.getInstance().saveOne(getGroupName(null), key, target);
	}

	// 获取单个对象，从组里面的一个列表获取 用户非ID的键 如：电话号码、名称等
	public CUser getOne(String key) {
		return CUserRedisDAO.getInstance().findByOne(getGroupName(null), key);
	}

	private String getGroupName(Object groupId) {
		return AppUtils.joinByUnderline(suffix, groupId);
	}
}

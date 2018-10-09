package com.hq.storeMS.service.buser.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.hq.storeMS.common.util.AppUtils;
import com.hq.storeMS.service.buser.apiData.QueryVipUserForm;
import com.zenmind.common.hotSwap.HotSwap;

public class BUserCacheDAO {

	public static BUserCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(BUserCacheDAO.class);
	}

	final private String suffix = "buser";

	public void saveList(Collection<Long> buserIdSet, List<BUser> list) {
		BUserRedisDAO.getInstance().saveList(list, getGroupName(null), getIdsKey(buserIdSet));
	}

	public List<BUser> getList(Collection<Long> buserIdSet) {
		return BUserRedisDAO.getInstance().getList(getGroupName(null), getIdsKey(buserIdSet));
	}
	
	public void saveList(QueryVipUserForm queryForm, List<BUser> list) {
		BUserRedisDAO.getInstance().saveList(list, getGroupName(null), queryForm.getListId());
	}
	
	public List<BUser> getList(QueryVipUserForm queryForm) {
		return BUserRedisDAO.getInstance().getList(getGroupName(null), queryForm.getListId());
	}
	
	//保存单个对象，放在组里面的一个列表  用户非ID的键  如：电话号码、名称等
	public void saveOne(String key, BUser target) {
		BUserRedisDAO.getInstance().saveOne(getGroupName(null), key, target);
	}
	
	//获取单个对象，从组里面的一个列表获取  用户非ID的键  如：电话号码、名称等
	public BUser getOne(String key) {
		return BUserRedisDAO.getInstance().findByOne(getGroupName(null), key);
	}
	
	public void save(BUser target) {
		BUserRedisDAO.getInstance().save(target);
	}
	
	public BUser get(long id) {
		return BUserRedisDAO.getInstance().get(id);
	}

	public void delete(BUser target) {
		BUserRedisDAO.getInstance().delete(target.getId());
		BUserRedisDAO.getInstance().deleteList(getGroupName(null));
	}

	private String getGroupName(Object storeId) {
		return AppUtils.joinByUnderline(suffix, storeId);
	}
	
	private String getIdsKey(Collection<Long> buserIdSet) {
		List<Long> ids = new ArrayList<Long>(buserIdSet);
		Collections.sort(ids);
		return StringUtils.join(ids, "_");
	}
}

package com.hq.storeManagerMS.service.muser.data;

import java.util.List;

import com.hq.storeManagerMS.service.muser.apiData.MUserQueryForm;
import com.zenmind.common.hotSwap.HotSwap;

public class MUserCacheDAO {

	public static MUserCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(MUserCacheDAO.class);
	}

	final private String groupName = "muser";

	public void saveList(MUserQueryForm queryForm, List<MUser> list) {
		MUserRedisDAO.getInstance().saveList(list, groupName, queryForm.getListId());
	}

	public List<MUser> getList(MUserQueryForm queryForm) {
		return MUserRedisDAO.getInstance().getList(groupName, queryForm.getListId());
	}
	
	//保存单个对象，放在组里面的一个列表  用户非ID的键  如：电话号码、名称等
	public void saveOne(String key, MUser target) {
		MUserRedisDAO.getInstance().saveOne(groupName, key, target);
	}
	
	//获取单个对象，从组里面的一个列表获取  用户非ID的键  如：电话号码、名称等
	public MUser getOne(String key) {
		return MUserRedisDAO.getInstance().findByOne(groupName, key);
	}

	public void save(MUser target) {
		MUserRedisDAO.getInstance().save(target);
	}

	public MUser get(long id) {
		return MUserRedisDAO.getInstance().get(id);
	}

	public void delete(MUser target) {
		MUserRedisDAO.getInstance().delete(target.getId());
		MUserRedisDAO.getInstance().deleteList(groupName);
	}
}

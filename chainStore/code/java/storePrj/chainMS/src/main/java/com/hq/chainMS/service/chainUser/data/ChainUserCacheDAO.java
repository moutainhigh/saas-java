package com.hq.chainMS.service.chainUser.data;

import java.util.List;

import com.hq.chainMS.service.chainUser.apiData.ChainUserQueryForm;
import com.zenmind.common.hotSwap.HotSwap;

public class ChainUserCacheDAO {

	public static ChainUserCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(ChainUserCacheDAO.class);
	}

	final private String groupName = "chainUser";
	
	public void saveList(ChainUserQueryForm queryForm, List<ChainUser> list) {
		ChainUserRedisDAO.getInstance().saveList(list, groupName, queryForm.getListId());
	}

	public List<ChainUser> getList(ChainUserQueryForm queryForm) {
		return ChainUserRedisDAO.getInstance().getList(groupName, queryForm.getListId());
	}

	public void save(ChainUser target) {
		ChainUserRedisDAO.getInstance().save(target);
	}
	
	public void saveOne(String key, ChainUser target) {
		ChainUserRedisDAO.getInstance().saveOne(groupName, key, target);
	}
	
	public ChainUser get(Object id) {
		return ChainUserRedisDAO.getInstance().get(id);
	}
	
	public ChainUser getOne(String key) {
		return ChainUserRedisDAO.getInstance().findByOne(groupName, key);
	}

	public void delete(ChainUser target) {
		ChainUserRedisDAO.getInstance().delete(target.getId());
		ChainUserRedisDAO.getInstance().deleteList(groupName);
	}

}

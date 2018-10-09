package com.hq.storeManagerMS.service.vipLevelType.data;

import java.util.List;

import com.hq.storeManagerMS.service.vipLevelType.apiData.QueryVipLevelTypeForm;
import com.zenmind.common.hotSwap.HotSwap;

public class VipLevelTypeCacheDAO {

	public static VipLevelTypeCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(VipLevelTypeCacheDAO.class);
	}
	
	final private String groupName = "vipLevelType";
	
	public void saveList(QueryVipLevelTypeForm queryForm, List<VipLevelType> list) {
		VipLevelTypeRedisDAO.getInstance().saveList(list, groupName, queryForm.getListId());
	}

	public List<VipLevelType> getList(QueryVipLevelTypeForm queryForm) {
		return VipLevelTypeRedisDAO.getInstance().getList(groupName, queryForm.getListId());
	}
	
	public void save(VipLevelType target) {
		VipLevelTypeRedisDAO.getInstance().save(target);
	}
	
	public VipLevelType get(long id) {
		return VipLevelTypeRedisDAO.getInstance().get(id);
	}

	public void delete(VipLevelType target) {
		VipLevelTypeRedisDAO.getInstance().delete(target.getId());
		VipLevelTypeRedisDAO.getInstance().deleteList(groupName);
	}
}

package com.hq.storeManagerMS.service.vipLevel.data;

import java.util.List;

import com.hq.storeManagerMS.service.vipLevel.apiData.QueryVipLevelForm;
import com.zenmind.common.hotSwap.HotSwap;

public class VipLevelCacheDAO {

	public static VipLevelCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(VipLevelCacheDAO.class);
	}
	
	final private String groupName = "vipLevel";
	
	public void saveList(QueryVipLevelForm queryForm, List<VipLevel> list) {
		VipLevelRedisDAO.getInstance().saveList(list, groupName, queryForm.getListId());
	}

	public List<VipLevel> getList(QueryVipLevelForm queryForm) {
		return VipLevelRedisDAO.getInstance().getList(groupName, queryForm.getListId());
	}
	
	public void save(VipLevel target) {
		VipLevelRedisDAO.getInstance().save(target);
	}
	
	public VipLevel get(long id) {
		return VipLevelRedisDAO.getInstance().get(id);
	}

	public void delete(VipLevel target) {
		VipLevelRedisDAO.getInstance().delete(target.getId());
		VipLevelRedisDAO.getInstance().deleteList(groupName);
	}
}

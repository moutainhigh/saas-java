package com.hq.storeMS.service.dynamic.data;

import java.util.List;

import com.hq.storeMS.common.util.AppUtils;
import com.hq.storeMS.service.dynamic.apiData.DynamicQueryForm;
import com.zenmind.common.hotSwap.HotSwap;

public class DynamicCacheDAO {

	public static DynamicCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(DynamicCacheDAO.class);
	}

	final private String suffix = "dynamic";

	public void saveList(DynamicQueryForm queryForm, List<Dynamic> list) {
		DynamicRedisDAO.getInstance().saveList(list, getGroupName(queryForm.getBuserId()), queryForm.getListId());
	}

	public List<Dynamic> getList(DynamicQueryForm queryForm) {
		return DynamicRedisDAO.getInstance().getList(getGroupName(queryForm.getBuserId()), queryForm.getListId());
	}
	
	public void save(Dynamic target) {
		DynamicRedisDAO.getInstance().save(target);
	}
	
	public Dynamic get(long id) {
		return DynamicRedisDAO.getInstance().get(id);
	}

	public void delete(Dynamic target) {
		DynamicRedisDAO.getInstance().delete(target.getId());
		DynamicRedisDAO.getInstance().deleteList(getGroupName(target.getBuserId()));
	}

	private String getGroupName(Object buserId) {
		return AppUtils.joinByUnderline(suffix, buserId);
	}
}

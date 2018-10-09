package com.hq.storeMS.service.areaCode.data;

import java.util.List;

import com.hq.storeMS.common.util.AppUtils;
import com.hq.storeMS.service.areaCode.apiData.AreaCodeQueryForm;
import com.zenmind.common.hotSwap.HotSwap;

public class AreaCodeCacheDAO {

	public static AreaCodeCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(AreaCodeCacheDAO.class);
	}

	final private String suffix = "areaCode";

	public void saveList(AreaCodeQueryForm queryForm, List<AreaCode> list) {
		AreaCodeRedisDAO.getInstance().saveList(list, getGroupName(null), queryForm.getListId());
	}

	public List<AreaCode> getList(AreaCodeQueryForm queryForm) {
		return AreaCodeRedisDAO.getInstance().getList(getGroupName(null), queryForm.getListId());
	}
	
	public void save(AreaCode target) {
		AreaCodeRedisDAO.getInstance().save(target);
	}
	
	public AreaCode get(long id) {
		return AreaCodeRedisDAO.getInstance().get(id);
	}

	public void delete(AreaCode target) {
		AreaCodeRedisDAO.getInstance().delete(target.getId());
		AreaCodeRedisDAO.getInstance().deleteList(getGroupName(null));
	}

	private String getGroupName(Object storeId) {
		return AppUtils.joinByUnderline(suffix, storeId);
	}
}

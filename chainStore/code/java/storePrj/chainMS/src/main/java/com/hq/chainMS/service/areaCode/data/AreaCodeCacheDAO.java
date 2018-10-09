package com.hq.chainMS.service.areaCode.data;

import java.util.List;

import com.hq.chainMS.service.areaCode.apiData.AreaCodeQueryForm;
import com.zenmind.common.hotSwap.HotSwap;

public class AreaCodeCacheDAO {

	public static AreaCodeCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(AreaCodeCacheDAO.class);
	}

	final private String groupName = "areaCode";

	public void saveList(AreaCodeQueryForm queryForm, List<AreaCode> list) {
		AreaCodeRedisDAO.getInstance().saveList(list, groupName, queryForm.getListId());
	}

	public List<AreaCode> getList(AreaCodeQueryForm queryForm) {
		return AreaCodeRedisDAO.getInstance().getList(groupName, queryForm.getListId());
	}
	
	public void save(AreaCode target) {
		AreaCodeRedisDAO.getInstance().save(target);
	}
	
	public AreaCode get(long id) {
		return AreaCodeRedisDAO.getInstance().get(id);
	}

	public void delete(AreaCode target) {
		AreaCodeRedisDAO.getInstance().delete(target.getId());
		AreaCodeRedisDAO.getInstance().deleteList(groupName);
	}
}

package com.hq.storeMS.service.footprint.data;

import java.util.List;

import com.hq.storeMS.common.util.AppUtils;
import com.hq.storeMS.service.footprint.apiData.FootprintQueryForm;
import com.zenmind.common.hotSwap.HotSwap;

public class FootprintCacheDAO {

	public static FootprintCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(FootprintCacheDAO.class);
	}

	final private String suffix = "footprint";

	public void saveList(FootprintQueryForm queryForm, List<Footprint> list) {
		FootprintRedisDAO.getInstance().saveList(list, getGroupName(queryForm.getBuserId()), queryForm.getListId());
	}

	public List<Footprint> getList(FootprintQueryForm queryForm) {
		return FootprintRedisDAO.getInstance().getList(getGroupName(queryForm.getBuserId()), queryForm.getListId());
	}
	
	public void save(Footprint target) {
		FootprintRedisDAO.getInstance().save(target);
	}
	
	public Footprint get(long id) {
		return FootprintRedisDAO.getInstance().get(id);
	}

	public void delete(Footprint target) {
		FootprintRedisDAO.getInstance().delete(target.getId());
		FootprintRedisDAO.getInstance().deleteList(getGroupName(target.getBuserId()));
	}

	private String getGroupName(Object buserId) {
		return AppUtils.joinByUnderline(suffix, buserId);
	}
}

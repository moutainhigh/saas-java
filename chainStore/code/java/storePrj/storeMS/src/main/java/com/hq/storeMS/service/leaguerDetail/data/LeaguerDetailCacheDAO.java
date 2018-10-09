package com.hq.storeMS.service.leaguerDetail.data;

import java.util.List;

import com.hq.storeMS.common.util.AppUtils;
import com.hq.storeMS.service.leaguerDetail.apiData.LeaguerDetailQueryForm;
import com.zenmind.common.hotSwap.HotSwap;

public class LeaguerDetailCacheDAO {

	public static LeaguerDetailCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(LeaguerDetailCacheDAO.class);
	}

	final private String suffix = "storeLeaguerDetail";

	public void saveList(LeaguerDetailQueryForm queryForm, List<LeaguerDetail> list) {
		LeaguerDetailRedisDAO.getInstance().saveList(list, getGroupName(queryForm.getStoreId()), queryForm.getListId());
	}

	public List<LeaguerDetail> getList(LeaguerDetailQueryForm queryForm) {
		return LeaguerDetailRedisDAO.getInstance().getList(getGroupName(queryForm.getStoreId()), queryForm.getListId());
	}
	
	public void save(LeaguerDetail target) {
		LeaguerDetailRedisDAO.getInstance().saveOne(getGroupName(target.getStoreId()), target.getId(), target);
	}
	
	public LeaguerDetail get(long storeId, String id) {
		return LeaguerDetailRedisDAO.getInstance().findByOne(getGroupName(storeId), id);
	}

	public void delete(LeaguerDetail target) {
		LeaguerDetailRedisDAO.getInstance().delete(target.getId());
		LeaguerDetailRedisDAO.getInstance().deleteList(getGroupName(target.getStoreId()));
	}

	private String getGroupName(Object storeId) {
		return AppUtils.joinByUnderline(suffix, storeId);
	}
}

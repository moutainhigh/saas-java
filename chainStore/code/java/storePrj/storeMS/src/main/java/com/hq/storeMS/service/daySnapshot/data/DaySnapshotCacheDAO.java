package com.hq.storeMS.service.daySnapshot.data;

import java.util.List;

import com.hq.storeMS.common.util.AppUtils;
import com.hq.storeMS.service.daySnapshot.apiData.DaySnapshotQueryForm;
import com.zenmind.common.hotSwap.HotSwap;

public class DaySnapshotCacheDAO {

	public static DaySnapshotCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(DaySnapshotCacheDAO.class);
	}

	final private String suffix = "daySnapshot";

	public void saveList(DaySnapshotQueryForm queryForm, List<DaySnapshot> list) {
		DaySnapshotRedisDAO.getInstance().saveList(list, getGroupName(queryForm.getStoreId()), queryForm.getListId());
	}

	public List<DaySnapshot> getList(DaySnapshotQueryForm queryForm) {
		return DaySnapshotRedisDAO.getInstance().getList(getGroupName(queryForm.getStoreId()), queryForm.getListId());
	}
	
	public void save(DaySnapshot target) {
		DaySnapshotRedisDAO.getInstance().save(target);
	}
	
	public DaySnapshot get(Object id) {
		return DaySnapshotRedisDAO.getInstance().get(id);
	}

	public void delete(DaySnapshot target) {
		DaySnapshotRedisDAO.getInstance().delete(target.getId());
		DaySnapshotRedisDAO.getInstance().deleteList(getGroupName(target.getStoreId()));
	}

	private String getGroupName(Object storeId) {
		return AppUtils.joinByUnderline(suffix, storeId);
	}
}

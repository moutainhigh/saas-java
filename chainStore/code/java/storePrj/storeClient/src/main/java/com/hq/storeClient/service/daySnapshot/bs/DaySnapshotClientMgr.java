package com.hq.storeClient.service.daySnapshot.bs;

import com.hq.storeClient.common.restClientResp.PageResp;
import com.hq.storeClient.service.daySnapshot.apiData.DaySnapshotAddForm;
import com.hq.storeClient.service.daySnapshot.apiData.DaySnapshotQueryForm;
import com.hq.storeClient.service.daySnapshot.data.DaySnapshot;
import com.hq.storeClient.service.daySnapshot.data.DaySnapshotDAO;
import com.hq.storeClient.service.daySnapshot.data.PreDaySnapshotData;
import com.zenmind.common.hotSwap.HotSwap;

public class DaySnapshotClientMgr {

	public static DaySnapshotClientMgr getInstance() {
		return HotSwap.getInstance().getSingleton(DaySnapshotClientMgr.class);
	}

	public PageResp<DaySnapshot> findPage(DaySnapshotQueryForm queryForm) {
		final String findPath = "findPage";
		return DaySnapshotDAO.getInstance().findPage(findPath, queryForm);
	}

	public PreDaySnapshotData getPreDaySnapshotData(DaySnapshotQueryForm queryForm) {
		final String findPath = "getPreDaySnapshotData";
		return DaySnapshotDAO.getInstance().getPreDaySnapshotData(findPath, queryForm);
	}
	
	public DaySnapshot addDaySnapshot(DaySnapshotAddForm addForm) {
		return DaySnapshotDAO.getInstance().add(addForm);
	}
	
	public DaySnapshot get(long id) {
		return DaySnapshotDAO.getInstance().get(id);
	}
}

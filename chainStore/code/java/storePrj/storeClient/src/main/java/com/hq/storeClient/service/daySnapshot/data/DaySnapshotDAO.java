package com.hq.storeClient.service.daySnapshot.data;

import com.hq.storeClient.common.restClientResp.PageResp;
import com.hq.storeClient.common.restClientResp.RestClientCfg;
import com.hq.storeClient.common.utils.JsonUtil4Client;
import com.hq.storeClient.service.daySnapshot.apiData.DaySnapshotQueryForm;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.common.json.JsonUtil;
import com.zenmind.dao.rest.RestDao;
import com.zenmind.dao.rest.RestResp;

public class DaySnapshotDAO extends RestDao<DaySnapshot> {

	public static DaySnapshotDAO getInstance() {
		return HotSwap.getInstance().getSingleton(DaySnapshotDAO.class);
	}
	
	@Override
	public String getService() {
		return RestClientCfg.getService();
	}
	
	public PageResp<DaySnapshot> findPage(String findPath, DaySnapshotQueryForm queryForm) {
		RestResp restResp = super.rawGetReq(findPath, queryForm.toReqMap(), queryForm.getPageItemCount(), queryForm.getPageNo());
		return JsonUtil4Client.getInstance().parseTPage(restResp.gettJson(), DaySnapshot.class);
	}
	
	public PreDaySnapshotData getPreDaySnapshotData(String findPath, DaySnapshotQueryForm queryForm) {
		RestResp restResp = super.rawGetReq(findPath, queryForm.toReqMap(), queryForm.getPageItemCount(), queryForm.getPageNo());
		return JsonUtil.getInstance().fromJson(restResp.gettJson(), PreDaySnapshotData.class);
	}

}

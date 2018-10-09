package com.hq.storeClient.service.dataReport.data;

import com.hq.storeClient.common.restClientResp.RestClientCfg;
import com.hq.storeClient.service.dataReport.apiData.DataReportQueryForm;
import com.hq.storeClient.service.dataReport.data.vo.SellStatisData;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.common.json.JsonUtil;
import com.zenmind.dao.rest.RestDao;
import com.zenmind.dao.rest.RestResp;

public class DataReportRestDAO extends RestDao<DataReport> {

	public static DataReportRestDAO getInstance() {
		return HotSwap.getInstance().getSingleton(DataReportRestDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getService();
	}

	public SellStatisData findSellStatisData(String findPath, DataReportQueryForm queryForm) {
		RestResp restResp = super.rawGetReq(findPath, queryForm.toReqMap(), 0, 1);
		return JsonUtil.getInstance().fromJson(restResp.gettJson(), SellStatisData.class);
	}

}

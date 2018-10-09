package com.hq.storeClient.service.spreadStatis.data;

import com.hq.storeClient.common.restClientResp.RestClientCfg;
import com.hq.storeClient.service.spreadStatis.apiData.SpreadStatisQueryForm;
import com.hq.storeClient.service.spreadStatis.data.vo.BuserSpreadStatis;
import com.hq.storeClient.service.spreadStatis.data.vo.StoreSpreadStatis;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.common.json.JsonUtil;
import com.zenmind.dao.rest.RestDao;
import com.zenmind.dao.rest.RestResp;

public class SpreadStatisDAO extends RestDao<SpreadStatis> {

	public static SpreadStatisDAO getInstance() {
		return HotSwap.getInstance().getSingleton(SpreadStatisDAO.class);
	}
	
	@Override
	public String getService() {
		return RestClientCfg.getService();
	}
	
	public BuserSpreadStatis findBuserSpreadStatis(String findPath, SpreadStatisQueryForm queryForm) {
		RestResp restResp = super.rawGetReq(findPath, queryForm.toReqMap(), queryForm.getPageItemCount(), queryForm.getPageNo());
		return JsonUtil.getInstance().fromJson(restResp.gettJson(), BuserSpreadStatis.class);
	}
	
	public StoreSpreadStatis findStoreSpreadStatis(String findPath, SpreadStatisQueryForm queryForm) {
		RestResp restResp = super.rawGetReq(findPath, queryForm.toReqMap(), queryForm.getPageItemCount(), queryForm.getPageNo());
		return JsonUtil.getInstance().fromJson(restResp.gettJson(), StoreSpreadStatis.class);
	}

}

package com.hq.storeClient.service.charge.data;

import com.hq.storeClient.common.restClientResp.PageResp;
import com.hq.storeClient.common.restClientResp.RestClientCfg;
import com.hq.storeClient.common.utils.JsonUtil4Client;
import com.hq.storeClient.service.charge.apiData.ChargeQueryForm;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;
import com.zenmind.dao.rest.RestResp;

public class ChargeDAO extends RestDao<Charge> {

	public static ChargeDAO getInstance(){
		return HotSwap.getInstance().getSingleton(ChargeDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getService();
	}
	
	public PageResp<Charge> findByCond(String findPath, ChargeQueryForm queryForm) {
		RestResp restResp = super.rawGetReq(findPath, queryForm.toReqMap(), queryForm.getPageItemCount(), queryForm.getPageNo());
		return JsonUtil4Client.getInstance().parseTPage(restResp.gettJson(), Charge.class);
	}
	
}

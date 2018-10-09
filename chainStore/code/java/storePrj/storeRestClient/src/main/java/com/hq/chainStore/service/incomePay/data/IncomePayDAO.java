package com.hq.chainStore.service.incomePay.data;

import com.hq.chainStore.service.common.PageResp;
import com.hq.chainStore.service.common.RestClientCfg;
import com.hq.chainStore.service.incomePay.apiData.IncomePayQueryForm;
import com.hq.common.JsonUtil4Client;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;
import com.zenmind.dao.rest.RestResp;

public class IncomePayDAO extends RestDao<IncomePay> {

	public static IncomePayDAO getInstance(){
		return HotSwap.getInstance().getSingleton(IncomePayDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getStoreService();
	}
	
	public PageResp<IncomePay> findByCond(String findPath, IncomePayQueryForm queryForm) {
		RestResp restResp = super.rawGetReq(findPath, queryForm.toReqMap(), queryForm.getPageItemCount(), queryForm.getPageNo());
		return JsonUtil4Client.getInstance().parseTPage(restResp.gettJson(), IncomePay.class);
	}
	
}

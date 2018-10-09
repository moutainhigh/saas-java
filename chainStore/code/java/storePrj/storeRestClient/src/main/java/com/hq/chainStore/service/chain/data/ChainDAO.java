package com.hq.chainStore.service.chain.data;

import com.hq.chainStore.service.chain.apiData.ChainQueryForm;
import com.hq.chainStore.service.common.PageResp;
import com.hq.chainStore.service.common.RestClientCfg;
import com.hq.common.JsonUtil4Client;
import com.hq.common.StringUtils4Client;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;
import com.zenmind.dao.rest.RestResp;

public class ChainDAO extends RestDao<Chain> {
	public static ChainDAO getInstance(){
		return HotSwap.getInstance().getSingleton(ChainDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getStoreService();
	}
	
	public PageResp<Chain> findChainByCond(ChainQueryForm queryForm) {
		final String findPath = "findChainByCond";
		RestResp restResp = super.rawGetReq(findPath, queryForm.toReqMap(), queryForm.getPageItemCount(), queryForm.getPageNo());
		if(restResp!=null && StringUtils4Client.isNotBlank(restResp.gettJson())) {
			return JsonUtil4Client.getInstance().parseTPage(restResp.gettJson(), Chain.class);
		}
		return null;
	}
}

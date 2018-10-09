package com.hq.chainClient.service.chain.data;

import com.hq.chainClient.common.restClientResp.PageResp;
import com.hq.chainClient.common.restClientResp.RestClientCfg;
import com.hq.chainClient.common.utils.JsonUtil4Client;
import com.hq.chainClient.common.utils.StringUtils4Client;
import com.hq.chainClient.service.chain.apiData.ChainQueryForm;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;
import com.zenmind.dao.rest.RestResp;

public class ChainDAO extends RestDao<Chain> {

	public static ChainDAO getInstance(){
		return HotSwap.getInstance().getSingleton(ChainDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getService();
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

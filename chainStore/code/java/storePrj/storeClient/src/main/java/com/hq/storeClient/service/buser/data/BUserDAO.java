package com.hq.storeClient.service.buser.data;

import com.hq.storeClient.common.restClientResp.PageResp;
import com.hq.storeClient.common.restClientResp.RestClientCfg;
import com.hq.storeClient.common.utils.JsonUtil4Client;
import com.hq.storeClient.service.buser.apiData.BUserChainQueryForm;
import com.hq.storeClient.service.buser.apiData.BUserCommQueryForm;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;
import com.zenmind.dao.rest.RestResp;

public class BUserDAO extends RestDao<BUser> {

	public static BUserDAO getInstance(){
		return HotSwap.getInstance().getSingleton(BUserDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getService();
	}
	
	public PageResp<BUser> findByChain(String findPath, BUserChainQueryForm queryForm) {
		RestResp restResp = super.rawGetReq(findPath, queryForm.toReqMap(), queryForm.getPageItemCount(), queryForm.getPageNo());
		return JsonUtil4Client.getInstance().parseTPage(restResp.gettJson(), BUser.class);
	}
	
	public PageResp<BUser> findByCond(String findPath, BUserCommQueryForm queryForm) {
		RestResp restResp = super.rawGetReq(findPath, queryForm.toReqMap(), queryForm.getPageItemCount(), queryForm.getPageNo());
		return JsonUtil4Client.getInstance().parseTPage(restResp.gettJson(), BUser.class);
	}
	
}

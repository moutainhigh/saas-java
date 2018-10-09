package com.hq.storeManagerRestClient.service.vipLevel.data;

import com.hq.storeManagerRestClient.common.restClientResp.PageResp;
import com.hq.storeManagerRestClient.common.restClientResp.RestClientCfg;
import com.hq.storeManagerRestClient.common.utils.JsonUtil4Client;
import com.hq.storeManagerRestClient.service.vipLevel.apiData.QueryVipLevelForm;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;
import com.zenmind.dao.rest.RestResp;

public class VipLevelDAO extends RestDao<VipLevel>{

	public static VipLevelDAO getInstance(){
		return HotSwap.getInstance().getSingleton(VipLevelDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getService();
	}
	
	public PageResp<VipLevel> findPage(String findPath, QueryVipLevelForm queryForm) {
		RestResp restResp = super.rawGetReq(findPath, queryForm.toReqMap(), queryForm.getPageItemCount(), queryForm.getPageNo());
		return JsonUtil4Client.getInstance().parseTPage(restResp.gettJson(), VipLevel.class);
	}
}

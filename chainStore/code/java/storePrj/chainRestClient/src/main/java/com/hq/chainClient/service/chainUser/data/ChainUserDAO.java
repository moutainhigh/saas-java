package com.hq.chainClient.service.chainUser.data;

import com.hq.chainClient.common.restClientResp.PageResp;
import com.hq.chainClient.common.restClientResp.RestClientCfg;
import com.hq.chainClient.common.utils.JsonUtil4Client;
import com.hq.chainClient.common.utils.StringUtils4Client;
import com.hq.chainClient.service.chainUser.apiData.ChainUserQueryForm;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;
import com.zenmind.dao.rest.RestResp;

public class ChainUserDAO extends RestDao<ChainUser> {

	public static ChainUserDAO getInstance(){
		return HotSwap.getInstance().getSingleton(ChainUserDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getService();
	}

	public PageResp<ChainUserDto> findByCond(ChainUserQueryForm queryForm) {
		final String findPath = "findByCond";
		RestResp restResp = rawGetReq(findPath, queryForm.toReqMap(), queryForm.getPageItemCount(), queryForm.getPageNo());
		if(restResp!=null && StringUtils4Client.isNotBlank(restResp.gettJson())) {
			return JsonUtil4Client.getInstance().parseTPage(restResp.gettJson(), ChainUserDto.class);
		}
		return null;
	}
}

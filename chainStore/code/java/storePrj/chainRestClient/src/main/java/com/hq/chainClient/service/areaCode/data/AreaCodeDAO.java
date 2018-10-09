package com.hq.chainClient.service.areaCode.data;

import java.util.List;

import com.hq.chainClient.common.restClientResp.RestClientCfg;
import com.hq.chainClient.common.utils.StringUtils4Client;
import com.hq.chainClient.service.areaCode.apiData.AreaCodeQueryForm;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class AreaCodeDAO extends RestDao<AreaCode> {

	public static AreaCodeDAO getInstance(){
		return HotSwap.getInstance().getSingleton(AreaCodeDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getService();
	}
	
	public List<AreaCode> findByCondUnAuth(String findPath, AreaCodeQueryForm queryForm){
		String path = StringUtils4Client.format("{}/{}", "unAuth", findPath);
		return findWithReqParam(path, queryForm.toReqMap(), queryForm.getPageItemCount(), queryForm.getPageNo());
	}
	
}

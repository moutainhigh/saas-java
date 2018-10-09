package com.hq.customerRestClient.service.buser.data;

import java.util.List;

import com.hq.customerRestClient.common.restClientResp.RestClientCfg;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.ReqMap;
import com.zenmind.dao.rest.RestDao;

public class BUserDAO extends RestDao<BUser> {

	public static BUserDAO getInstance(){
		return HotSwap.getInstance().getSingleton(BUserDAO.class);
	}
	
	@Override
	public String getService() {
		return RestClientCfg.getService();
	}
	
	/**
	 * 根据ID查询用户列表详情
	 * @param reqMap
	 * @return
	 */
	public List<BUser> findByMultitId(ReqMap reqMap) {
		final String findPath = "findByMultitId";
		List<BUser> buserList = findWithReqParam(findPath, reqMap, -1, -1);
		return buserList;
	}
	
}

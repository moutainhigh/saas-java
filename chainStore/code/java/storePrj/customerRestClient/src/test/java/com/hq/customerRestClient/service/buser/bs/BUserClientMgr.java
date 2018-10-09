package com.hq.customerRestClient.service.buser.bs;

import java.util.List;
import java.util.Set;

import com.hq.customerRestClient.common.util.StringUtils4Client;
import com.hq.customerRestClient.service.buser.data.BUser;
import com.hq.customerRestClient.service.buser.data.BUserDAO;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.ReqMap;

public class BUserClientMgr {

	public static BUserClientMgr getInstance() {
		return HotSwap.getInstance().getSingleton(BUserClientMgr.class);
	}

	public List<BUser> findByMultitId(Set<Long> ids) {
		final String findPath = "findByMultitId";
		ReqMap reqMap = ReqMap.newInstance();
		reqMap.add("ids", StringUtils4Client.join(ids, ","));
		return BUserDAO.getInstance().findWithReqParam(findPath, reqMap, ids.size(), 1);
	}

	public BUser get(long buserId) {
		return BUserDAO.getInstance().get(buserId);
	}
}

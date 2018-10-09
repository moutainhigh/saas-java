package com.hq.chainStore.service.chainPackageProject.data;

import com.hq.chainStore.service.common.RestClientCfg;
import com.hq.common.StringUtils4Client;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.common.json.JsonUtil;
import com.zenmind.dao.rest.ReqMap;
import com.zenmind.dao.rest.RestDao;
import com.zenmind.dao.rest.RestResp;

public class ChainPackageProjectDAO extends RestDao<ChainPackageProject> {

	public static ChainPackageProjectDAO getInstance(){
		return HotSwap.getInstance().getSingleton(ChainPackageProjectDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getStoreService();
	}

	public PackageProjectDetail findPackageProjectDetail(String packageProjectId, long chainId) {
		final String findPath = "findPackageProjectDetail";
		ReqMap reqMap = ReqMap.newInstance().add("packageProjectId", packageProjectId).add("chainId", chainId);
		RestResp restResp = super.rawGetReq(findPath, reqMap, 1, 1);
		if(restResp!=null && StringUtils4Client.isNotBlank(restResp.gettJson())) {
			return JsonUtil.getInstance().fromJson(restResp.gettJson(), PackageProjectDetail.class);
		}
		return null;
	}
}

package com.hq.chainClient.service.chainPackageProject.data;

import com.hq.chainClient.common.restClientResp.PageResp;
import com.hq.chainClient.common.restClientResp.RestClientCfg;
import com.hq.chainClient.common.utils.JsonUtil4Client;
import com.hq.chainClient.service.chainPackageProject.apiData.PackageProjectDetailQueryForm;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;
import com.zenmind.dao.rest.RestResp;

public class PackageProjectDetailDAO extends RestDao<PackageProjectDetail> {

	public static PackageProjectDetailDAO getInstance(){
		return HotSwap.getInstance().getSingleton(PackageProjectDetailDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getService();
	}

	public PageResp<PackageProjectDetail> getPackageProjectDetailPageInfo(String findPath, PackageProjectDetailQueryForm queryForm) {
		RestResp restResp = super.rawGetReq(findPath, queryForm.toReqMap(), queryForm.getPageItemCount(), queryForm.getPageNo());
		return JsonUtil4Client.getInstance().parseTPage(restResp.gettJson(), PackageProjectDetail.class);
	}
	
}

package com.hq.storeClient.service.packageProjectDetail.data;

import com.hq.storeClient.common.restClientResp.PageResp;
import com.hq.storeClient.common.restClientResp.RestClientCfg;
import com.hq.storeClient.common.utils.JsonUtil4Client;
import com.hq.storeClient.service.packageProjectDetail.apiData.PackageProjectDetailQueryForm;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;
import com.zenmind.dao.rest.RestResp;

public class PackageProjectDetailDAO extends RestDao<PackageProjectDetail> {

	public static PackageProjectDetailDAO getInstance() {
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

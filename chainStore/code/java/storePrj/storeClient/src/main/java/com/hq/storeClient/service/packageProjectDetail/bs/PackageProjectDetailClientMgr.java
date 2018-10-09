package com.hq.storeClient.service.packageProjectDetail.bs;

import com.hq.storeClient.common.restClientResp.PageResp;
import com.hq.storeClient.common.utils.StringUtils4Client;
import com.hq.storeClient.service.packageProjectDetail.apiData.PackageProjectDetailQueryForm;
import com.hq.storeClient.service.packageProjectDetail.data.PackageProjectDetail;
import com.hq.storeClient.service.packageProjectDetail.data.PackageProjectDetailDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class PackageProjectDetailClientMgr {

	public static PackageProjectDetailClientMgr getInstance() {
		return HotSwap.getInstance().getSingleton(PackageProjectDetailClientMgr.class);
	}
	
	public PackageProjectDetail getPackageProjectDetail(long storeId, String packageProjectDetailId) {
		String uriPath = StringUtils4Client.format("{}/{}", storeId, packageProjectDetailId);
		return PackageProjectDetailDAO.getInstance().findOne(uriPath);
	}
	
	public PageResp<PackageProjectDetail> getPackageProjectDetailPageInfo(PackageProjectDetailQueryForm queryForm) {
		final String findPath = "getPackageProjectDetailPageInfo";
		return PackageProjectDetailDAO.getInstance().getPackageProjectDetailPageInfo(findPath, queryForm);
	}
	
}

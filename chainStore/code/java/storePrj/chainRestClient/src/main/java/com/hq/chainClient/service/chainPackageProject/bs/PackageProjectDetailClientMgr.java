package com.hq.chainClient.service.chainPackageProject.bs;

import com.hq.chainClient.common.restClientResp.PageResp;
import com.hq.chainClient.service.chainPackageProject.apiData.PackageProjectDetailQueryForm;
import com.hq.chainClient.service.chainPackageProject.data.PackageProjectDetail;
import com.hq.chainClient.service.chainPackageProject.data.PackageProjectDetailDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class PackageProjectDetailClientMgr {

	public static PackageProjectDetailClientMgr getInstance() {
		return HotSwap.getInstance().getSingleton(PackageProjectDetailClientMgr.class);
	}

	public PageResp<PackageProjectDetail> getPackageProjectDetailPageInfo(PackageProjectDetailQueryForm queryForm) {
		final String findPath = "getPackageProjectDetailPageInfo";
		return PackageProjectDetailDAO.getInstance().getPackageProjectDetailPageInfo(findPath, queryForm);
	}

	public PackageProjectDetail getPackageProjectDetail(long chainId, String detailId) {
		return PackageProjectDetailDAO.getInstance().get(chainId + "/" + detailId);
	}
}

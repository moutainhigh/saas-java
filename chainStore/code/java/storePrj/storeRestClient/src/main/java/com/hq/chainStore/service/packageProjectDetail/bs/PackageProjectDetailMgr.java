package com.hq.chainStore.service.packageProjectDetail.bs;

import com.hq.chainStore.service.common.PageResp;
import com.hq.chainStore.service.packageProjectDetail.apiData.PackageProjectDetailQueryForm;
import com.hq.chainStore.service.packageProjectDetail.data.PackageProjectDetail;
import com.hq.chainStore.service.packageProjectDetail.data.PackageProjectDetailDAO;
import com.hq.common.StringUtils4Client;
import com.zenmind.common.hotSwap.HotSwap;

public class PackageProjectDetailMgr {

	public static PackageProjectDetailMgr getInstance() {
		return HotSwap.getInstance().getSingleton(PackageProjectDetailMgr.class);
	}
	
	public PageResp<PackageProjectDetail> getPackageProjectDetailPageInfo(PackageProjectDetailQueryForm queryForm) {
		final String findPath = "getPackageProjectDetailPageInfo";
		return PackageProjectDetailDAO.getInstance().getPackageProjectDetailPageInfo(findPath, queryForm);
	}
	
	@Deprecated
	public PackageProjectDetail getPackageProjectDetail(String packageProjectDetailId) {
		return PackageProjectDetailDAO.getInstance().get(packageProjectDetailId);
	}
	
	public PackageProjectDetail getPackageProjectDetail(long storeId, String packageProjectDetailId) {
		String uriPath=StringUtils4Client.format("{}/{}", storeId, packageProjectDetailId);
		return PackageProjectDetailDAO.getInstance().findOne(uriPath);
	}
}

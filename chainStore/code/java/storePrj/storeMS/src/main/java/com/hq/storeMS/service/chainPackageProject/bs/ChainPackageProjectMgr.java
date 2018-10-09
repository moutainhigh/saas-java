package com.hq.storeMS.service.chainPackageProject.bs;

import com.hq.chainClient.service.chainPackageProject.data.ChainPackageProject;
import com.hq.chainClient.service.chainPackageProject.data.PackageProjectDetail;
import com.zenmind.common.hotSwap.HotSwap;

public class ChainPackageProjectMgr {

	public static ChainPackageProjectMgr getInstance() {
		return HotSwap.getInstance().getSingleton(ChainPackageProjectMgr.class);
	}

	public ChainPackageProject getChainPackageProject(long chainId) {
		return ChainPackageProjectDataHolder.getInstance().get(chainId);
	}

	public PackageProjectDetail getPackageProjectDetail(String PackageProjectId, long chainId) {
		return ChainPackageProjectDataHolder.getInstance().getPackageProjectDetail(PackageProjectId, chainId);
	}
}

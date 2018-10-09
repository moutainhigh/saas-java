package com.hq.chainStore.service.chainPackageProject.bs;

import com.hq.chainStore.service.chainPackageProject.data.ChainPackageProject;
import com.hq.chainStore.service.chainPackageProject.data.ChainPackageProjectDAO;
import com.hq.chainStore.service.chainPackageProject.data.PackageProjectDetail;
import com.zenmind.common.hotSwap.HotSwap;

public class ChainPackageProjectMgr {

	public static ChainPackageProjectMgr getInstance(){
		return HotSwap.getInstance().getSingleton(ChainPackageProjectMgr.class);
	}
	
	public ChainPackageProject get(long chainId) {
		return ChainPackageProjectDAO.getInstance().get(chainId);
	}
	
	public PackageProjectDetail findPackageProjectDetail(String packageProjectId, long chainId) {
		return ChainPackageProjectDAO.getInstance().findPackageProjectDetail(packageProjectId, chainId);
	}
	
}

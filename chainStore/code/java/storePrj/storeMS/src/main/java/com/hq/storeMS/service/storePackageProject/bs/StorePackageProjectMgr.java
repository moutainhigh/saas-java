package com.hq.storeMS.service.storePackageProject.bs;

import com.hq.storeMS.service.storePackageProject.data.StorePackageProject;
import com.zenmind.common.hotSwap.HotSwap;

public class StorePackageProjectMgr {

	public static StorePackageProjectMgr getInstance() {
		return HotSwap.getInstance().getSingleton(StorePackageProjectMgr.class);
	}

	public void updateStorePackageProject(StorePackageProject storePackageProject) {
		StorePackageProjectDataHolder.getInstance().update(storePackageProject);
	}
	
	public StorePackageProject getByStoreId(long storeId) {
		StorePackageProject info = StorePackageProjectDataHolder.getInstance().get(storeId);
		if(info == null){//不存在，则新增
			info = StorePackageProject.newInstance(storeId);
			StorePackageProjectDataHolder.getInstance().addWithId(info);
		}
		return info;
	}
}

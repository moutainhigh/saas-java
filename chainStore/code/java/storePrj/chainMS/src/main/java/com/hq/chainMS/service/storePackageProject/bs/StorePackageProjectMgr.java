package com.hq.chainMS.service.storePackageProject.bs;

import java.util.Set;

import com.hq.storeClient.service.storePackageProject.apiData.PackageProjectBatchUpdateStateForm;
import com.hq.storeClient.service.storePackageProject.apiData.PackageProjectUpdateStateForm;
import com.hq.storeClient.service.storePackageProject.data.StorePackageProject;
import com.zenmind.common.hotSwap.HotSwap;

public class StorePackageProjectMgr {

	public static StorePackageProjectMgr getInstance() {
		return HotSwap.getInstance().getSingleton(StorePackageProjectMgr.class);
	}
	
	public StorePackageProject getStorePackageProject(long storeId) {
		return StorePackageProjectDataHolder.getInstance().getStorePackageProject(storeId);
	}
	
	public void updPackageProjectState(Set<Long> applyStoreIds, String id, int state) {
		for (Long storeId : applyStoreIds) {
			PackageProjectUpdateStateForm data = PackageProjectUpdateStateForm.newInstance();
			data.setId(id);
			data.setState(state);
			updPackageProjectState(storeId, data);
		}
	}
	
	public void batchUpdatePackageProjectState(long storeId, PackageProjectBatchUpdateStateForm inputForm) {
		StorePackageProjectDataHolder.getInstance().batchUpdatePackageProjectState(storeId, inputForm);
	}
	
	public void updPackageProjectState(long storeId, PackageProjectUpdateStateForm inputForm) {
		StorePackageProjectDataHolder.getInstance().updPackageProjectState(storeId, inputForm);
	}
}

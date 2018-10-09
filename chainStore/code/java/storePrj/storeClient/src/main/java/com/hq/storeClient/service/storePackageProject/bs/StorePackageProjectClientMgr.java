package com.hq.storeClient.service.storePackageProject.bs;

import com.hq.storeClient.service.storePackageProject.apiData.PackageProjectBatchUpdateStateForm;
import com.hq.storeClient.service.storePackageProject.apiData.PackageProjectUpdateStateForm;
import com.hq.storeClient.service.storePackageProject.apiData.StorePackageProjectUpdateForm;
import com.hq.storeClient.service.storePackageProject.apiData.StorePackageProjectUpdateType;
import com.hq.storeClient.service.storePackageProject.data.StorePackageProject;
import com.hq.storeClient.service.storePackageProject.data.StorePackageProjectDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class StorePackageProjectClientMgr {

	public static StorePackageProjectClientMgr getInstance() {
		return HotSwap.getInstance().getSingleton(StorePackageProjectClientMgr.class);
	}

	public StorePackageProject get(long storeId) {
		return StorePackageProjectDAO.getInstance().get(storeId);
	}

	public void update(long storeId, StorePackageProjectUpdateForm updateForm) {
		StorePackageProjectDAO.getInstance().update(storeId, updateForm);
	}

	public void updPackageProjectState(long storeId, PackageProjectUpdateStateForm inputForm) {
		StorePackageProjectUpdateForm updateForm = StorePackageProjectUpdateForm.newInstance();
		updateForm.setUpdateType(StorePackageProjectUpdateType.UpdPackageProjectState.ordinal());
		updateForm.setProjectUpdateStateForm(inputForm);
		update(storeId, updateForm);
	}

	public void batchUpdatePackageProjectState(long storeId, PackageProjectBatchUpdateStateForm inputForm) {
		StorePackageProjectUpdateForm updateForm = StorePackageProjectUpdateForm.newInstance();
		updateForm.setUpdateType(StorePackageProjectUpdateType.BatchUpdatePackageProjectState.ordinal());
		updateForm.setProjectBatchUpdateStateForm(inputForm);
		update(storeId, updateForm);
	}

}

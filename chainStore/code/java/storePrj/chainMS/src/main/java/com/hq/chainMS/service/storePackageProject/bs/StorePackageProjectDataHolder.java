package com.hq.chainMS.service.storePackageProject.bs;

import com.hq.chainMS.common.constants.ServerConstants;
import com.hq.chainMS.common.validate.AppIdThreadLocal;
import com.hq.chainMS.service.storePackageProject.data.StorePackageProjectCacheDAO;
import com.hq.storeClient.service.storePackageProject.apiData.PackageProjectBatchUpdateStateForm;
import com.hq.storeClient.service.storePackageProject.apiData.PackageProjectUpdateStateForm;
import com.hq.storeClient.service.storePackageProject.bs.StorePackageProjectClientMgr;
import com.hq.storeClient.service.storePackageProject.data.StorePackageProject;
import com.zenmind.common.hotSwap.HotSwap;

public class StorePackageProjectDataHolder {

	public static StorePackageProjectDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(StorePackageProjectDataHolder.class);
	}

	public StorePackageProject getStorePackageProject(long storeId) {
		StorePackageProject data = StorePackageProjectCacheDAO.getInstance().get(storeId);
		if(data!=null) {
			return data;
		}
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		data = StorePackageProjectClientMgr.getInstance().get(storeId);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}
	
	public void batchUpdatePackageProjectState(long storeId, PackageProjectBatchUpdateStateForm inputForm) {
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		StorePackageProjectClientMgr.getInstance().batchUpdatePackageProjectState(storeId, inputForm);
		AppIdThreadLocal.getInstance().set(null);
	}
	
	public void updPackageProjectState(long storeId, PackageProjectUpdateStateForm inputForm) {
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		StorePackageProjectClientMgr.getInstance().updPackageProjectState(storeId, inputForm);
		AppIdThreadLocal.getInstance().set(null);
	}
	
}

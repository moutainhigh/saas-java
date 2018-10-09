package com.hq.chainStore.service.storePackageProject.bs;

import com.hq.chainStore.service.storePackageProject.apiData.PackageProjectAddForm;
import com.hq.chainStore.service.storePackageProject.apiData.PackageProjectBatchUpdateStateForm;
import com.hq.chainStore.service.storePackageProject.apiData.PackageProjectRemoveForm;
import com.hq.chainStore.service.storePackageProject.apiData.PackageProjectTypeAddForm;
import com.hq.chainStore.service.storePackageProject.apiData.PackageProjectTypeRemoveForm;
import com.hq.chainStore.service.storePackageProject.apiData.PackageProjectTypeUpdateForm;
import com.hq.chainStore.service.storePackageProject.apiData.PackageProjectUpdateForm;
import com.hq.chainStore.service.storePackageProject.apiData.PackageProjectUpdateStateForm;
import com.hq.chainStore.service.storePackageProject.apiData.StorePackageProjectUpdateForm;
import com.hq.chainStore.service.storePackageProject.apiData.StorePackageProjectUpdateType;
import com.hq.chainStore.service.storePackageProject.data.StorePackageProject;
import com.hq.chainStore.service.storePackageProject.data.StorePackageProjectDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class StorePackageProjectMgr {

	public static StorePackageProjectMgr getInstance(){
		return HotSwap.getInstance().getSingleton(StorePackageProjectMgr.class);
	}

	public StorePackageProject getStorePackageProject(long storeId) {
		return StorePackageProjectDAO.getInstance().get(storeId);
	}
	
	private void update(long storeId, StorePackageProjectUpdateForm updateForm) {
		StorePackageProjectDAO.getInstance().update(storeId, updateForm);
	}
	
	/*************************** 套餐基本操作 ***************************/
	public void addPackageProject(long storeId, PackageProjectAddForm inputForm){
		StorePackageProjectUpdateForm updateForm = StorePackageProjectUpdateForm.newInstance();
		updateForm.setUpdateType(StorePackageProjectUpdateType.AddPackageProject.ordinal());
		updateForm.setPackageProjectAddForm(inputForm);
		update(storeId, updateForm);
	}
	
	public void removePackageProject(long storeId, PackageProjectRemoveForm inputForm){
		StorePackageProjectUpdateForm updateForm = StorePackageProjectUpdateForm.newInstance();
		updateForm.setUpdateType(StorePackageProjectUpdateType.RemovePackageProject.ordinal());
		updateForm.setPackageProjectRemoveForm(inputForm);
		update(storeId, updateForm);
	}
	
	public void updatePackageProject(long storeId, PackageProjectUpdateForm inputForm){
		StorePackageProjectUpdateForm updateForm = StorePackageProjectUpdateForm.newInstance();
		updateForm.setUpdateType(StorePackageProjectUpdateType.UpdatePackageProject.ordinal());
		updateForm.setPackageProjectUpdateForm(inputForm);
		update(storeId, updateForm);
	}
	
	public void updPackageProjectState(long storeId, PackageProjectUpdateStateForm inputForm){
		StorePackageProjectUpdateForm updateForm = StorePackageProjectUpdateForm.newInstance();
		updateForm.setUpdateType(StorePackageProjectUpdateType.UpdPackageProjectState.ordinal());
		updateForm.setProjectUpdateStateForm(inputForm);
		update(storeId, updateForm);
	}
	
	public void batchUpdatePackageProjectState(long storeId, PackageProjectBatchUpdateStateForm inputForm){
		StorePackageProjectUpdateForm updateForm = StorePackageProjectUpdateForm.newInstance();
		updateForm.setUpdateType(StorePackageProjectUpdateType.BatchUpdatePackageProjectState.ordinal());
		updateForm.setProjectBatchUpdateStateForm(inputForm);
		update(storeId, updateForm);
	}
	
	/*************************** 套餐分类基本操作 ***************************/
	public void addPackageProjectType(long storeId, PackageProjectTypeAddForm inputForm){
		StorePackageProjectUpdateForm updateForm = StorePackageProjectUpdateForm.newInstance();
		updateForm.setUpdateType(StorePackageProjectUpdateType.AddPackageProjectType.ordinal());
		updateForm.setPackageProjectTypeAddForm(inputForm);
		update(storeId, updateForm);
	}
	
	public void removePackageProjectType(long storeId, PackageProjectTypeRemoveForm inputForm){
		StorePackageProjectUpdateForm updateForm = StorePackageProjectUpdateForm.newInstance();
		updateForm.setUpdateType(StorePackageProjectUpdateType.RemovePackageProjectType.ordinal());
		updateForm.setPackageProjectTypeRemoveForm(inputForm);
		update(storeId, updateForm);
	}
	
	public void updatePackageProjectType(long storeId, PackageProjectTypeUpdateForm inputForm){
		StorePackageProjectUpdateForm updateForm = StorePackageProjectUpdateForm.newInstance();
		updateForm.setUpdateType(StorePackageProjectUpdateType.UpdatePackageProjectType.ordinal());
		updateForm.setPackageProjectTypeUpdateForm(inputForm);
		update(storeId, updateForm);
	}
}

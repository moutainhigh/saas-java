package com.hq.storeMS.service.storePackageProject.data;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.hq.storeMS.common.constants.ServerConstants;
import com.hq.storeMS.common.util.AppUtils;
import com.hq.storeMS.service.common.EntityState;
import com.hq.storeMS.service.storePackageProject.apiData.PackageProjectAddForm;
import com.hq.storeMS.service.storePackageProject.apiData.PackageProjectBatchUpdateStateForm;
import com.hq.storeMS.service.storePackageProject.apiData.PackageProjectRemoveForm;
import com.hq.storeMS.service.storePackageProject.apiData.PackageProjectTypeAddForm;
import com.hq.storeMS.service.storePackageProject.apiData.PackageProjectTypeRemoveForm;
import com.hq.storeMS.service.storePackageProject.apiData.PackageProjectTypeUpdateForm;
import com.hq.storeMS.service.storePackageProject.apiData.PackageProjectUpdateForm;
import com.hq.storeMS.service.storePackageProject.apiData.PackageProjectUpdateStateForm;
import com.zenmind.common.hotSwap.HotSwap;

public class StorePackageProjectBeanHelper {

	public static StorePackageProjectBeanHelper getInstance() {
		return HotSwap.getInstance().getSingleton(StorePackageProjectBeanHelper.class);
	}
	
	/*************************** 套餐基本操作 ***************************/
	public boolean addPackageProject(StorePackageProject storePackageProject, PackageProjectAddForm inputForm) {
		if(storePackageProject == null){
			return false;
		}
		long storeId = storePackageProject.getStoreId();
		PackageProject data = inputForm.toPackageProject(storeId);
		Map<String, PackageProject> packageProjectMap = storePackageProject.getPackageProjectMap();
		long index = inputForm.getIndex();
		if (!packageProjectMap.containsKey(data.getId()) && storePackageProject.getPackageProjectIndex() + 1 == index) {
			packageProjectMap.put(data.getId(), data);
			storePackageProject.setPackageProjectIndex(index);
			return true;
		}
		return false;
	}
	
	public boolean removePackageProject(StorePackageProject storePackageProject, PackageProjectRemoveForm inputForm) {
		if(storePackageProject == null){
			return false;
		}
		inputForm.setId(getLocalId(inputForm.getId()));
		Map<String, PackageProject> packageProjectMap = storePackageProject.getPackageProjectMap();
		if (packageProjectMap.containsKey(inputForm.getId())) {
			PackageProject data = packageProjectMap.get(inputForm.getId());
			data.setEntityState(EntityState.Deleted.ordinal());
			return true;
		}
		return false;
	}
	
	public boolean updatePackageProject(StorePackageProject storePackageProject, PackageProjectUpdateForm inputForm) {
		if(storePackageProject == null){
			return false;
		}
		inputForm.setId(getLocalId(inputForm.getId()));
		Map<String, PackageProject> packageProjectMap = storePackageProject.getPackageProjectMap();
		if (packageProjectMap.containsKey(inputForm.getId())) {
			PackageProject oldData = packageProjectMap.get(inputForm.getId());
			PackageProject newData = AppUtils.copyBeanBySerialize(oldData, PackageProject.class);
			inputForm.updatePackageProject(newData);
			if(!newData.equals(oldData)) {
				packageProjectMap.put(newData.getId(), newData);
				return true;
			}
		}
		return false;
	}
	
	public boolean updatePackageProjectState(StorePackageProject storePackageProject, PackageProjectUpdateStateForm inputForm){
		if(storePackageProject == null){
			return false;
		}
		inputForm.setId(getLocalId(inputForm.getId()));
		Map<String, PackageProject> packageProjectMap = storePackageProject.getPackageProjectMap();
		if (packageProjectMap.containsKey(inputForm.getId())){
			PackageProject data = packageProjectMap.get(inputForm.getId());
			data.setState(inputForm.getState());
			return true;
		}
		return false;
	}
	
	private String getLocalId(String id) {
		if(id.contains(ServerConstants.CHAIN_ID_SUFFFIX)) {
			return ServerConstants.CHAIN_ID_SUFFFIX+StringUtils.substringAfter(id, ServerConstants.CHAIN_ID_SUFFFIX);
		}
		return id;
	}
	
	public boolean batchUpdatePackageProjectState(StorePackageProject storePackageProject, PackageProjectBatchUpdateStateForm inputForm){
		if(storePackageProject == null){
			return false;
		}
		List<PackageProjectUpdateStateForm> list = inputForm.toPackageProjectUpdateStateFormList();
		for (PackageProjectUpdateStateForm form : list) {
			if(!updatePackageProjectState(storePackageProject, form)) {
				return false;
			}
		}
		return true;
	}
	
	/*************************** 套餐分类基本操作 ***************************/
	public boolean addPackageProjectType(StorePackageProject storeData, PackageProjectTypeAddForm inputForm) {
		if (storeData == null) {
			return false;
		}
		boolean success = false;
		Map<String, PackageProjectType> packageProjectTypeMap = storeData.getPackageProjectTypeMap();
		PackageProjectType data = inputForm.toPackageProjectType();
		long index = inputForm.getIndex();
		if (!packageProjectTypeMap.containsKey(data.getId()) && storeData.getPackageProjectTypeIndex() + 1 == index) {
			packageProjectTypeMap.put(data.getId(), data);
			storeData.setPackageProjectTypeIndex(index);
			success = true;
		}
		return success;
	}

	public boolean removePackageProjectType(StorePackageProject storeData, PackageProjectTypeRemoveForm inputForm) {
		if (storeData == null) {
			return false;
		}
		boolean success = false;
		Map<String, PackageProjectType> packageProjectTypeMap = storeData.getPackageProjectTypeMap();
		if (packageProjectTypeMap.containsKey(inputForm.getId())) {
			PackageProjectType data = packageProjectTypeMap.get(inputForm.getId());
			data.setEntityState(EntityState.Deleted.ordinal());
			data.setLastUpdateTime(System.currentTimeMillis());
			success = true;
		}
		return success;
	}

	public boolean updatePackageProjectType(StorePackageProject storeData, PackageProjectTypeUpdateForm inputForm) {
		if (storeData == null) {
			return false;
		}
		boolean success = false;
		Map<String, PackageProjectType> packageProjectTypeMap = storeData.getPackageProjectTypeMap();
		if (packageProjectTypeMap.containsKey(inputForm.getId())) {
			PackageProjectType data = packageProjectTypeMap.get(inputForm.getId());
			inputForm.updatePackageProjectType(data);
			data.setLastUpdateTime(System.currentTimeMillis());
			success = true;
		}
		return success;
	}
}

package com.hq.chainMS.service.chainPackageProject.data;

import java.util.Map;

import com.hq.chainMS.common.util.AppUtils;
import com.hq.chainMS.service.chainPackageProject.apiData.PackageProjectAddForm;
import com.hq.chainMS.service.chainPackageProject.apiData.PackageProjectAllotForm;
import com.hq.chainMS.service.chainPackageProject.apiData.PackageProjectRemoveForm;
import com.hq.chainMS.service.chainPackageProject.apiData.PackageProjectTypeAddForm;
import com.hq.chainMS.service.chainPackageProject.apiData.PackageProjectTypeRemoveForm;
import com.hq.chainMS.service.chainPackageProject.apiData.PackageProjectTypeUpdateForm;
import com.hq.chainMS.service.chainPackageProject.apiData.PackageProjectUpdateForm;
import com.hq.chainMS.service.chainPackageProject.apiData.PackageProjectUpdateStateForm;
import com.hq.chainMS.service.common.EntityState;
import com.zenmind.common.hotSwap.HotSwap;

public class ChainPackageProjectBeanHelper {

	public static ChainPackageProjectBeanHelper getInstance() {
		return HotSwap.getInstance().getSingleton(ChainPackageProjectBeanHelper.class);
	}
	
	/*************************** 套餐基本操作 ***************************/
	public boolean addPackageProject(ChainPackageProject chainPackageProject, PackageProjectAddForm inputForm) {
		if(chainPackageProject == null){
			return false;
		}
		long chainId = chainPackageProject.getChainId();
		PackageProject data = inputForm.toPackageProject(chainId);
		Map<String, PackageProject> packageProjectMap = chainPackageProject.getPackageProjectMap();
		long index = inputForm.getIndex();
		if (!packageProjectMap.containsKey(data.getId()) && chainPackageProject.getPackageProjectIndex() + 1 == index) {
			packageProjectMap.put(data.getId(), data);
			chainPackageProject.setPackageProjectIndex(index);
			return true;
		}
		return false;
	}
	
	public boolean removePackageProject(ChainPackageProject chainPackageProject, PackageProjectRemoveForm inputForm) {
		if(chainPackageProject == null){
			return false;
		}
		Map<String, PackageProject> packageProjectMap = chainPackageProject.getPackageProjectMap();
		if (packageProjectMap.containsKey(inputForm.getId())) {
			PackageProject data = packageProjectMap.get(inputForm.getId());
			data.setEntityState(EntityState.Deleted.ordinal());
			return true;
		}
		return false;
	}
	
	public boolean updatePackageProject(ChainPackageProject chainPackageProject, PackageProjectUpdateForm inputForm) {
		if(chainPackageProject == null){
			return false;
		}
		Map<String, PackageProject> packageProjectMap = chainPackageProject.getPackageProjectMap();
		if (packageProjectMap.containsKey(inputForm.getId())) {
			PackageProject oldTarget = packageProjectMap.get(inputForm.getId());
			PackageProject newTarget = AppUtils.copyBeanBySerialize(oldTarget, PackageProject.class);
			inputForm.updatePackageProject(newTarget);
			if(!newTarget.equals(oldTarget)) {
				packageProjectMap.put(newTarget.getId(), newTarget);
				return true;
			}
		}
		return false;
	}
	
	public boolean updatePackageProjectState(ChainPackageProject chainPackageProject, PackageProjectUpdateStateForm inputForm){
		if(chainPackageProject == null){
			return false;
		}
		Map<String, PackageProject> packageProjectMap = chainPackageProject.getPackageProjectMap();
		if (packageProjectMap.containsKey(inputForm.getId())){
			PackageProject data = packageProjectMap.get(inputForm.getId());
			data.setState(inputForm.getState());
			return true;
		}
		return false;
	}
	
	public boolean allotStore(ChainPackageProject chainPackageProject, PackageProjectAllotForm inputForm){
		if(chainPackageProject == null){
			return false;
		}
		Map<String, PackageProject> packageProjectMap = chainPackageProject.getPackageProjectMap();
		if (packageProjectMap.containsKey(inputForm.getId())){
			PackageProject data = packageProjectMap.get(inputForm.getId());
			data.setApplyStoreIds(inputForm.getApplyStoreIds());
			return true;
		}
		return false;
	}
	
	/*************************** 套餐分类基本操作 ***************************/
	public boolean addPackageProjectType(ChainPackageProject chainData, PackageProjectTypeAddForm inputForm) {
		if (chainData == null) {
			return false;
		}
		boolean success = false;
		Map<String, PackageProjectType> packageProjectTypeMap = chainData.getPackageProjectTypeMap();
		PackageProjectType data = inputForm.toPackageProjectType(chainData.getChainId());
		long index = inputForm.getIndex();
		if (!packageProjectTypeMap.containsKey(data.getId()) && chainData.getPackageProjectTypeIndex() + 1 == index) {
			packageProjectTypeMap.put(data.getId(), data);
			chainData.setPackageProjectTypeIndex(index);
			success = true;
		}
		return success;
	}

	public boolean removePackageProjectType(ChainPackageProject chainData, PackageProjectTypeRemoveForm inputForm) {
		if (chainData == null) {
			return false;
		}
		boolean success = false;
		Map<String, PackageProjectType> packageProjectTypeMap = chainData.getPackageProjectTypeMap();
		if (packageProjectTypeMap.containsKey(inputForm.getId())) {
			PackageProjectType data = packageProjectTypeMap.get(inputForm.getId());
			data.setEntityState(EntityState.Deleted.ordinal());
			data.setLastUpdateTime(System.currentTimeMillis());
			success = true;
		}
		return success;
	}

	public boolean updatePackageProjectType(ChainPackageProject chainData, PackageProjectTypeUpdateForm inputForm) {
		if (chainData == null) {
			return false;
		}
		boolean success = false;
		Map<String, PackageProjectType> packageProjectTypeMap = chainData.getPackageProjectTypeMap();
		if (packageProjectTypeMap.containsKey(inputForm.getId())) {
			PackageProjectType data = packageProjectTypeMap.get(inputForm.getId());
			inputForm.updatePackageProjectType(data);
			data.setLastUpdateTime(System.currentTimeMillis());
			success = true;
		}
		return success;
	}
}

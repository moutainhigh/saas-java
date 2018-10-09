package com.hq.chainClient.service.chainPackageProject.bs;

import com.hq.chainClient.service.chainPackageProject.apiData.ChainPackageProjectUpdateForm;
import com.hq.chainClient.service.chainPackageProject.apiData.ChainPackageProjectUpdateType;
import com.hq.chainClient.service.chainPackageProject.apiData.PackageProjectAddForm;
import com.hq.chainClient.service.chainPackageProject.apiData.PackageProjectBatchUpdateStateForm;
import com.hq.chainClient.service.chainPackageProject.apiData.PackageProjectRemoveForm;
import com.hq.chainClient.service.chainPackageProject.apiData.PackageProjectTypeAddForm;
import com.hq.chainClient.service.chainPackageProject.apiData.PackageProjectTypeRemoveForm;
import com.hq.chainClient.service.chainPackageProject.apiData.PackageProjectTypeUpdateForm;
import com.hq.chainClient.service.chainPackageProject.apiData.PackageProjectUpdateForm;
import com.hq.chainClient.service.chainPackageProject.apiData.PackageProjectUpdateStateForm;
import com.hq.chainClient.service.chainPackageProject.data.ChainPackageProject;
import com.hq.chainClient.service.chainPackageProject.data.ChainPackageProjectDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class ChainPackageProjectClientMgr {

	public static ChainPackageProjectClientMgr getInstance(){
		return HotSwap.getInstance().getSingleton(ChainPackageProjectClientMgr.class);
	}
	
	public ChainPackageProject get(long chainId) {
		return ChainPackageProjectDAO.getInstance().get(chainId);
	}
	
	/***********************************套餐分类操作***********************************/
	//新增分类
	public void addPackageProjectType(long chainId, PackageProjectTypeAddForm inputForm) {
		ChainPackageProjectUpdateForm updateForm = ChainPackageProjectUpdateForm.newInstance();
		updateForm.setPackageProjectTypeAddForm(inputForm);
		updateForm.setUpdateType(ChainPackageProjectUpdateType.AddPackageProjectType.ordinal());
		update(chainId, updateForm);
	}
	
	//删除分类
	public void removePackageProjectType(long chainId, PackageProjectTypeRemoveForm inputForm) {
		ChainPackageProjectUpdateForm updateForm = ChainPackageProjectUpdateForm.newInstance();
		updateForm.setPackageProjectTypeRemoveForm(inputForm);
		updateForm.setUpdateType(ChainPackageProjectUpdateType.RemovePackageProjectType.ordinal());
		update(chainId, updateForm);
	}
	
	//修改分类
	public void updatePackageProjectType(long chainId, PackageProjectTypeUpdateForm inputForm) {
		ChainPackageProjectUpdateForm updateForm = ChainPackageProjectUpdateForm.newInstance();
		updateForm.setPackageProjectTypeUpdateForm(inputForm);
		updateForm.setUpdateType(ChainPackageProjectUpdateType.UpdatePackageProjectType.ordinal());
		update(chainId, updateForm);
	}
	
	/***********************************套餐操作***********************************/
	//新增套餐
	public void addPackageProject(long chainId, PackageProjectAddForm inputForm) {
		ChainPackageProjectUpdateForm updateForm = ChainPackageProjectUpdateForm.newInstance();
		updateForm.setPackageProjectAddForm(inputForm);
		updateForm.setUpdateType(ChainPackageProjectUpdateType.AddPackageProject.ordinal());
		update(chainId, updateForm);
	}
	
	//删除套餐
	public void removePackageProject(long chainId, PackageProjectRemoveForm inputForm) {
		ChainPackageProjectUpdateForm updateForm = ChainPackageProjectUpdateForm.newInstance();
		updateForm.setPackageProjectRemoveForm(inputForm);
		updateForm.setUpdateType(ChainPackageProjectUpdateType.RemovePackageProject.ordinal());
		update(chainId, updateForm);
	}
	
	//修改套餐
	public void updatePackageProject(long chainId, PackageProjectUpdateForm inputForm) {
		ChainPackageProjectUpdateForm updateForm = ChainPackageProjectUpdateForm.newInstance();
		updateForm.setPackageProjectUpdateForm(inputForm);
		updateForm.setUpdateType(ChainPackageProjectUpdateType.UpdatePackageProject.ordinal());
		update(chainId, updateForm);
	}
	
	//修改套餐状态
	public void updPackageProjectState(long chainId, PackageProjectUpdateStateForm inputForm) {
		ChainPackageProjectUpdateForm updateForm = ChainPackageProjectUpdateForm.newInstance();
		updateForm.setProjectUpdateStateForm(inputForm);
		updateForm.setUpdateType(ChainPackageProjectUpdateType.UpdPackageProjectState.ordinal());
		update(chainId, updateForm);
	}
	
	//批量修改套餐状态
	public void batchUpdatePackageProjectState(long chainId, PackageProjectBatchUpdateStateForm inputForm) {
		ChainPackageProjectUpdateForm updateForm = ChainPackageProjectUpdateForm.newInstance();
		updateForm.setProjectBatchUpdateStateForm(inputForm);
		updateForm.setUpdateType(ChainPackageProjectUpdateType.BatchUpdatePackageProjectState.ordinal());
		update(chainId, updateForm);
	}
	
	private void update(long chainId, ChainPackageProjectUpdateForm updateForm) {
		ChainPackageProjectDAO.getInstance().update(chainId, updateForm);
	}
	
}

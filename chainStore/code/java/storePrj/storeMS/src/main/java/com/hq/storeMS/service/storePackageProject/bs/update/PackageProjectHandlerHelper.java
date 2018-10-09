package com.hq.storeMS.service.storePackageProject.bs.update;

import java.util.HashMap;
import java.util.Map;

import com.hq.storeMS.service.auth.buser.BUserAuthUtils;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.storeClerkInfo.data.adminRole.StoreAdminPermEnum;
import com.hq.storeMS.service.storePackageProject.apiData.StorePackageProjectUpdateForm;
import com.hq.storeMS.service.storePackageProject.apiData.StorePackageProjectUpdateType;
import com.zenmind.common.hotSwap.HotSwap;

public class PackageProjectHandlerHelper {

	public static PackageProjectHandlerHelper getInstance() {
		return HotSwap.getInstance().getSingleton(PackageProjectHandlerHelper.class);
	}

	private Map<StorePackageProjectUpdateType, IPackageProjectHandler> handleMapper = new HashMap<StorePackageProjectUpdateType, IPackageProjectHandler>();

	public PackageProjectHandlerHelper() {
		handleMapper.put(StorePackageProjectUpdateType.AddPackageProjectTop, new IPackageProjectHandler(){
			@Override
			public OperateTips update(long storeId, StorePackageProjectUpdateForm updateForm){
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.PACKAGE_ADMIN);
				return StPackageProjectTopMgr.getInstance().addToTop(storeId, updateForm.getPkgPrjAddTopForm());
			}
		});
		handleMapper.put(StorePackageProjectUpdateType.CancelPackageProjectTop, new IPackageProjectHandler(){
			@Override
			public OperateTips update(long storeId, StorePackageProjectUpdateForm updateForm){
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.PACKAGE_ADMIN);
				return StPackageProjectTopMgr.getInstance().cancelTop(storeId, updateForm.getPkgPrjCancelTopForm());
			}
		});
		
		handleMapper.put(StorePackageProjectUpdateType.AddPackageProject, new IPackageProjectHandler(){
			@Override
			public OperateTips update(long storeId, StorePackageProjectUpdateForm updateForm){
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.PACKAGE_ADMIN);
				return StPackageProjectMgr.getInstance().addPackageProject(storeId, updateForm.getPackageProjectAddForm());
			}
		});
		handleMapper.put(StorePackageProjectUpdateType.RemovePackageProject, new IPackageProjectHandler(){
			@Override
			public OperateTips update(long storeId, StorePackageProjectUpdateForm updateForm){
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.PACKAGE_ADMIN);
				return StPackageProjectMgr.getInstance().removePackageProject(storeId, updateForm.getPackageProjectRemoveForm());
			}
		});
		handleMapper.put(StorePackageProjectUpdateType.UpdatePackageProject, new IPackageProjectHandler(){
			@Override
			public OperateTips update(long storeId, StorePackageProjectUpdateForm updateForm){
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.PACKAGE_ADMIN);
				return StPackageProjectMgr.getInstance().updatePackageProject(storeId, updateForm.getPackageProjectUpdateForm());
			}
		});
		handleMapper.put(StorePackageProjectUpdateType.UpdPackageProjectState, new IPackageProjectHandler(){
			@Override
			public OperateTips update(long storeId, StorePackageProjectUpdateForm updateForm){
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.PACKAGE_ADMIN);
				return StPackageProjectMgr.getInstance().updPackageProjectState(storeId, updateForm.getProjectUpdateStateForm());
			}
		});
		handleMapper.put(StorePackageProjectUpdateType.BatchUpdatePackageProjectState, new IPackageProjectHandler(){
			@Override
			public OperateTips update(long storeId, StorePackageProjectUpdateForm updateForm){
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.PACKAGE_ADMIN);
				return BatchStPackageProjectMgr.getInstance().batchUpdatePackageProjectState(storeId, updateForm.getProjectBatchUpdateStateForm());
			}
		});
		
		handleMapper.put(StorePackageProjectUpdateType.AddPackageProjectType, new IPackageProjectHandler(){
			@Override
			public OperateTips update(long storeId, StorePackageProjectUpdateForm updateForm){
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.PACKAGE_ADMIN);
				return StPackageProjectTypeMgr.getInstance().addPackageProjectType(storeId, updateForm.getPackageProjectTypeAddForm());
			}
		});
		handleMapper.put(StorePackageProjectUpdateType.RemovePackageProjectType, new IPackageProjectHandler(){
			@Override
			public OperateTips update(long storeId, StorePackageProjectUpdateForm updateForm){
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.PACKAGE_ADMIN);
				return StPackageProjectTypeMgr.getInstance().removePackageProjectType(storeId, updateForm.getPackageProjectTypeRemoveForm());
			}
		});
		handleMapper.put(StorePackageProjectUpdateType.UpdatePackageProjectType, new IPackageProjectHandler(){
			@Override
			public OperateTips update(long storeId, StorePackageProjectUpdateForm updateForm){
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.PACKAGE_ADMIN);
				return StPackageProjectTypeMgr.getInstance().updatePackageProjectType(storeId, updateForm.getPackageProjectTypeUpdateForm());
			}
		});
		handleMapper.put(StorePackageProjectUpdateType.BatchCancelChainPackage, new IPackageProjectHandler(){
			@Override
			public OperateTips update(long storeId, StorePackageProjectUpdateForm updateForm){
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.SYNDATA_ADMIN);
				return ChPackageProjectMgr.getInstance().batchCancelChainProduct(storeId, updateForm.getPackageBatchCancelForm());
			}
		});
		handleMapper.put(StorePackageProjectUpdateType.BatchPullPackageFromChain, new IPackageProjectHandler(){
			@Override
			public OperateTips update(long storeId, StorePackageProjectUpdateForm updateForm){
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.SYNDATA_ADMIN);
				return ChPackageProjectMgr.getInstance().batchAddChainPackage(storeId, updateForm.getPackageBatchPullForm());
			}
		});
		handleMapper.put(StorePackageProjectUpdateType.CancelChainPackage, new IPackageProjectHandler(){
			@Override
			public OperateTips update(long storeId, StorePackageProjectUpdateForm updateForm){
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.SYNDATA_ADMIN);
				return ChPackageProjectMgr.getInstance().cancelChainPackage(storeId, updateForm.getPackageCancelForm());
			}
		});
		handleMapper.put(StorePackageProjectUpdateType.PullPackageFromChain, new IPackageProjectHandler(){
			@Override
			public OperateTips update(long storeId, StorePackageProjectUpdateForm updateForm){
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.SYNDATA_ADMIN);
				return ChPackageProjectMgr.getInstance().addChainPackage(storeId, updateForm.getPackagePullForm());
			}
		});
	}

	public OperateTips update(long storeId, StorePackageProjectUpdateForm updateForm) {
		StorePackageProjectUpdateType updateType = StorePackageProjectUpdateType.valueOf(updateForm.getUpdateType());
		IPackageProjectHandler handle = handleMapper.get(updateType);
		if (handle!=null) {
			return handle.update(storeId, updateForm);
		}
		return OperateTips.newInstance(false, "操作失败");
	}
}

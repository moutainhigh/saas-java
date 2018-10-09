package com.hq.chainMS.service.chainPackageProject.bs.updateHandle;

import java.util.HashMap;
import java.util.Map;

import com.hq.chainMS.service.common.OperateTips;
import com.hq.chainMS.service.chainPackageProject.apiData.ChainPackageProjectUpdateForm;
import com.hq.chainMS.service.chainPackageProject.apiData.ChainPackageProjectUpdateType;
import com.zenmind.common.hotSwap.HotSwap;

public class PackageProjectHandleHelper {

	public static PackageProjectHandleHelper getInstance() {
		return HotSwap.getInstance().getSingleton(PackageProjectHandleHelper.class);
	}

	private Map<ChainPackageProjectUpdateType, IPackageProjectHandle> handleMapper = new HashMap<ChainPackageProjectUpdateType, IPackageProjectHandle>();

	public PackageProjectHandleHelper() {
		handleMapper.put(ChainPackageProjectUpdateType.AddPackageProject, new IPackageProjectHandle(){
			@Override
			public OperateTips update(long chainId, ChainPackageProjectUpdateForm updateForm){
				return PackageProjectHandle.getInstance().addPackageProject(chainId, updateForm.getPackageProjectAddForm());
			}
		});
		handleMapper.put(ChainPackageProjectUpdateType.RemovePackageProject, new IPackageProjectHandle(){
			@Override
			public OperateTips update(long chainId, ChainPackageProjectUpdateForm updateForm){
				return PackageProjectHandle.getInstance().removePackageProject(chainId, updateForm.getPackageProjectRemoveForm());
			}
		});
		handleMapper.put(ChainPackageProjectUpdateType.UpdatePackageProject, new IPackageProjectHandle(){
			@Override
			public OperateTips update(long chainId, ChainPackageProjectUpdateForm updateForm){
				return PackageProjectHandle.getInstance().updatePackageProject(chainId, updateForm.getPackageProjectUpdateForm());
			}
		});
		handleMapper.put(ChainPackageProjectUpdateType.UpdPackageProjectState, new IPackageProjectHandle(){
			@Override
			public OperateTips update(long chainId, ChainPackageProjectUpdateForm updateForm){
				return PackageProjectHandle.getInstance().updPackageProjectState(chainId, updateForm.getProjectUpdateStateForm());
			}
		});
		handleMapper.put(ChainPackageProjectUpdateType.BatchUpdatePackageProjectState, new IPackageProjectHandle(){
			@Override
			public OperateTips update(long chainId, ChainPackageProjectUpdateForm updateForm){
				return BatchPackageProjectHandle.getInstance().batchUpdatePackageProjectState(chainId, updateForm.getProjectBatchUpdateStateForm());
			}
		});
		
		handleMapper.put(ChainPackageProjectUpdateType.AddPackageProjectType, new IPackageProjectHandle(){
			@Override
			public OperateTips update(long chainId, ChainPackageProjectUpdateForm updateForm){
				return PackageProjectTypeHandle.getInstance().addPackageProjectType(chainId, updateForm.getPackageProjectTypeAddForm());
			}
		});
		handleMapper.put(ChainPackageProjectUpdateType.RemovePackageProjectType, new IPackageProjectHandle(){
			@Override
			public OperateTips update(long chainId, ChainPackageProjectUpdateForm updateForm){
				return PackageProjectTypeHandle.getInstance().removePackageProjectType(chainId, updateForm.getPackageProjectTypeRemoveForm());
			}
		});
		handleMapper.put(ChainPackageProjectUpdateType.UpdatePackageProjectType, new IPackageProjectHandle(){
			@Override
			public OperateTips update(long chainId, ChainPackageProjectUpdateForm updateForm){
				return PackageProjectTypeHandle.getInstance().updatePackageProjectType(chainId, updateForm.getPackageProjectTypeUpdateForm());
			}
		});
		
		handleMapper.put(ChainPackageProjectUpdateType.AllotStore, new IPackageProjectHandle(){
			@Override
			public OperateTips update(long chainId, ChainPackageProjectUpdateForm updateForm){
				return PackageProjectHandle.getInstance().allotStore(chainId, updateForm.getPackageProjectAllotForm());
			}
		});
		handleMapper.put(ChainPackageProjectUpdateType.BatchAllotStore, new IPackageProjectHandle(){
			@Override
			public OperateTips update(long chainId, ChainPackageProjectUpdateForm updateForm){
				return BatchPackageProjectHandle.getInstance().batchAllotStore(chainId, updateForm.getPackageProjectBatchAllotForm());
			}
		});
	}

	public OperateTips update(long chainId, ChainPackageProjectUpdateForm updateForm) {
		ChainPackageProjectUpdateType updateType = updateForm.getChainPackageProjectUpdateType();
		IPackageProjectHandle handle = handleMapper.get(updateType);
		if (handle!=null) {
			return handle.update(chainId, updateForm);
		}
		return OperateTips.newInstance(false, "操作失败");
	}
}

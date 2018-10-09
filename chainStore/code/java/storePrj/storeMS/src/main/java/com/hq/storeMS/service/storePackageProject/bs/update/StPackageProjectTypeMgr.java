package com.hq.storeMS.service.storePackageProject.bs.update;

import java.util.Collection;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import com.hq.storeMS.service.common.EntityState;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.opLog.bs.asyn.OpLogTaskMgr;
import com.hq.storeMS.service.opLog.data.OpLog;
import com.hq.storeMS.service.opLog.data.OpLogTypeEnum;
import com.hq.storeMS.service.storePackageProject.apiData.PackageProjectTypeAddForm;
import com.hq.storeMS.service.storePackageProject.apiData.PackageProjectTypeRemoveForm;
import com.hq.storeMS.service.storePackageProject.apiData.PackageProjectTypeUpdateForm;
import com.hq.storeMS.service.storePackageProject.apiData.StorePackageProjectUpdateType;
import com.hq.storeMS.service.storePackageProject.bs.StorePackageProjectMgr;
import com.hq.storeMS.service.storePackageProject.data.PackageProjectType;
import com.hq.storeMS.service.storePackageProject.data.StorePackageProject;
import com.hq.storeMS.service.storePackageProject.data.StorePackageProjectBeanHelper;
import com.zenmind.common.hotSwap.HotSwap;

public class StPackageProjectTypeMgr {
	public static StPackageProjectTypeMgr getInstance() {
		return HotSwap.getInstance().getSingleton(StPackageProjectTypeMgr.class);
	}
	
	public OperateTips addPackageProjectType(long storeId, PackageProjectTypeAddForm inputForm){
		OperateTips tips = OperateTips.newInstance(false, StorePackageProjectUpdateType.AddPackageProjectType.getMark()+"失败");
		
		StorePackageProject storeData = StorePackageProjectMgr.getInstance().getByStoreId(storeId);
		if(checkNameExists(inputForm.getName(), storeData)){
			tips.setTips("套餐分类已存在");
			return tips;
		}
		
		if(StorePackageProjectBeanHelper.getInstance().addPackageProjectType(storeData, inputForm)){
			StorePackageProjectMgr.getInstance().updateStorePackageProject(storeData);
			tips.setSuccess(true);
			addLogger(storeId, inputForm.getName(), StorePackageProjectUpdateType.AddPackageProjectType);
		}
		return tips;
	}
	
	public OperateTips removePackageProjectType(long storeId, PackageProjectTypeRemoveForm inputForm){
		OperateTips tips = OperateTips.newInstance(false,StorePackageProjectUpdateType.RemovePackageProjectType.getMark()+"失败");
		
		StorePackageProject storeData = StorePackageProjectMgr.getInstance().getByStoreId(storeId);
		if(StorePackageProjectBeanHelper.getInstance().removePackageProjectType(storeData, inputForm)){
			StorePackageProjectMgr.getInstance().updateStorePackageProject(storeData);
			tips.setSuccess(true);
			addLogger(storeId, storeData.getPackageProjectTypeMap().get(inputForm.getId()).getName(), StorePackageProjectUpdateType.RemovePackageProjectType);
		}
		return tips;
	}
	
	//修改分类
	public OperateTips updatePackageProjectType(long storeId, PackageProjectTypeUpdateForm inputForm){
		OperateTips tips = OperateTips.newInstance(false,StorePackageProjectUpdateType.UpdatePackageProjectType.getMark()+"失败");
		
		StorePackageProject storeData = StorePackageProjectMgr.getInstance().getByStoreId(storeId);
		if(checkNameExists(inputForm.getName(), storeData)){
			tips.setTips("套餐分类已存在");
			return tips;
		}
		
		if(StorePackageProjectBeanHelper.getInstance().updatePackageProjectType(storeData, inputForm)){
			StorePackageProjectMgr.getInstance().updateStorePackageProject(storeData);
			tips.setSuccess(true);
			addLogger(storeId, inputForm.getName(), StorePackageProjectUpdateType.UpdatePackageProjectType);
		}
		return tips;
	}
	
	private void addLogger(long storeId, String typeName, StorePackageProjectUpdateType updateType) {
		OpLogTaskMgr.getInstance().add(OpLog.newInstance(storeId, typeName, OpLogTypeEnum.Product, updateType.getMark()));
	}
	
	private boolean checkNameExists(String name, StorePackageProject storeData){
		Map<String, PackageProjectType> typeMap = storeData.getPackageProjectTypeMap();
		if(StringUtils.isNoneBlank(name) && MapUtils.isNotEmpty(typeMap)){
			Collection<PackageProjectType> values = typeMap.values();
			for (PackageProjectType type : values) {
				if(type.getName().equals(name) && type.getEntityState() != EntityState.Deleted.ordinal()) {
					return true;
				}
			}
		}
		return false;
	}
}

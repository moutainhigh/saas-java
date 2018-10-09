package com.hq.chainMS.service.chainPackageProject.bs.updateHandle;

import java.util.Collection;

import org.apache.commons.lang3.StringUtils;

import com.hq.chainMS.service.auth.chainUser.ChainUserAuthUtils;
import com.hq.chainMS.service.chainClerk.data.adminRole.AdminPermEnum;
import com.hq.chainMS.service.chainPackageProject.apiData.ChainPackageProjectUpdateType;
import com.hq.chainMS.service.chainPackageProject.apiData.PackageProjectTypeAddForm;
import com.hq.chainMS.service.chainPackageProject.apiData.PackageProjectTypeRemoveForm;
import com.hq.chainMS.service.chainPackageProject.apiData.PackageProjectTypeUpdateForm;
import com.hq.chainMS.service.chainPackageProject.bs.ChainPackageProjectMgr;
import com.hq.chainMS.service.chainPackageProject.data.ChainPackageProject;
import com.hq.chainMS.service.chainPackageProject.data.ChainPackageProjectBeanHelper;
import com.hq.chainMS.service.chainPackageProject.data.PackageProjectType;
import com.hq.chainMS.service.common.EntityState;
import com.hq.chainMS.service.common.OperateTips;
import com.zenmind.common.hotSwap.HotSwap;

public class PackageProjectTypeHandle {
	public static PackageProjectTypeHandle getInstance() {
		return HotSwap.getInstance().getSingleton(PackageProjectTypeHandle.class);
	}
	
	public OperateTips addPackageProjectType(long chainId, PackageProjectTypeAddForm inputForm){
		ChainUserAuthUtils.getInstance().checkPermission(chainId, AdminPermEnum.SELL_PRODUCT_ADMIN);
		OperateTips tips = OperateTips.newInstance(false, ChainPackageProjectUpdateType.AddPackageProjectType.getMark()+"失败");
		
		ChainPackageProject chainData = ChainPackageProjectMgr.getInstance().get(chainId);
		if(checkNameExists4Add(inputForm.getName(), chainData.getPackageProjectTypeMap().values())){
			tips.setTips("套餐分类已存在");
			return tips;
		}
		
		if(ChainPackageProjectBeanHelper.getInstance().addPackageProjectType(chainData, inputForm)){
			ChainPackageProjectMgr.getInstance().updateChainPackageProject(chainData);
			tips.setSuccess(true);
		}
		return tips;
	}
	
	public OperateTips removePackageProjectType(long chainId, PackageProjectTypeRemoveForm inputForm){
		ChainUserAuthUtils.getInstance().checkPermission(chainId, AdminPermEnum.SELL_PRODUCT_ADMIN);
		OperateTips tips = OperateTips.newInstance(false,ChainPackageProjectUpdateType.RemovePackageProjectType.getMark()+"失败");
		
		ChainPackageProject chainData = ChainPackageProjectMgr.getInstance().get(chainId);
		if(ChainPackageProjectBeanHelper.getInstance().removePackageProjectType(chainData, inputForm)){
			ChainPackageProjectMgr.getInstance().updateChainPackageProject(chainData);
			tips.setSuccess(true);
		}
		return tips;
	}
	
	//修改分类
	public OperateTips updatePackageProjectType(long chainId, PackageProjectTypeUpdateForm inputForm){
		ChainUserAuthUtils.getInstance().checkPermission(chainId, AdminPermEnum.SELL_PRODUCT_ADMIN);
		OperateTips tips = OperateTips.newInstance(false,ChainPackageProjectUpdateType.UpdatePackageProjectType.getMark()+"失败");
		
		ChainPackageProject chainData = ChainPackageProjectMgr.getInstance().get(chainId);
		if(checkNameExists4Update(inputForm.getName(), chainData.getPackageProjectTypeMap().values(), inputForm.getId())){
			tips.setTips("套餐分类已存在");
			return tips;
		}
		
		if(ChainPackageProjectBeanHelper.getInstance().updatePackageProjectType(chainData, inputForm)){
			ChainPackageProjectMgr.getInstance().updateChainPackageProject(chainData);
			tips.setSuccess(true);
		}
		return tips;
	}
	
	private boolean checkNameExists4Add(String name, Collection<PackageProjectType> types) {
		return checkNameExists(name, types, "");
	}

	private boolean checkNameExists4Update(String name, Collection<PackageProjectType> types, String id) {
		return checkNameExists(name, types, id);
	}

	private boolean checkNameExists(String name, Collection<PackageProjectType> types, String id) {
		if (StringUtils.isBlank(name)) {
			return false;
		}
		for (PackageProjectType type : types) {
			if (name.equals(type.getName()) && type.getEntityState() != EntityState.Deleted.ordinal() && !id.equals(type.getId())) {
				return true;
			}
		}
		return false;
	}
}

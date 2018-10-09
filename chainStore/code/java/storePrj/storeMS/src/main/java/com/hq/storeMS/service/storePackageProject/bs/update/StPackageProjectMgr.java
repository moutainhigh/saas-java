package com.hq.storeMS.service.storePackageProject.bs.update;

import java.util.Collection;

import org.apache.commons.lang3.StringUtils;

import com.hq.storeMS.common.constants.ServerConstants;
import com.hq.storeMS.service.common.EntityState;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.opLog.bs.asyn.OpLogTaskMgr;
import com.hq.storeMS.service.opLog.data.OpLog;
import com.hq.storeMS.service.opLog.data.OpLogTypeEnum;
import com.hq.storeMS.service.packageProjectDetail.bs.PackageProjectDetailMgr;
import com.hq.storeMS.service.packageProjectDetail.data.PackageProjectDetail;
import com.hq.storeMS.service.storePackageProject.apiData.PackageProjectAddForm;
import com.hq.storeMS.service.storePackageProject.apiData.PackageProjectRemoveForm;
import com.hq.storeMS.service.storePackageProject.apiData.PackageProjectUpdateForm;
import com.hq.storeMS.service.storePackageProject.apiData.PackageProjectUpdateStateForm;
import com.hq.storeMS.service.storePackageProject.apiData.StorePackageProjectUpdateType;
import com.hq.storeMS.service.storePackageProject.bs.StorePackageProjectMgr;
import com.hq.storeMS.service.storePackageProject.data.PackageProject;
import com.hq.storeMS.service.storePackageProject.data.StorePackageProject;
import com.hq.storeMS.service.storePackageProject.data.StorePackageProjectBeanHelper;
import com.hq.storeMS.service.storeVip.bs.StoreVipMgr;
import com.zenmind.common.hotSwap.HotSwap;

public class StPackageProjectMgr {

	public static StPackageProjectMgr getInstance() {
		return HotSwap.getInstance().getSingleton(StPackageProjectMgr.class);
	}

	// 添加
	public OperateTips addPackageProject(long storeId, PackageProjectAddForm inputForm) {
		OperateTips tips = OperateTips.newInstance(false, StorePackageProjectUpdateType.AddPackageProject.getMark() + "失败");
		if(StoreVipMgr.getInstance().isPackageLimited(storeId)){
			tips.setTips("当前店铺套餐数量已达上限");
			return tips;
		}
		StorePackageProject storePackageProject = StorePackageProjectMgr.getInstance().getByStoreId(storeId);
		if (checkNumberExists4Add(inputForm.getNumber(), storePackageProject.getPackageProjectMap().values())) {
			tips.setTips("编号已存在");
			return tips;
		}

		if (StorePackageProjectBeanHelper.getInstance().addPackageProject(storePackageProject, inputForm)) {
			StorePackageProjectMgr.getInstance().updateStorePackageProject(storePackageProject);
			PackageProjectDetail detail = inputForm.toPackageProjectDetail(storeId);
			PackageProjectDetailMgr.getInstance().addWithId(detail);
			tips.setSuccess(true);
			OpLogTaskMgr.getInstance().add(OpLog.newInstance(storeId, detail.getName(), OpLogTypeEnum.Product, StorePackageProjectUpdateType.AddPackageProject.getMark()));
		}
		return tips;
	}
	
	// 删除
	public OperateTips removePackageProject(long storeId, PackageProjectRemoveForm inputForm) {
		OperateTips tips = OperateTips.newInstance(false, StorePackageProjectUpdateType.RemovePackageProject.getMark() + "失败");
		StorePackageProject storePackageProject = StorePackageProjectMgr.getInstance().getByStoreId(storeId);
		
		if (StorePackageProjectBeanHelper.getInstance().removePackageProject(storePackageProject, inputForm)) {
			StorePackageProjectMgr.getInstance().updateStorePackageProject(storePackageProject);
			
			PackageProjectDetail detail = PackageProjectDetailMgr.getInstance().get(storeId, inputForm.getId());
			detail.setEntityState(EntityState.Deleted.ordinal());
			PackageProjectDetailMgr.getInstance().update(detail);
			tips.setSuccess(true);
			OpLogTaskMgr.getInstance().add(OpLog.newInstance(storeId, detail.getName(), OpLogTypeEnum.Product, StorePackageProjectUpdateType.RemovePackageProject.getMark()));
		}
		return tips;
	}
	
	// 修改基础信息
	public OperateTips updatePackageProject(long storeId, PackageProjectUpdateForm inputForm) {
		OperateTips tips = OperateTips.newInstance(false, StorePackageProjectUpdateType.UpdatePackageProject.getMark() + "失败");
		StorePackageProject storePackageProject = StorePackageProjectMgr.getInstance().getByStoreId(storeId);
		if (checkNumberExists4Update(inputForm.getNumber(), storePackageProject.getPackageProjectMap().values(), inputForm.getId())) {
			tips.setTips("编号已存在");
			return tips;
		}

		if (StorePackageProjectBeanHelper.getInstance().updatePackageProject(storePackageProject, inputForm)) {
			StorePackageProjectMgr.getInstance().updateStorePackageProject(storePackageProject);
		}
		
		PackageProjectDetail detail = PackageProjectDetailMgr.getInstance().get(storeId, inputForm.getId());
		inputForm.updatePackageProjectDetail(detail);
		PackageProjectDetailMgr.getInstance().update(detail);
		tips.setSuccess(true);
		OpLogTaskMgr.getInstance().add(OpLog.newInstance(storeId, detail.getName(), OpLogTypeEnum.Product, StorePackageProjectUpdateType.UpdatePackageProject.getMark()));
		return tips;
	}
	
	// 修改状态
	public OperateTips updPackageProjectState(long storeId, PackageProjectUpdateStateForm inputForm) {
		OperateTips tips = OperateTips.newInstance(false, StorePackageProjectUpdateType.UpdPackageProjectState.getMark() + "失败");
		StorePackageProject storePackageProject = StorePackageProjectMgr.getInstance().getByStoreId(storeId);
		
		if (StorePackageProjectBeanHelper.getInstance().updatePackageProjectState(storePackageProject, inputForm)) {
			StorePackageProjectMgr.getInstance().updateStorePackageProject(storePackageProject);
			PackageProjectDetail detail = PackageProjectDetailMgr.getInstance().get(storeId, inputForm.getId());
			detail.setState(inputForm.getState());
			PackageProjectDetailMgr.getInstance().update(detail);
			tips.setSuccess(true);
			OpLogTaskMgr.getInstance().add(OpLog.newInstance(storeId, detail.getName(), OpLogTypeEnum.Product, StorePackageProjectUpdateType.UpdPackageProjectState.getMark()));
		}
		return tips;
	}
	
	private boolean checkNumberExists4Add(String number, Collection<PackageProject> packageProjects) {
		return checkNumberExists(number, packageProjects, ServerConstants.ZERO);
	}
	
	private boolean checkNumberExists4Update(String number, Collection<PackageProject> packageProjects, String id) {
		return checkNumberExists(number, packageProjects, id);
	}
	
	private boolean checkNumberExists(String number, Collection<PackageProject> packageProjects, String id) {
		if(StringUtils.isBlank(number)) {
			return false;
		}
		for (PackageProject packageProject : packageProjects) {
			if(packageProject.getNumber().equals(number) && packageProject.getEntityState() != EntityState.Deleted.ordinal() && !packageProject.getId().equals(id)) {
				return true;
			}
		}
		return false;
	}
}

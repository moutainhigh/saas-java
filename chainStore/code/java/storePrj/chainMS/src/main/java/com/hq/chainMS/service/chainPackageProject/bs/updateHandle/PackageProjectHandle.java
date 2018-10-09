package com.hq.chainMS.service.chainPackageProject.bs.updateHandle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.hq.chainMS.common.log.LogModule;
import com.hq.chainMS.common.log.MainLog;
import com.hq.chainMS.service.auth.chainUser.ChainUserAuthUtils;
import com.hq.chainMS.service.buserMessage.apiData.ProductMessageForm;
import com.hq.chainMS.service.buserMessage.bs.BUserMessageMgr;
import com.hq.chainMS.service.chainClerk.data.adminRole.AdminPermEnum;
import com.hq.chainMS.service.chainPackageProject.apiData.ChainPackageProjectUpdateType;
import com.hq.chainMS.service.chainPackageProject.apiData.PackageProjectAddForm;
import com.hq.chainMS.service.chainPackageProject.apiData.PackageProjectAllotForm;
import com.hq.chainMS.service.chainPackageProject.apiData.PackageProjectRemoveForm;
import com.hq.chainMS.service.chainPackageProject.apiData.PackageProjectUpdateForm;
import com.hq.chainMS.service.chainPackageProject.apiData.PackageProjectUpdateStateForm;
import com.hq.chainMS.service.chainPackageProject.bs.ChainPackageProjectMgr;
import com.hq.chainMS.service.chainPackageProject.bs.PackageProjectDetailMgr;
import com.hq.chainMS.service.chainPackageProject.data.ChainPackageProject;
import com.hq.chainMS.service.chainPackageProject.data.ChainPackageProjectBeanHelper;
import com.hq.chainMS.service.chainPackageProject.data.PackageProject;
import com.hq.chainMS.service.chainPackageProject.data.PackageProjectDetail;
import com.hq.chainMS.service.chainPackageProject.data.PackageStateEnum;
import com.hq.chainMS.service.common.EntityState;
import com.hq.chainMS.service.common.OperateTips;
import com.hq.chainMS.service.storeChain.bs.StoreChainMgr;
import com.hq.storeClient.service.storeChain.apiData.StoreChainUpdateStatusForm;
import com.hq.storeClient.service.storeChain.data.StoreChainItemType;
import com.hq.storeClient.service.storeChain.data.StoreChainStatus;
import com.zenmind.common.hotSwap.HotSwap;

public class PackageProjectHandle {

	public static PackageProjectHandle getInstance() {
		return HotSwap.getInstance().getSingleton(PackageProjectHandle.class);
	}

	// 添加
	public OperateTips addPackageProject(long chainId, PackageProjectAddForm inputForm) {
		ChainUserAuthUtils.getInstance().checkPermission(chainId, AdminPermEnum.SELL_PRODUCT_ADMIN);
		OperateTips tips = OperateTips.newInstance(false, ChainPackageProjectUpdateType.AddPackageProject.getMark() + "失败");
		ChainPackageProject chainPackageProject = ChainPackageProjectMgr.getInstance().get(chainId);

		if (checkNumberExists4Add(inputForm.getNumber(), chainPackageProject.getPackageProjectMap().values())) {
			tips.setTips("编号已存在");
			return tips;
		}

		if (ChainPackageProjectBeanHelper.getInstance().addPackageProject(chainPackageProject, inputForm)) {
			ChainPackageProjectMgr.getInstance().updateChainPackageProject(chainPackageProject);

			PackageProjectDetail detail = inputForm.toPackageProjectDetail(chainId);
			PackageProjectDetailMgr.getInstance().addWithId(detail);
			tips.setSuccess(true);
		}
		return tips;
	}

	// 删除
	public OperateTips removePackageProject(long chainId, PackageProjectRemoveForm inputForm) {
		ChainUserAuthUtils.getInstance().checkPermission(chainId, AdminPermEnum.SELL_PRODUCT_ADMIN);
		OperateTips tips = OperateTips.newInstance(false, ChainPackageProjectUpdateType.RemovePackageProject.getMark() + "失败");
		ChainPackageProject chainPackageProject = ChainPackageProjectMgr.getInstance().get(chainId);

		if (ChainPackageProjectBeanHelper.getInstance().removePackageProject(chainPackageProject, inputForm)) {
			ChainPackageProjectMgr.getInstance().updateChainPackageProject(chainPackageProject);

			PackageProjectDetail detail = PackageProjectDetailMgr.getInstance().get(chainId, inputForm.getId());
			detail.setEntityState(EntityState.Deleted.ordinal());
			PackageProjectDetailMgr.getInstance().update(detail);
			tips.setSuccess(true);
		}
		return tips;
	}

	// 修改基础信息
	public OperateTips updatePackageProject(long chainId, PackageProjectUpdateForm inputForm) {
		ChainUserAuthUtils.getInstance().checkPermission(chainId, AdminPermEnum.SELL_PRODUCT_ADMIN);
		OperateTips tips = OperateTips.newInstance(false, ChainPackageProjectUpdateType.UpdatePackageProject.getMark() + "失败");
		ChainPackageProject chainPackageProject = ChainPackageProjectMgr.getInstance().get(chainId);

		if (checkNumberExists4Update(inputForm.getNumber(), chainPackageProject.getPackageProjectMap().values(), inputForm.getId())) {
			tips.setTips("编号已存在");
			return tips;
		}

		if (ChainPackageProjectBeanHelper.getInstance().updatePackageProject(chainPackageProject, inputForm)) {
			ChainPackageProjectMgr.getInstance().updateChainPackageProject(chainPackageProject);
		}

		PackageProjectDetail detail = PackageProjectDetailMgr.getInstance().get(chainId, inputForm.getId());
		inputForm.updatePackageProjectDetail(detail);
		PackageProjectDetailMgr.getInstance().update(detail);
		
		ProductMessageForm form = ProductMessageForm.newInstance(detail.getId(), detail.getName(), detail.getApplyStoreIds());
		BUserMessageMgr.getInstance().updateChainProduct(chainId, form);
		tips.setSuccess(true);
		return tips;
	}

	// 修改状态
	public OperateTips updPackageProjectState(long chainId, PackageProjectUpdateStateForm inputForm) {
		ChainUserAuthUtils.getInstance().checkPermission(chainId, AdminPermEnum.SELL_PRODUCT_ADMIN);
		OperateTips tips = OperateTips.newInstance(false, ChainPackageProjectUpdateType.UpdPackageProjectState.getMark() + "失败");
		ChainPackageProject chainPackageProject = ChainPackageProjectMgr.getInstance().get(chainId);

		if (ChainPackageProjectBeanHelper.getInstance().updatePackageProjectState(chainPackageProject, inputForm)) {
			ChainPackageProjectMgr.getInstance().updateChainPackageProject(chainPackageProject);

			PackageProjectDetail detail = PackageProjectDetailMgr.getInstance().get(chainId, inputForm.getId());
			detail.setState(inputForm.getState());
			PackageProjectDetailMgr.getInstance().update(detail);
			
			if(inputForm.getState() == PackageStateEnum.Close.ordinal()) {//下架操作
				updateStoreDataClose(chainId, detail.getApplyStoreIds(), inputForm.getId());
			}else {
				ProductMessageForm form = ProductMessageForm.newInstance(detail.getId(), detail.getName(), detail.getApplyStoreIds());
				BUserMessageMgr.getInstance().openChainProduct(chainId, form);
			}
			
			tips.setSuccess(true);
		}
		return tips;
	}
	
	private void updateStoreDataClose(long chainId, Set<Long> applyStoreIds, String id) {
		try {
			List<StoreChainUpdateStatusForm> updateStatusForms = new ArrayList<StoreChainUpdateStatusForm>();
			for (Long storeId : applyStoreIds) {
				StoreChainUpdateStatusForm form = StoreChainUpdateStatusForm.newInstance();
				form.setStatus(StoreChainStatus.Close.ordinal());
				form.setId(id);
				form.setItemType(StoreChainItemType.PackagePrj.ordinal());
				form.setStoreId(storeId);
				updateStatusForms.add(form);
			}
			StoreChainMgr.getInstance().batchUpdateState(String.valueOf(chainId), updateStatusForms);	
		} catch (Exception e) {
			MainLog.error(LogModule.ChainPackageProject, "PackageProjectHandle[updateStoreDataClose]", "", e);
		}
	}
	
	// 分配
	public OperateTips allotStore(long chainId, PackageProjectAllotForm inputForm) {
		ChainUserAuthUtils.getInstance().checkPermission(chainId, AdminPermEnum.SELL_PRODUCT_ADMIN);
		OperateTips tips = OperateTips.newInstance(false, ChainPackageProjectUpdateType.AllotStore.getMark() + "失败");
		ChainPackageProject chainPackageProject = ChainPackageProjectMgr.getInstance().get(chainId);
		
		inputForm.getApplyStoreIds().remove(null);
		if (ChainPackageProjectBeanHelper.getInstance().allotStore(chainPackageProject, inputForm)) {
			ChainPackageProjectMgr.getInstance().updateChainPackageProject(chainPackageProject);
			
			PackageProjectDetail detail = PackageProjectDetailMgr.getInstance().get(chainId, inputForm.getId());
			detail.setApplyStoreIds(inputForm.getApplyStoreIds());
			PackageProjectDetailMgr.getInstance().update(detail);
			tips.setSuccess(true);
		}
		return tips;
	}

	private boolean checkNumberExists4Add(String number, Collection<PackageProject> packageProjects) {
		return checkNumberExists(number, packageProjects, "");
	}

	private boolean checkNumberExists4Update(String number, Collection<PackageProject> packageProjects, String id) {
		return checkNumberExists(number, packageProjects, id);
	}

	private boolean checkNumberExists(String number, Collection<PackageProject> packageProjects, String id) {
		if (StringUtils.isBlank(number)) {
			return false;
		}
		for (PackageProject packageProject : packageProjects) {
			if (packageProject.getNumber().equals(number)
					&& packageProject.getEntityState() != EntityState.Deleted.ordinal()
					&& !packageProject.getId().equals(id)) {
				return true;
			}
		}
		return false;
	}
}

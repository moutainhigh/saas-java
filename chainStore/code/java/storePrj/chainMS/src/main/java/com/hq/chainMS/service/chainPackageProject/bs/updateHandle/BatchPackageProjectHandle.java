package com.hq.chainMS.service.chainPackageProject.bs.updateHandle;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.hq.chainMS.service.auth.chainUser.ChainUserAuthUtils;
import com.hq.chainMS.service.chainClerk.data.adminRole.AdminPermEnum;
import com.hq.chainMS.service.chainPackageProject.apiData.ChainPackageProjectUpdateType;
import com.hq.chainMS.service.chainPackageProject.apiData.PackageProjectAllotForm;
import com.hq.chainMS.service.chainPackageProject.apiData.PackageProjectBatchAllotForm;
import com.hq.chainMS.service.chainPackageProject.apiData.PackageProjectBatchUpdateStateForm;
import com.hq.chainMS.service.chainPackageProject.apiData.PackageProjectUpdateStateForm;
import com.hq.chainMS.service.chainPackageProject.bs.ChainPackageProjectMgr;
import com.hq.chainMS.service.chainPackageProject.data.ChainPackageProject;
import com.hq.chainMS.service.chainPackageProject.data.PackageProject;
import com.hq.chainMS.service.common.OperateTips;
import com.zenmind.common.hotSwap.HotSwap;

public class BatchPackageProjectHandle {

	public static BatchPackageProjectHandle getInstance() {
		return HotSwap.getInstance().getSingleton(BatchPackageProjectHandle.class);
	}

	// 批量修改状态
	public OperateTips batchUpdatePackageProjectState(long chainId, PackageProjectBatchUpdateStateForm inputForm) {
		ChainUserAuthUtils.getInstance().checkPermission(chainId, AdminPermEnum.SELL_PRODUCT_ADMIN);
		OperateTips tips = OperateTips.newInstance(false, ChainPackageProjectUpdateType.BatchUpdatePackageProjectState.getMark() + "失败");

		ChainPackageProject chainPackageProject = ChainPackageProjectMgr.getInstance().get(chainId);
		List<String> tipsInfo = new ArrayList<String>();
		boolean flag = false;
		List<PackageProjectUpdateStateForm> list = inputForm.toPackageProjectUpdateStateFormList();
		for (PackageProjectUpdateStateForm updateStateForm : list) {
			OperateTips optips = PackageProjectHandle.getInstance().updPackageProjectState(chainId, updateStateForm);
			if (!optips.isSuccess()) {
				flag = true;
				tipsInfo.add(chainPackageProject.takePackageProjectById(updateStateForm.getId()).getName());
			}
		}
		tips.setSuccess(true);
		if (flag) {
			tips.setTips("以下套餐更新失败：[" + StringUtils.join(tipsInfo, ",") + "]");
		}
		return tips;
	}
	
	// 批量分配
	public OperateTips batchAllotStore(long chainId, PackageProjectBatchAllotForm inputForm) {
		ChainUserAuthUtils.getInstance().checkPermission(chainId, AdminPermEnum.SELL_PRODUCT_ADMIN);
		OperateTips tips = OperateTips.newInstance(false, ChainPackageProjectUpdateType.BatchAllotStore.getMark() + "失败");
		
		ChainPackageProject chainPackageProject = ChainPackageProjectMgr.getInstance().get(chainId);
		List<String> tipsInfo = new ArrayList<String>();
		boolean flag = false;
		List<PackageProjectAllotForm> list = inputForm.getPackageProjectAllotForms();
		for (PackageProjectAllotForm form : list) {
			//批量分配  将现有的和原来的storeIds合并
			PackageProject data = chainPackageProject.takePackageProjectById(form.getId());
			if(data==null) {
				continue;
			}
			form.getApplyStoreIds().addAll(data.getApplyStoreIds());
			OperateTips optips = PackageProjectHandle.getInstance().allotStore(chainId, form);
			if (!optips.isSuccess()) {
				flag = true;
				tipsInfo.add(data.getName());
			}
		}
		tips.setSuccess(true);
		if (flag) {
			tips.setTips("以下套餐分配失败：[" + StringUtils.join(tipsInfo, ",") + "]");
		}
		return tips;
	}
}

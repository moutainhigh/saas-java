package com.hq.storeMS.service.storePackageProject.bs.update;

import java.util.Set;

import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.packageProjectDetail.bs.PackageProjectDetailMgr;
import com.hq.storeMS.service.packageProjectDetail.data.PackageProjectDetail;
import com.hq.storeMS.service.storePackageProject.apiData.PackageProjectBatchUpdateStateForm;
import com.hq.storeMS.service.storePackageProject.apiData.StorePackageProjectUpdateType;
import com.hq.storeMS.service.storePackageProject.bs.StorePackageProjectMgr;
import com.hq.storeMS.service.storePackageProject.data.StorePackageProject;
import com.hq.storeMS.service.storePackageProject.data.StorePackageProjectBeanHelper;
import com.zenmind.common.hotSwap.HotSwap;

public class BatchStPackageProjectMgr {

	public static BatchStPackageProjectMgr getInstance() {
		return HotSwap.getInstance().getSingleton(BatchStPackageProjectMgr.class);
	}

	// 批量修改状态
	public OperateTips batchUpdatePackageProjectState(long storeId, PackageProjectBatchUpdateStateForm inputForm) {
		OperateTips tips = OperateTips.newInstance(false, StorePackageProjectUpdateType.BatchUpdatePackageProjectState.getMark() + "失败");
		StorePackageProject storePackageProject = StorePackageProjectMgr.getInstance().getByStoreId(storeId);
		
		if (StorePackageProjectBeanHelper.getInstance().batchUpdatePackageProjectState(storePackageProject, inputForm)) {
			StorePackageProjectMgr.getInstance().updateStorePackageProject(storePackageProject);
			
			Set<String> idSet = inputForm.getIdSet();
			for (String id : idSet) {
				PackageProjectDetail detail = PackageProjectDetailMgr.getInstance().get(storeId, id);
				detail.setState(inputForm.getState());
				PackageProjectDetailMgr.getInstance().update(detail);
			}
			tips.setSuccess(true);
		}
		return tips;
	}
}

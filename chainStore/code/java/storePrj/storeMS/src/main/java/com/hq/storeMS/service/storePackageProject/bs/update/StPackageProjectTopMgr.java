package com.hq.storeMS.service.storePackageProject.bs.update;

import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.common.TopFlagEnum;
import com.hq.storeMS.service.packageProjectDetail.bs.PackageProjectDetailMgr;
import com.hq.storeMS.service.packageProjectDetail.data.PackageProjectDetail;
import com.hq.storeMS.service.storePackageProject.apiData.PkgPrjAddTopForm;
import com.hq.storeMS.service.storePackageProject.apiData.PkgPrjCancelTopForm;
import com.hq.storeMS.service.storePackageProject.apiData.StorePackageProjectUpdateType;
import com.hq.storeMS.service.storePackageProject.bs.StorePackageProjectMgr;
import com.hq.storeMS.service.storePackageProject.data.PackageProject;
import com.hq.storeMS.service.storePackageProject.data.StorePackageProject;
import com.zenmind.common.hotSwap.HotSwap;

public class StPackageProjectTopMgr {
	public static StPackageProjectTopMgr getInstance() {
		return HotSwap.getInstance().getSingleton(StPackageProjectTopMgr.class);
	}
	
	//置顶
	public OperateTips addToTop(long storeId, PkgPrjAddTopForm inputData){
		return operdateTop(storeId, StorePackageProjectUpdateType.AddPackageProjectTop, inputData.getId());
	}
	
	//取消置顶
	public OperateTips cancelTop(long storeId, PkgPrjCancelTopForm inputData){
		return operdateTop(storeId, StorePackageProjectUpdateType.CancelPackageProjectTop, inputData.getId());
	}
	
	private OperateTips operdateTop(long storeId, StorePackageProjectUpdateType updateType, String id) {
		OperateTips tips = OperateTips.newInstance(false, updateType.getMark()+"失败");
		StorePackageProject storeData = StorePackageProjectMgr.getInstance().getByStoreId(storeId);
		PackageProject data = storeData.getPackageProjectMap().get(id);
		if(data != null) {
			PackageProjectDetail detail = PackageProjectDetailMgr.getInstance().get(storeId, id);
			if(updateType==StorePackageProjectUpdateType.AddPackageProjectTop) {
				data.setTopFlag(TopFlagEnum.Top.ordinal());
				detail.setTopFlag(TopFlagEnum.Top.ordinal());
			}else if(updateType==StorePackageProjectUpdateType.CancelPackageProjectTop) {
				data.setTopFlag(TopFlagEnum.Normal.ordinal());
				detail.setTopFlag(TopFlagEnum.Normal.ordinal());
			}
			StorePackageProjectMgr.getInstance().updateStorePackageProject(storeData);
			PackageProjectDetailMgr.getInstance().update(detail);
			tips.setSuccess(true);
		}else {
			tips.setTips("套餐不存在");
		}
		return tips;
	}
}

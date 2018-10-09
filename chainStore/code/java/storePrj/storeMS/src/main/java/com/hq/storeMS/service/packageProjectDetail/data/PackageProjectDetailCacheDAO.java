package com.hq.storeMS.service.packageProjectDetail.data;

import java.util.List;

import com.hq.storeMS.common.util.AppUtils;
import com.hq.storeMS.service.packageProjectDetail.apiData.PackageProjectDetailQueryForm;
import com.zenmind.common.hotSwap.HotSwap;

public class PackageProjectDetailCacheDAO {

	public static PackageProjectDetailCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(PackageProjectDetailCacheDAO.class);
	}

	final private String suffix = "storePackageProjectDetail";

	public void saveList(PackageProjectDetailQueryForm queryForm, List<PackageProjectDetail> list) {
		PackageProjectDetailRedisDAO.getInstance().saveList(list, getGroupName(queryForm.getStoreId()), queryForm.getListId());
	}

	public List<PackageProjectDetail> getList(PackageProjectDetailQueryForm queryForm) {
		return PackageProjectDetailRedisDAO.getInstance().getList(getGroupName(queryForm.getStoreId()), queryForm.getListId());
	}
	
	public void save(PackageProjectDetail target) {
		PackageProjectDetailRedisDAO.getInstance().saveOne(getGroupName(target.getStoreId()), target.getId(), target);
	}
	
	public PackageProjectDetail get(long storeId, String id) {
		return PackageProjectDetailRedisDAO.getInstance().findByOne(getGroupName(storeId), id);
	}

	public void delete(PackageProjectDetail target) {
		PackageProjectDetailRedisDAO.getInstance().delete(target.getId());
		PackageProjectDetailRedisDAO.getInstance().deleteList(getGroupName(target.getStoreId()));
	}

	private String getGroupName(Object storeId) {
		return AppUtils.joinByUnderline(suffix, storeId);
	}
}

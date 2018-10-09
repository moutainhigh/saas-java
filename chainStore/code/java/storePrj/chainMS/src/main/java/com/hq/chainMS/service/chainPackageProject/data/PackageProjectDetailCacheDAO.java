package com.hq.chainMS.service.chainPackageProject.data;

import java.util.List;

import com.hq.chainMS.common.util.AppUtils;
import com.hq.chainMS.service.chainPackageProject.apiData.PackageProjectDetailQueryForm;
import com.zenmind.common.hotSwap.HotSwap;

public class PackageProjectDetailCacheDAO {

	public static PackageProjectDetailCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(PackageProjectDetailCacheDAO.class);
	}

	final private String suffix = "chainPackageProjectDetail";

	public void saveList(PackageProjectDetailQueryForm queryForm, List<PackageProjectDetail> list) {
		PackageProjectDetailRedisDAO.getInstance().saveList(list, getGroupName(queryForm.getChainId()), queryForm.getListId());
	}

	public List<PackageProjectDetail> getList(PackageProjectDetailQueryForm queryForm) {
		return PackageProjectDetailRedisDAO.getInstance().getList(getGroupName(queryForm.getChainId()), queryForm.getListId());
	}
	
	public void save(PackageProjectDetail target) {
		PackageProjectDetailRedisDAO.getInstance().saveOne(getGroupName(target.getChainId()), target.getId(), target);
	}
	
	public PackageProjectDetail get(long chainId, String id) {
		return PackageProjectDetailRedisDAO.getInstance().findByOne(getGroupName(chainId), id);
	}

	public void delete(PackageProjectDetail target) {
		PackageProjectDetailRedisDAO.getInstance().delete(target.getId());
		PackageProjectDetailRedisDAO.getInstance().deleteList(getGroupName(target.getChainId()));
	}

	private String getGroupName(long chainId) {
		return AppUtils.joinByUnderline(suffix, chainId);
	}
}

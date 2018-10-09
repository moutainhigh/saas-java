package com.hq.storeMS.service.chainPackageProject.data;

import com.hq.chainClient.service.chainPackageProject.data.PackageProjectDetail;
import com.hq.storeMS.common.util.AppUtils;
import com.zenmind.common.hotSwap.HotSwap;

public class PackageProjectDetailCacheDAO {

	public static PackageProjectDetailCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(PackageProjectDetailCacheDAO.class);
	}

	final private String suffix = "chainPackageProjectDetail";

	public PackageProjectDetail get(long chainId, String id) {
		return PackageProjectDetailRedisDAO.getInstance().findByOne(getGroupName(chainId), id);
	}

	private String getGroupName(long chainId) {
		return AppUtils.joinByUnderline(suffix, chainId);
	}
}

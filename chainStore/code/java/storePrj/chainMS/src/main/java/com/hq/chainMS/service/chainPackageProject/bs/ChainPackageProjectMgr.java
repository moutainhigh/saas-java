package com.hq.chainMS.service.chainPackageProject.bs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.hq.chainMS.service.chainPackageProject.data.ChainPackageProject;
import com.hq.chainMS.service.chainPackageProject.data.PackageProject;
import com.hq.chainMS.service.common.EntityState;
import com.hq.chainMS.service.sellProduct.data.SellProduct;
import com.zenmind.common.hotSwap.HotSwap;

public class ChainPackageProjectMgr {

	public static ChainPackageProjectMgr getInstance() {
		return HotSwap.getInstance().getSingleton(ChainPackageProjectMgr.class);
	}

	public void updateChainPackageProject(ChainPackageProject chainPackageProject) {
		ChainPackageProjectDataHolder.getInstance().update(chainPackageProject);
	}
	
	public List<SellProduct> getSellProducts(long chainId){
		List<SellProduct> result = new ArrayList<SellProduct>();
		ChainPackageProject chainData = get(chainId);
		Collection<PackageProject> array = chainData.getPackageProjectMap().values();
		for (PackageProject data : array) {
			if(data.getEntityState() == EntityState.Deleted.ordinal()) {
				continue;
			}
			result.add(data.toSellProduct());
		}
		return result;
	} 
	
	public ChainPackageProject get(long chainId) {
		ChainPackageProject info = ChainPackageProjectDataHolder.getInstance().get(chainId);
		if(info == null){//不存在，则新增
			info = ChainPackageProject.newInstance(chainId);
			ChainPackageProjectDataHolder.getInstance().addWithId(info);
		}
		return info;
	}
}

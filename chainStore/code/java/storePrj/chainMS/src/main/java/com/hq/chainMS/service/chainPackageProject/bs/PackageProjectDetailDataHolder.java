package com.hq.chainMS.service.chainPackageProject.bs;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.hq.chainMS.common.validate.ValidateInfoThreadLocal;
import com.hq.chainMS.service.chain.bs.ChainDataHolder;
import com.hq.chainMS.service.chain.data.Chain;
import com.hq.chainMS.service.chainPackageProject.apiData.PackageProjectDetailQueryForm;
import com.hq.chainMS.service.chainPackageProject.data.PackageProjectDetail;
import com.hq.chainMS.service.chainPackageProject.data.PackageProjectDetailCacheDAO;
import com.hq.chainMS.service.chainPackageProject.data.PackageProjectDetailDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class PackageProjectDetailDataHolder {
	
	public static PackageProjectDetailDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(PackageProjectDetailDataHolder.class);
	}
	
	public void addWithId(PackageProjectDetail target) {
		PackageProjectDetailDAO.getInstance().addWithId(getBossId(target.getChainId()), target);
		PackageProjectDetailCacheDAO.getInstance().delete(target);
	}

	public void updpate(PackageProjectDetail target) {
		target.incrVer();
		target.setLastUpdateTime(System.currentTimeMillis());
		PackageProjectDetailDAO.getInstance().updpate(getBossId(target.getChainId()), target);
		PackageProjectDetailCacheDAO.getInstance().delete(target);
	}
	
	public void delete(PackageProjectDetail target) {
		PackageProjectDetailDAO.getInstance().delete(getBossId(target.getChainId()), target.getId());
		PackageProjectDetailCacheDAO.getInstance().delete(target);
	}
	
	public PackageProjectDetail get(long chainId, String id) {
		PackageProjectDetail target = PackageProjectDetailCacheDAO.getInstance().get(chainId, id);
		if(target == null){
			target = PackageProjectDetailDAO.getInstance().get(getBossId(chainId), id);
			if(target != null){
				PackageProjectDetailCacheDAO.getInstance().save(target);
			}
		}
		return target;
	}
	
	public List<PackageProjectDetail> findPackageProjectDetailList(PackageProjectDetailQueryForm queryForm) {
		List<PackageProjectDetail> list = PackageProjectDetailCacheDAO.getInstance().getList(queryForm);
		if(CollectionUtils.isEmpty(list)){
			list = PackageProjectDetailDAO.getInstance().findPackageProjectDetailList(getBossId(queryForm.getChainId()), queryForm);
			if(CollectionUtils.isNotEmpty(list)){
				PackageProjectDetailCacheDAO.getInstance().saveList(queryForm, list);
			}
		}
		return list;
	}
	
	private long getBossId(long chainId) {
		long bossId = ValidateInfoThreadLocal.getInstance().getBossId();
		if(bossId == 0) {
			Chain chain = ChainDataHolder.getInstance().get(chainId);
			if(chain!=null) {
				bossId = chain.getBossId();
			}
		}
		return bossId;
	}
}

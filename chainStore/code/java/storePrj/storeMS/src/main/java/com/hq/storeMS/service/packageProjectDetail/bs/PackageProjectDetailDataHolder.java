package com.hq.storeMS.service.packageProjectDetail.bs;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.hq.storeMS.service.packageProjectDetail.apiData.PackageProjectDetailQueryForm;
import com.hq.storeMS.service.packageProjectDetail.data.PackageProjectDetail;
import com.hq.storeMS.service.packageProjectDetail.data.PackageProjectDetailCacheDAO;
import com.hq.storeMS.service.packageProjectDetail.data.PackageProjectDetailDAO;
import com.hq.storeMS.service.store.bs.BossDataHolder;
import com.zenmind.common.hotSwap.HotSwap;

public class PackageProjectDetailDataHolder {
	
	public static PackageProjectDetailDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(PackageProjectDetailDataHolder.class);
	}
	
	public void addWithId(PackageProjectDetail target) {
		PackageProjectDetailDAO.getInstance().addWithId(getBossId(target.getStoreId()), target);
		PackageProjectDetailCacheDAO.getInstance().delete(target);
	}

	public void updpate(PackageProjectDetail target) {
		target.incrVer();
		target.setLastUpdateTime(System.currentTimeMillis());
		PackageProjectDetailDAO.getInstance().updpate(getBossId(target.getStoreId()), target);
		PackageProjectDetailCacheDAO.getInstance().delete(target);
	}
	
	public void delete(PackageProjectDetail target) {
		PackageProjectDetailDAO.getInstance().delete(getBossId(target.getStoreId()), target.getId());
		PackageProjectDetailCacheDAO.getInstance().delete(target);
	}
	
	public PackageProjectDetail get(long storeId, String id) {
		PackageProjectDetail target = PackageProjectDetailCacheDAO.getInstance().get(storeId, id);
		if(target == null){
			target = PackageProjectDetailDAO.getInstance().get(getBossId(storeId), id);
			if(target != null){
				PackageProjectDetailCacheDAO.getInstance().save(target);
			}
		}
		return target;
	}
	
	public List<PackageProjectDetail> findPackageProjectDetailList(PackageProjectDetailQueryForm queryForm) {
		List<PackageProjectDetail> list = PackageProjectDetailCacheDAO.getInstance().getList(queryForm);
		if(CollectionUtils.isEmpty(list)){
			list = PackageProjectDetailDAO.getInstance().findPackageProjectDetailList(getBossId(queryForm.getStoreId()), queryForm);
			if(CollectionUtils.isNotEmpty(list)){
				PackageProjectDetailCacheDAO.getInstance().saveList(queryForm, list);
			}
		}
		return list;
	}
	
	private long getBossId(long storeId) {
		return BossDataHolder.getInstance().getBossId(storeId);
	}
}

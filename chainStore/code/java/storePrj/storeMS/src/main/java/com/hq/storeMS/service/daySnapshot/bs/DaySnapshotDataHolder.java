package com.hq.storeMS.service.daySnapshot.bs;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.hq.storeMS.service.daySnapshot.apiData.DaySnapshotQueryForm;
import com.hq.storeMS.service.daySnapshot.data.DaySnapshot;
import com.hq.storeMS.service.daySnapshot.data.DaySnapshotCacheDAO;
import com.hq.storeMS.service.daySnapshot.data.DaySnapshotDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class DaySnapshotDataHolder {
	
	public static DaySnapshotDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(DaySnapshotDataHolder.class);
	}
	
	public void addAndReturnId(DaySnapshot target) {
		DaySnapshotDAO.getInstance().addAndReturnId(target);
		DaySnapshotCacheDAO.getInstance().delete(target);
	}

	public void updpate(DaySnapshot target) {
		target.incrVer();
		target.setLastUpdateTime(System.currentTimeMillis());
		DaySnapshotDAO.getInstance().updpate(target);
		DaySnapshotCacheDAO.getInstance().delete(target);
	}
	
	public void delete(DaySnapshot target) {
		DaySnapshotDAO.getInstance().delete(target.getId());
		DaySnapshotCacheDAO.getInstance().delete(target);
	}
	
	public DaySnapshot get(long id) {
		DaySnapshot target = DaySnapshotCacheDAO.getInstance().get(id);
		if(target == null){
			target = DaySnapshotDAO.getInstance().get(id);
			if(target != null){
				DaySnapshotCacheDAO.getInstance().save(target);
			}
		}
		return target;
	}
	
	public List<DaySnapshot> findList(DaySnapshotQueryForm queryForm) {
		List<DaySnapshot> list = DaySnapshotCacheDAO.getInstance().getList(queryForm);
		if(CollectionUtils.isEmpty(list)){
			list = DaySnapshotDAO.getInstance().findList(queryForm);
			if(CollectionUtils.isNotEmpty(list)){
				DaySnapshotCacheDAO.getInstance().saveList(queryForm, list);
			}
		}
		return list;
	}
}

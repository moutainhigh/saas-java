package com.hq.storeMS.service.storePackageProject.bs;

import org.apache.commons.lang3.math.NumberUtils;

import com.hq.storeMS.common.datasyn.IntfDataHolder;
import com.hq.storeMS.common.datasyn.info.DataSynItem;
import com.hq.storeMS.common.datasyn.info.DataSynType;
import com.hq.storeMS.common.datasyn.info.DataSynVer;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.service.storePackageProject.data.StorePackageProject;
import com.hq.storeMS.service.storePackageProject.data.StorePackageProjectCacheDAO;
import com.hq.storeMS.service.storePackageProject.data.StorePackageProjectDAO;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dataSyn.DataSynMgr;

public class StorePackageProjectDataHolder implements IntfDataHolder {

	public static StorePackageProjectDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(StorePackageProjectDataHolder.class);
	}

	final private DataSynType synType = DataSynType.StorePackageProject;

	public void addWithId(StorePackageProject target) {
		StorePackageProjectDAO.getInstance().addWithId(target);
	}

	public void update(StorePackageProject target) {
		target.incrVer();
		target.setLastUpdateTime(System.currentTimeMillis());
		StorePackageProjectDAO.getInstance().updpate(target);
		StorePackageProjectCacheDAO.getInstance().delete(target);
	}

	public void delete(StorePackageProject target) {
		StorePackageProjectDAO.getInstance().delete(target.getId());
		StorePackageProjectCacheDAO.getInstance().delete(target);
	}

	public StorePackageProject get(long id) {
		StorePackageProject target = StorePackageProjectCacheDAO.getInstance().get(id);
		if(target == null){
			target = StorePackageProjectDAO.getInstance().get(id);
			if(target != null){
				StorePackageProjectCacheDAO.getInstance().save(target);
			}
		}
		return target;
	}
	
	public DataSynType getSynType() {
		return synType;
	}

	public DataSynItem getSynItem(DataSynVer clientSynVer) {
		DataSynItem item = null;
		String id = clientSynVer.getId();
		if(NumberUtils.isNumber( id )){
			StorePackageProject target = this.get(Long.valueOf(id));
			if(target != null){
				long newVer = target.getVer();
				if (clientSynVer.getVer() < newVer) {
					String data = DataSynMgr.getInstance().toClientData(target);
					item = DataSynItem.newInstance(clientSynVer, newVer, data);
				}
			}else{
				MainLog.error(LogModule.StorePackageProject, "StorePackageProjectDataHolder[getSynItem]", "获取详情对象为空[id="+id+"]");
			}
		}else{
			MainLog.error(LogModule.StorePackageProject, "StorePackageProjectDataHolder[getSynItem]", "数据同步失败[id="+id+"]");
		}
		return item;
	}
}

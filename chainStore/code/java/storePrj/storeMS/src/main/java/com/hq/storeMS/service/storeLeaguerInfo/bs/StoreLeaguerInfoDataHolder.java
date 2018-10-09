package com.hq.storeMS.service.storeLeaguerInfo.bs;

import org.apache.commons.lang3.math.NumberUtils;

import com.hq.storeMS.common.datasyn.IntfDataHolder;
import com.hq.storeMS.common.datasyn.info.DataSynItem;
import com.hq.storeMS.common.datasyn.info.DataSynType;
import com.hq.storeMS.common.datasyn.info.DataSynVer;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.service.storeLeaguerInfo.data.StoreLeaguerInfo;
import com.hq.storeMS.service.storeLeaguerInfo.data.StoreLeaguerInfoCacheDAO;
import com.hq.storeMS.service.storeLeaguerInfo.data.StoreLeaguerInfoDAO;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dataSyn.DataSynMgr;

public class StoreLeaguerInfoDataHolder implements IntfDataHolder {

	public static StoreLeaguerInfoDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(StoreLeaguerInfoDataHolder.class);
	}

	final private DataSynType synType = DataSynType.StoreLeaguerInfo;

	public void addWithId(StoreLeaguerInfo target) {
		StoreLeaguerInfoDAO.getInstance().addWithId(target);
	}

	public void updpate(StoreLeaguerInfo target) {
		target.incrVer();
		target.setLastUpdateTime(System.currentTimeMillis());
		StoreLeaguerInfoDAO.getInstance().updpate(target);
		StoreLeaguerInfoCacheDAO.getInstance().delete(target);
	}

	public void delete(StoreLeaguerInfo target) {
		StoreLeaguerInfoDAO.getInstance().delete(target.getId());
		StoreLeaguerInfoCacheDAO.getInstance().delete(target);
	}

	public StoreLeaguerInfo get(long id) {
		StoreLeaguerInfo target = StoreLeaguerInfoCacheDAO.getInstance().get(id);
		if(target == null){
			target = StoreLeaguerInfoDAO.getInstance().get(id);
			if(target != null){
				StoreLeaguerInfoCacheDAO.getInstance().save(target);
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
		if(NumberUtils.isNumber(id )){
			StoreLeaguerInfo target = this.get(Long.valueOf(id));
			if(target != null){
				long newVer = target.getVer();
				if (clientSynVer.getVer() < newVer) {
					String data = DataSynMgr.getInstance().toClientData(target);
					item = DataSynItem.newInstance(clientSynVer, newVer, data);
				}
			}else{
				MainLog.info(LogModule.Leaguer, "StoreLeaguerInfoDataHolder[getSynItem]", "获取详情对象为空[id="+id+"]");
			}
		}else{
			MainLog.info(LogModule.Leaguer, "StoreLeaguerInfoDataHolder[getSynItem]", "数据同步失败[id="+id+"]");
		}
		return item;
	}
}

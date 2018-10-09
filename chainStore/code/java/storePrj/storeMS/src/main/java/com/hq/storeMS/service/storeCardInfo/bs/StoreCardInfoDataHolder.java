package com.hq.storeMS.service.storeCardInfo.bs;

import org.apache.commons.lang3.math.NumberUtils;

import com.hq.storeMS.common.datasyn.IntfDataHolder;
import com.hq.storeMS.common.datasyn.info.DataSynItem;
import com.hq.storeMS.common.datasyn.info.DataSynType;
import com.hq.storeMS.common.datasyn.info.DataSynVer;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.service.storeCardInfo.data.StoreCardInfo;
import com.hq.storeMS.service.storeCardInfo.data.StoreCardInfoCacheDAO;
import com.hq.storeMS.service.storeCardInfo.data.StoreCardInfoDAO;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dataSyn.DataSynMgr;

public class StoreCardInfoDataHolder implements IntfDataHolder {

	public static StoreCardInfoDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(StoreCardInfoDataHolder.class);
	}

	final private DataSynType synType = DataSynType.StoreCardInfo;

	public void addWithId(StoreCardInfo target) {
		StoreCardInfoDAO.getInstance().addWithId(target);
	}

	public void update(StoreCardInfo target) {
		target.incrVer();
		target.setLastUpdateTime(System.currentTimeMillis());
		StoreCardInfoDAO.getInstance().updpate(target);
		StoreCardInfoCacheDAO.getInstance().delete(target);
	}

	public void delete(StoreCardInfo target) {
		StoreCardInfoDAO.getInstance().delete(target.getId());
		StoreCardInfoCacheDAO.getInstance().delete(target);
	}

	public StoreCardInfo get(long id) {
		StoreCardInfo target = StoreCardInfoCacheDAO.getInstance().get(id);
		if(target == null){
			target = StoreCardInfoDAO.getInstance().get(id);
			if(target != null){
				StoreCardInfoCacheDAO.getInstance().save(target);
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
			StoreCardInfo target = this.get(Long.valueOf(id));
			if(target != null){
				long newVer = target.getVer();
				if (clientSynVer.getVer() < newVer) {
					String data = DataSynMgr.getInstance().toClientData(target);
					item = DataSynItem.newInstance(clientSynVer, newVer, data);
				}
			}else{
				MainLog.error(LogModule.StoreCardInfo, "StoreCardInfoDataHolder[getSynItem]", "获取详情对象为空[id="+id+"]");
			}
		}else{
			MainLog.error(LogModule.StoreCardInfo, "StoreCardInfoDataHolder[getSynItem]", "数据同步失败[id="+id+"]");
		}
		return item;
	}
}

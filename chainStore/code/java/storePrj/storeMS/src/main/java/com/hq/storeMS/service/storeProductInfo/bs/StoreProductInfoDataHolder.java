package com.hq.storeMS.service.storeProductInfo.bs;

import org.apache.commons.lang3.math.NumberUtils;

import com.hq.storeMS.common.datasyn.IntfDataHolder;
import com.hq.storeMS.common.datasyn.info.DataSynItem;
import com.hq.storeMS.common.datasyn.info.DataSynType;
import com.hq.storeMS.common.datasyn.info.DataSynVer;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.service.storeProductInfo.data.StoreProductInfo;
import com.hq.storeMS.service.storeProductInfo.data.StoreProductInfoCacheDAO;
import com.hq.storeMS.service.storeProductInfo.data.StoreProductInfoDAO;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dataSyn.DataSynMgr;

public class StoreProductInfoDataHolder implements IntfDataHolder{
	
	public static StoreProductInfoDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(StoreProductInfoDataHolder.class);
	}
	
	final private DataSynType synType = DataSynType.StoreProductInfo;
	
	public void addWithId(StoreProductInfo target) {
		StoreProductInfoDAO.getInstance().addWithId(target);
	}
	
	public void delete(StoreProductInfo target) {
		StoreProductInfoDAO.getInstance().delete(target.getId());
		StoreProductInfoCacheDAO.getInstance().delete(target);
	}
	
	public void update(StoreProductInfo target) {
		target.incrVer();
		target.setLastUpdateTime(System.currentTimeMillis());
		StoreProductInfoDAO.getInstance().updpate(target);
		StoreProductInfoCacheDAO.getInstance().delete(target);
	}
	
	public StoreProductInfo get(long id) {
		StoreProductInfo target = StoreProductInfoCacheDAO.getInstance().get(id);
		if(target == null){
			target = StoreProductInfoDAO.getInstance().get(id);
			if(target != null){
				StoreProductInfoCacheDAO.getInstance().save(target);
			}
		}
		return target;
	}
	
	public DataSynType getSynType() {
		return synType;
	}

	public DataSynItem getSynItem(DataSynVer clientSynVer){
		DataSynItem item = null;
		String id = clientSynVer.getId();
		if(NumberUtils.isNumber(id )){
			StoreProductInfo target = this.get(Long.valueOf(id));			
			if(target != null){
				long newVer = target.getVer();
				if(clientSynVer.getVer() < newVer){
					String data = DataSynMgr.getInstance().toClientData(target);
					item = DataSynItem.newInstance(clientSynVer, newVer, data );
				}
			}else{
				MainLog.info(LogModule.StoreProductInfo, "StoreProductInfoDataHolder[getSynItem]", "获取详情对象为空[id="+id+"]");
			}
		}else{
			MainLog.info(LogModule.StoreProductInfo, "StoreProductInfoDataHolder[getSynItem]", "数据同步失败[id="+id+"]");
		}
		return item;
	}	
}
